package data.services;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import data.data.*;
import data.data.JourneyService;

public class Server implements data.services.ServerInterface {
    // Almacenamiento simulado para datos persistentes
    private final Map<VehicleIDInterface, Boolean> vehicleAvailability = new HashMap<>();
    private final Map<VehicleIDInterface, GeographicPointInterface> vehicleLocations = new HashMap<>();
    private final Map<VehicleIDInterface, StationIDInterface> vehicleStation = new HashMap<>();
    private final Map<UserAccountInterface, JourneyService> userJourneyRecords = new HashMap<>();

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
    public void registerPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date)
            throws InvalidPairingArgsException, ConnectException {
        if (!vehicleAvailability.containsKey(veh.getId())) {
            throw new ConnectException("Connection failed: Vehicle ID not found in the server.");
        }
        if (!vehicleLocations.containsKey(loc) || !vehicleStation.containsKey(st)) {
            throw new InvalidPairingArgsException("Vehicle ID or location not registered in the server.");
        }
        if (!vehicleLocations.get(veh).equals(loc)) {
            throw new InvalidPairingArgsException("Provided location does not match the vehicle's actual location.");
        }
        vehicleAvailability.put(veh, false); // Mark vehicle as unavailable
        userJourneyRecords.put(user, new JourneyService()); // Initialize journey for user
    }

    @Override
    public void stopPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp)
            throws InvalidPairingArgsException, ConnectException {
        if (!vehicleAvailability.containsKey(veh.getId())) {
            throw new ConnectException("Connection failed: Vehicle ID not found in the server.");
        }
        if (!vehicleLocations.containsKey(loc) || !vehicleStation.containsKey(st)) {
            throw new InvalidPairingArgsException("Vehicle ID or location not registered in the server.");
        }
        if (!vehicleLocations.get(veh).equals(loc)) {
            throw new InvalidPairingArgsException("Provided location does not match the vehicle's actual location.");
        }
        if (!userJourneyRecords.containsKey(user)) {
            throw new InvalidPairingArgsException("No pairing record found for the user.");
        }
        // Update vehicle state and location
        vehicleAvailability.put(veh, true);
        vehicleLocations.put(veh, loc);
        // Record the journey details
        JourneyService journey = userJourneyRecords.get(user);
        journey.setServiceFinish(); // Assuming setServiceFinish finalizes the service
    }

    @Override
    public void setPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date) {
        vehicleAvailability.put(veh, false);
        vehicleLocations.put(veh, loc);
    }

    @Override
    public void unPairRegisterService(JourneyService s) throws PairingNotFoundException {
        if (!userJourneyRecords.containsValue(s)) {
            throw new PairingNotFoundException("No matching journey service record found.");
        }
        // Simulate unpairing process
        userJourneyRecords.values().remove(s);
    }

    @Override
    public void registerLocation(VehicleIDInterface veh, StationIDInterface st) {
        vehicleStation.put(veh, st); // Example default location
    }
}
