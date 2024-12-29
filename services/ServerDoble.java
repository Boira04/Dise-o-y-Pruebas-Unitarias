package data.services;

import data.data.GeographicPointInterface;
import data.data.StationIDInterface;
import data.data.UserAccountInterface;
import data.data.VehicleIDInterface;
import data.micromobility.JourneyServiceInterface;


import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

public class ServerDoble implements ServerInterface {

    private int contCheck;
    private int contRegister;
    private int contStopPairing;

    @Override
    public void checkPMVAvail(VehicleIDInterface vhID) throws PMVNotAvailException, ConnectException {
        contCheck++;
    }

    @Override
    public void registerPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, JourneyServiceInterface journey) throws InvalidPairingArgsException, ConnectException {
        contRegister++;
    }

    @Override
    public void stopPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp) throws InvalidPairingArgsException, ConnectException {
        contStopPairing++;
    }

    @Override
    public void setPairing(UserAccountInterface user, VehicleIDInterface veh, StationIDInterface st, GeographicPointInterface loc, LocalDateTime date, JourneyServiceInterface journey) {

    }

    @Override
    public void unPairRegisterService(JourneyServiceInterface s) throws PairingNotFoundException {

    }

    @Override
    public void registerLocation(VehicleIDInterface veh, StationIDInterface st) {

    }

    public int getContCheck() {
        return contCheck;
    }

    public int getContRegister() {
        return contRegister;
    }

    public int getContStopPairing() {
        return contStopPairing;
    }
}