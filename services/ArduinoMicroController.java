package data.services;

import data.micromobility.ProceduralException;

import java.net.ConnectException;

public interface ArduinoMicroController {
    void setBTconnection() throws ConnectException;
    void startDriving() throws PMVPhisicalException, ConnectException,
            ProceduralException;
    void stopDriving() throws PMVPhisicalException, ConnectException,
            ProceduralException;
    void undoBTconnection();

}
