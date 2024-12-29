package data.services.smartfeatures;

import data.micromobility.ProceduralException;
import data.services.PMVPhisicalException;

import java.net.ConnectException;

/**
 * Implementación de la interfaz ArduinoMicroController.
 * Simula el comportamiento de un microcontrolador Arduino.
 */
public class ArduinoMicroControllerImpl implements ArduinoMicroController {

    private boolean btConnected;
    private boolean driving;

    /**
     * Constructor por defecto.
     * Inicializa el estado del microcontrolador con BT desconectado y no conduciendo.
     */
    public ArduinoMicroControllerImpl() {
        this.btConnected = false;
        this.driving = false;
    }

    /**
     * Establece la conexión Bluetooth (BT) con el dispositivo.
     *
     * @throws ConnectException si ya está conectado.
     */
    @Override
    public void setBTconnection() throws ConnectException {
        if (btConnected) {
            throw new ConnectException("BT ya está conectado.");
        }
        btConnected = true;
    }

    /**
     * Inicia el desplazamiento del vehículo.
     *
     * @throws PMVPhisicalException si ocurre un problema técnico.
     * @throws ConnectException     si BT no está conectado.
     * @throws ProceduralException  si ya está en movimiento.
     */
    @Override
    public void startDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
        if (!btConnected) {
            throw new ConnectException("BT no está conectado.");
        }
        if (driving) {
            throw new ProceduralException("El vehículo ya está en movimiento.");
        }
        // Simulación de posibles problemas técnicos
        if (Math.random() < 0.1) { // Ejemplo: 10% de probabilidad de fallo técnico
            throw new PMVPhisicalException("Problema técnico: no se puede iniciar el trayecto.");
        }
        driving = true;
    }

    /**
     * Detiene el desplazamiento del vehículo.
     *
     * @throws PMVPhisicalException si ocurre un problema técnico con los frenos.
     * @throws ConnectException     si BT no está conectado (rara vez debería ocurrir aquí).
     * @throws ProceduralException  si el vehículo ya está detenido.
     */
    @Override
    public void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
        if (!driving) {
            throw new ProceduralException("El vehículo no está en movimiento.");
        }
        // Simulación de posibles problemas técnicos
        if (Math.random() < 0.1) { // Ejemplo: 10% de probabilidad de fallo técnico
            throw new PMVPhisicalException("Problema técnico: fallo en los frenos.");
        }
        driving = false;
    }

    /**
     * Desconecta la conexión Bluetooth (BT) y reinicia el estado de conducción.
     */
    @Override
    public void undoBTconnection() {
        btConnected = false;
        driving = false;
    }
}
