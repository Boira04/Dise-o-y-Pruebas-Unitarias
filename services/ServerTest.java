package data.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

import data.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.services.InvalidPairingArgsException;

class ServerTest {
    private Server server;
    private UserAccount testUser;
    private VehicleID testVehicle;
    private StationID testStation;

    @BeforeEach
    void setUp() {
        server = new Server();

        // Crear una instancia válida de GeographicPoint
        GeographicPoint location = new GeographicPoint(40.4168f, -3.7038f);

        // Crear una instancia válida de StationID
        testStation = new StationID(1, location);

        // Crear una instancia válida de VehicleID
        testVehicle = new VehicleID(101, testStation);

        // Crear una instancia válida de UserAccount
        testUser = new UserAccount(
                "user123",
                "testUser",
                "test@example.com",
                "securePassword123",
                100
        );

        // Inicializar datos en el servidor
        server.setPairing(testUser, testVehicle, testStation, location, LocalDateTime.now());
    }

    @Test
    void testCheckPMVAvailThrowsPMVNotAvailException() {
        VehicleID unavailableVehicle = new VehicleID(102, testStation);
        server.setPairing(testUser, unavailableVehicle, testStation, new GeographicPoint(0, 0), LocalDateTime.now());

        assertThrows(PMVNotAvailException.class, () -> server.checkPMVAvail(unavailableVehicle));
    }

    @Test
    void testRegisterPairingThrowsInvalidPairingArgsException() {
        UserAccount newUser = new UserAccount(
                "user789",
                "invalidUser",
                "invalid@example.com",
                "password",
                20
        );

        assertThrows(InvalidPairingArgsException.class, () -> server.registerPairing(
                newUser,
                testVehicle,
                testStation,
                new GeographicPoint(1.0f, 1.0f), // Location mismatch
                LocalDateTime.now()
        ));
    }

    @Test
    void testUnPairRegisterServiceThrowsPairingNotFoundException() {
        JourneyService invalidJourney = new JourneyService();
        assertThrows(PairingNotFoundException.class, () -> server.unPairRegisterService(invalidJourney));
    }

    @Test
    void testVehicleAvailabilityAfterStopPairing() throws ConnectException, InvalidPairingArgsException {
        GeographicPoint location = (GeographicPoint) testStation.getgeoPoint();

        server.stopPairing(
                testUser,
                testVehicle,
                testStation,
                location,
                LocalDateTime.now(),
                15.0f,
                5.0f,
                10,
                BigDecimal.valueOf(2.5)
        );

        // Verifica si el vehículo está disponible después de detener el emparejamiento
        assertDoesNotThrow(() -> server.checkPMVAvail(testVehicle));
    }

    @Test
    void testRegisterPairingWithNullLocationThrowsException() {
        UserAccount newUser = new UserAccount("user999", "newUser", "new@example.com", "password123", 30);

        assertThrows(InvalidPairingArgsException.class, () -> server.registerPairing(
                newUser,
                testVehicle,
                testStation,
                null, // Location null
                LocalDateTime.now()
        ));
    }

    @Test
    void testCheckPMVAvail() {
        assertDoesNotThrow(() -> server.checkPMVAvail(testVehicle));
    }

    @Test
    void testRegisterPairing() throws ConnectException, InvalidPairingArgsException {
        UserAccount newUser = new UserAccount(
                "user456",
                "newUser",
                "newuser@example.com",
                "anotherSecurePassword123",
                50
        );

        // Cast explícito de getgeoPoint()
        GeographicPoint location = (GeographicPoint) testStation.getgeoPoint();

        assertDoesNotThrow(() -> server.registerPairing(
                newUser,
                testVehicle,
                testStation,
                location,
                LocalDateTime.now()
        ));
    }

    @Test
    void testStopPairing() throws ConnectException, InvalidPairingArgsException {
        // Cast explícito de getgeoPoint()
        GeographicPoint location = (GeographicPoint) testStation.getgeoPoint();

        assertDoesNotThrow(() -> server.stopPairing(
                testUser,
                testVehicle,
                testStation,
                location,
                LocalDateTime.now(),
                15.0f,
                5.0f,
                10,
                BigDecimal.valueOf(2.5)
        ));
    }

    @Test
    void testRegisterPairingWithValidation() {
        assertInstanceOf(GeographicPoint.class, testStation.getgeoPoint(), "Expected GeographicPoint");
    }
}
