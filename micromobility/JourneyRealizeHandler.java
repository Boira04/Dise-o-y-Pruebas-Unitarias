package data.micromobility;

import data.data.*;
import data.services.*;

import java.awt.image.BufferedImage;
import java.net.ConnectException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.chrono.ChronoLocalDate;

public class JourneyRealizeHandler {

    // Different input events that intervene
    // User interface input events

    private final QRDecoder qrDecoder;
    private final ServerInterface server;

    private BufferedImage qrImage;
    private UserAccountInterface user;
    private StationIDInterface station;
    private StationIDInterface newStation;
    private GeographicPointInterface location;
    private GeographicPointInterface initialLocation;
    private VehicleIDInterface vehicleID;
    private float averageSpeed;
    private float distance;
    private int duration;
    private BigDecimal amount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public JourneyRealizeHandler(QRDecoder qrDecoder, Server server) {
        this.qrDecoder = qrDecoder;
        this.server = server;
    }

    public void scanQR()
            throws ConnectException, InvalidPairingArgsException, CorruptedImgException, PMVNotAvailException, ProceduralException {
        try {
            // 1. Decodificar el QR para obtener el VehicleID
            VehicleIDInterface vehicleID = qrDecoder.getVehicleID(qrImage);

            // 2. Verificar disponibilidad del vehículo
            server.checkPMVAvail(vehicleID);

            // 3. Registrar el emparejamiento
            server.registerPairing(user, vehicleID, station, location, LocalDateTime.now());

            initialLocation = vehicleID.getStation().getgeoPoint(); // Guardamos la posicion inicial para calcular la distancia posteriormente

        } catch (CorruptedImgException e) {
            throw new CorruptedImgException("La imagen del QR no es válida.");

        } catch (PMVNotAvailException e) {
            throw new PMVNotAvailException("El vehículo no está disponible para emparejamiento.");

        } catch (ConnectException e) {
            throw new ConnectException("Error al conectar con el servidor.");

        } catch (InvalidPairingArgsException e) {
            throw new InvalidPairingArgsException("Error en los argumentos de emparejamiento.");

        } catch (Exception e) {
            throw new ProceduralException("Error en el proceso de escaneo y emparejamiento.");
        }
    }
    public void unPairVehicle ()
            throws ConnectException, InvalidPairingArgsException, PairingNotFoundException, ProceduralException
    {
        try {
            if (user == null || vehicleID == null || station == null || location == null) {
                throw new ProceduralException("Los datos necesarios para finalizar el servicio no están configurados.");
            }

            server.stopPairing(user, vehicleID, station, location, LocalDateTime.now(), averageSpeed, distance, duration, amount);

        } catch (PairingNotFoundException e) {
            throw new PairingNotFoundException("No se encontró el registro de emparejamiento en el servidor.");

        } catch (InvalidPairingArgsException e) {
            throw new InvalidPairingArgsException("Error en los argumentos al detener el emparejamiento.");

        } catch (ConnectException e) {
            throw new ConnectException("Error al conectar con el servidor.");

        } catch (Exception e) {
            throw new ProceduralException("Error en el proceso de finalizar el servicio.");
        }
    }
    // Input events from the unbonded Bluetooth channel
    public void broadcastStationID (StationIDInterface stID)
            throws ConnectException
    {
        try {
            // Simular la recepción del ID de la estación
            if (stID == null) {
                throw new ConnectException("Error: No se recibió un ID de estación válido.");
            }

            // Actualizar la estación activa en el manejador
            this.station = stID;

        } catch (ConnectException e) {
            throw new ConnectException("Error al recibir el ID de la estación: " + e.getMessage());
        }
    }
    // Input events from the Arduino microcontroller channel
    public void startDriving ()
            throws ConnectException, ProceduralException
    {
        if (vehicleID == null) {
            throw new ProceduralException("Error: No se ha configurado un vehículo para iniciar el desplazamiento.");
        }

        System.out.println("Conexión Bluetooth establecida con el vehículo: " + vehicleID);

        System.out.println("El desplazamiento ha comenzado con el vehículo: " + vehicleID);
    }
    public void stopDriving ()
            throws ConnectException, ProceduralException
    {
        try {
            if (vehicleID == null) {
                throw new ProceduralException("Error: No se ha configurado un vehículo para detener el desplazamiento.");
            }

            if (newStation == null) {
                throw new ConnectException("Error: No se pudo establecer la desconexión Bluetooth.");
            } else {
                station = newStation;
                location = newStation.getgeoPoint(); // Guardamos el punto en el que termina el trayecto para calcular la distancia posteriormente
                newStation = null;
            }

            System.out.println("El desplazamiento se ha detenido con el vehículo: " + vehicleID);

        } catch (ConnectException e) {
            throw new ConnectException("Error al desconectar el vehículo: " + e.getMessage());
        }
    }
    // Internal operations
    private void calculateValues (GeographicPointInterface gP, LocalDateTime date)
    {
        // 1. Calcular la distancia (en kilómetros) usando una fórmula simplificada de haversine
        this.distance = calculateDistance(initialLocation, location);

        // 2. Calcular la duración (en minutos)
        this.duration = (int) Duration.between(startDate, endDate).toMinutes();

        // 3. Calcular la velocidad promedio (en km/h)
        if (duration > 0) {
            this.averageSpeed = (distance / duration) * 60; // Convertir a km/h
        } else {
            this.averageSpeed = 0; // Evitar división por cero
        }
    }

    private float calculateDistance(GeographicPointInterface start, GeographicPointInterface end) {
        final int EARTH_RADIUS_KM = 6371;

        double lat1 = Math.toRadians(start.getLatitude());
        double lon1 = Math.toRadians(start.getLongitude());
        double lat2 = Math.toRadians(end.getLatitude());
        double lon2 = Math.toRadians(end.getLongitude());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (EARTH_RADIUS_KM * c);
    }

    private void calculateImport (float dis, int dur, float avSp, LocalDateTime date)
    {
        // 1. Fórmula simplificada para calcular el importe
        // Precio base más un cargo por distancia y duración
        BigDecimal baseRate = BigDecimal.valueOf(1.5); // Tarifa base
        BigDecimal distanceRate = BigDecimal.valueOf(0.5).multiply(BigDecimal.valueOf(dis)); // 0.5 por km
        BigDecimal timeRate = BigDecimal.valueOf(0.2).multiply(BigDecimal.valueOf(dur)); // 0.2 por minuto

        // Sumar todas las tarifas
        this.amount = baseRate.add(distanceRate).add(timeRate);
    }

    public StationIDInterface getStation() {
        return this.station;
    }

    public VehicleIDInterface getVehicleID() {
        return this.vehicleID;
    }

    public float getDistance() {
        return this.distance;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setQrImage(BufferedImage qrImage) {
        this.qrImage = qrImage;
    }

    public void setUser(UserAccountInterface user) {
        this.user = user;
    }

    public void setStation(StationIDInterface station) {
        this.station = station;
    }

    public void setLocation(GeographicPointInterface location) {
        this.location = location;
    }

    public void setVehicleID(VehicleIDInterface vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setNewStation(StationIDInterface newStation) {
        this.newStation = newStation;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}

