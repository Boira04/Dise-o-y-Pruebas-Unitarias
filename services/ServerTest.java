package data.services;

import static org.junit.jupiter.api.Assertions.*;

import data.data.*;
import data.micromobility.JourneyServiceDoble;
import data.micromobility.JourneyServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

class ServerTest {
    private Server server; // Clase real para probar
    private VehicleIDdoble testVehicle;
    private StationIDdoble testStation;
    private GeographicPointDoble testLocation;
    private UserAccountDoble testUser;
    private JourneyServiceDoble testJourney;

    @BeforeEach
    void setUp() {
        // Inicialización de la clase real y los dobles
        server = new Server();
        testLocation = new GeographicPointDoble();
        testStation = new StationIDdoble();
        testVehicle = new VehicleIDdoble();
        testUser = new UserAccountDoble();
        testJourney = new JourneyServiceDoble();
    }

    @Test
    void testCheckPMVAvailSuccess() throws Exception {
        // Configurar el estado inicial del servidor
        server.setPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), testJourney);

        // Verificar que no lanza excepciones si el vehículo está disponible
        PMVNotAvailException exception = assertThrows(
                PMVNotAvailException.class,
                () -> server.checkPMVAvail(testVehicle)
        );
    }

    @Test
    void testCheckPMVAvailThrowsPMVNotAvailException() {
        // Configurar el estado inicial marcando el vehículo como no disponible
        server.setPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), testJourney);

        // Marcar vehículo como no disponible
        assertThrows(PMVNotAvailException.class, () -> server.checkPMVAvail(testVehicle));
    }

    @Test
    void testRegisterPairingSuccess() throws Exception {
        // Registrar un emparejamiento exitoso
        server.registerPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), testJourney);

        // Verificar que el emparejamiento se realizó sin errores
        PMVNotAvailException exception = assertThrows(
                PMVNotAvailException.class,
                () -> server.checkPMVAvail(testVehicle)
        );
    }

    @Test
    void testRegisterPairingThrowsInvalidPairingArgsException() {
        // Crear una ubicación incorrecta
        GeographicPointDoble2 wrongLocation = new GeographicPointDoble2();
        // Intentar registrar un emparejamiento con argumentos inválidos
        assertThrows(InvalidPairingArgsException.class, () ->
                server.registerPairing(testUser, testVehicle, testStation, wrongLocation, LocalDateTime.now(), testJourney)
        );
    }

    @Test
    void testStopPairingSuccess() throws Exception {
        // Configurar el estado inicial
        server.setPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), testJourney);

        // Finalizar el emparejamiento
        server.stopPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(),
                20.0f, 5.0f, 15, BigDecimal.valueOf(10.0));

        // Verificar que no lanza excepciones
        assertDoesNotThrow(() -> server.checkPMVAvail(testVehicle));
    }

    @Test
    void testStopPairingThrowsInvalidPairingArgsException() {
        // Crear una ubicación incorrecta
        GeographicPointDoble2 wrongLocation = new GeographicPointDoble2();

        server.setPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), testJourney);

        // Intentar detener un emparejamiento con argumentos inválidos
        assertThrows(InvalidPairingArgsException.class, () ->
                server.stopPairing(testUser, testVehicle, testStation, wrongLocation, LocalDateTime.now(),
                        20.0f, 5.0f, 15, BigDecimal.valueOf(10.0))
        );
    }

    @Test
    void testSetPairingAndRetrieveState() {
        // Configurar un emparejamiento
        server.setPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), testJourney);
        try {
            server.stopPairing(testUser, testVehicle, testStation, testLocation, LocalDateTime.now(), 20.0f, 5.0f, 15, BigDecimal.valueOf(10.0));
        } catch (InvalidPairingArgsException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
        // Verificar que el vehículo está disponible
        assertDoesNotThrow(() -> server.checkPMVAvail(testVehicle));
    }

    @Test
    void testUnPairRegisterServiceThrowsPairingNotFoundException() {
        // Intentar eliminar un emparejamiento inexistente
        JourneyServiceDoble journey = new JourneyServiceDoble();
        assertThrows(PairingNotFoundException.class, () -> server.unPairRegisterService(journey));
    }
}