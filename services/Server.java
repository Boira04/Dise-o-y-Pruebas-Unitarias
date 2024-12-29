package data.services;

import java.awt.desktop.SystemEventListener;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import data.data.*;
import data.micromobility.JourneyServiceInterface;

public class Server implements data.services.ServerInterface {
    // Almacenamiento simulado para datos persistentes
    private final Map<VehicleIDInterface, Boolean> vehicleAvailability = new HashMap<>();
    private final Map<VehicleIDInterface, GeographicPointInterface> vehicleLocations = new HashMap<>();
    private final Map<VehicleIDInterface, StationIDInterface> vehicleStation = new HashMap<>();
    private final Map<UserAccountInterface, JourneyServiceInterface> userJourneyRecords = new HashMap<>();

    @Override
    public void checkPMVAvail(VehicleIDInterface vhID) throws PMVNotAvailException, ConnectException {
        if (!vehicleAvailability.containsKey(vhID)) {
            throw new ConnectException("Connection failed: Vehicle ID not found in the server.");
        }
        if (!vehicleAvailability.get(vhID)) {
            throw new PMVNotAvailException("The vehicle is not available for pairing.");
        }
    }

    @Override
    public void registerPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, JourneyServiceInterface journey)
            throws InvalidPairingArgsException, ConnectException {

        StationIDInterface currentStation = veh.getStation();
        if (!currentStation.equals(st)) {
            throw new InvalidPairingArgsException("The provided station does not match the vehicle's registered station.");
        }

        GeographicPointInterface stationLocation = st.getgeoPoint();
        if (!stationLocation.equals(loc)) {
            throw new InvalidPairingArgsException("The provided location does not match the location of the station.");
        }

        vehicleAvailability.put(veh, false); // Marquem el vehicle a no disponible
        vehicleLocations.put(veh, loc);
        vehicleStation.put(veh, st);
        journey.setServiceInit(date, loc);
        userJourneyRecords.put(user, journey);
    }

    @Override
    public void stopPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp)
            throws InvalidPairingArgsException, ConnectException {
        if (!vehicleAvailability.containsKey(veh)) {
            throw new ConnectException("Connection failed: Vehicle ID not found in the server.");
        }
        if (!vehicleStation.containsKey(veh) || !vehicleLocations.containsValue(loc)) {
            throw new InvalidPairingArgsException("Station or location not registered in the server.");
        }

        StationIDInterface currentStation = veh.getStation();
        if (!currentStation.equals(st)) {
            throw new InvalidPairingArgsException("The provided station does not match the vehicle's registered station.");
        }

        GeographicPointInterface stationLocation = st.getgeoPoint();
        if (!stationLocation.equals(loc)) {
            throw new InvalidPairingArgsException("The provided location does not match the location of the station.");
        }

        vehicleAvailability.put(veh, true);
        vehicleLocations.put(veh, loc);

        System.out.println(userJourneyRecords);
        JourneyServiceInterface journey = userJourneyRecords.get(user);
        journey.setServiceFinish(date, loc, imp, avSp, dist, dur);
    }

    @Override
    public void setPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, JourneyServiceInterface journey) {
        vehicleAvailability.put(veh, false);
        vehicleLocations.put(veh, loc);
        vehicleStation.put(veh, st);
        journey.setServiceInit(date, loc);
        userJourneyRecords.put(user, journey);

    }

    @Override
    public void unPairRegisterService(JourneyServiceInterface s) throws PairingNotFoundException {
        if (!userJourneyRecords.containsValue(s)) {
            throw new PairingNotFoundException("No matching journey service record found.");
        }

        userJourneyRecords.values().remove(s);
    }

    @Override
    public void registerLocation(VehicleIDInterface veh, StationIDInterface st) {
        vehicleStation.put(veh, st);
    }
}