package data.services;

import data.data.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

public interface ServerInterface {

    // To be invoked by the use case controller
    void checkPMVAvail(VehicleIDInterface vhID)
            throws PMVNotAvailException, ConnectException;
    void registerPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date)
            throws InvalidPairingArgsException, ConnectException;
    void stopPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp)
            throws InvalidPairingArgsException, ConnectException;
    // Internal operations
    void setPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date);
    void unPairRegisterService(JourneyServiceInterface s)
            throws PairingNotFoundException;
    void registerLocation(VehicleIDInterface veh, StationIDInterface st);
}

