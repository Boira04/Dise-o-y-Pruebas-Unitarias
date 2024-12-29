package data.services;

import data.micromobility.ProceduralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para ArduinoMicroControllerImpl.
 */
class ArduinoMicroControllerImplTest {

    private ArduinoMicroControllerImpl controller;

    @BeforeEach
    void setUp() {
        // Instancia directa de la implementación
        controller = new ArduinoMicroControllerImpl();
    }

    @Test
    void testSetBTconnectionSuccess() throws ConnectException {
        controller.setBTconnection(); // No debería lanzar excepción
    }

    @Test
    void testSetBTconnectionAlreadyConnected() throws ConnectException {
        controller.setBTconnection(); // Conexión inicial
        assertThrows(ConnectException.class, controller::setBTconnection, "BT ya está conectado.");
    }

    @Test
    void testStartDrivingSuccess() throws ConnectException, PMVPhisicalException, ProceduralException {
        controller.setBTconnection();
        controller.startDriving(); // No debería lanzar excepción
    }

    @Test
    void testStartDrivingWithoutBTConnection() {
        assertThrows(ConnectException.class, controller::startDriving, "BT no está conectado.");
    }

    @Test
    void testStartDrivingWhileAlreadyDriving() throws ConnectException, PMVPhisicalException, ProceduralException {
        controller.setBTconnection();
        controller.startDriving();
        assertThrows(ProceduralException.class, controller::startDriving, "El vehículo ya está en movimiento.");
    }

    @Test
    void testStopDrivingSuccess() throws ConnectException, PMVPhisicalException, ProceduralException {
        controller.setBTconnection();
        controller.startDriving();
        controller.stopDriving(); // No debería lanzar excepción
    }

    @Test
    void testStopDrivingWhenNotDriving() {
        assertThrows(ProceduralException.class, controller::stopDriving, "El vehículo no está en movimiento.");
    }

    @Test
    void testUndoBTconnection() throws ConnectException {
        controller.setBTconnection();
        controller.undoBTconnection(); // No debería lanzar excepción
    }
}
