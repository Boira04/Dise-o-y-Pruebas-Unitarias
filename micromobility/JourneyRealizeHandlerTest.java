package data.micromobility;

import data.data.*;
import data.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyRealizeHandlerTest {

    private JourneyRealizeHandler journeyHandler;
    private QRDecoderDoble qrDecoder;
    private ServerDoble server;
    private UserAccountDoble user;
    private StationIDdoble station;
    private GeographicPointDoble location;
    private VehicleIDdoble vehicle;
    private BufferedImage qrImage;

    @BeforeEach
    void setUp() {
        // Configurar dependències simulades (dobles)
        qrDecoder = new QRDecoderDoble();
        server = new ServerDoble();
        station = new StationIDdoble();
        journeyHandler = new JourneyRealizeHandler(qrDecoder, server, station);
        user = new UserAccountDoble();
        location = new GeographicPointDoble();
        vehicle = new VehicleIDdoble();

        qrImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        journeyHandler.setUser(user);
        journeyHandler.setLocation(location);
        journeyHandler.setQrImage(qrImage);
        journeyHandler.setJourney(new JourneyServiceDoble());
    }

    // TESTOS PER SCANQR
    @Test
    void testScanQRSuccess() {
        assertDoesNotThrow(() -> journeyHandler.scanQR());
        assertNotNull(journeyHandler.getVehicleID());
        assertEquals(3, journeyHandler.getVehicleID().getId());
        assertEquals(1, server.getContCheck());
        assertEquals(1, server.getContRegister());
    }

    @Test
    void testScanQRWithNullImage() {
        journeyHandler.setQrImage(null);
        assertThrows(ProceduralException.class, () -> journeyHandler.scanQR());
    }

    // TESTOS PER UNPAIRVEHICLE
    @Test
    void testUnPairVehicleSuccess() {
        journeyHandler.setVehicleID(vehicle);
        journeyHandler.setStartDate(LocalDateTime.now().minusMinutes(30));
        journeyHandler.setEndDate(LocalDateTime.now());

        assertDoesNotThrow(() -> journeyHandler.unPairVehicle());
        assertEquals(1, server.getContStopPairing());
    }

    @Test
    void testUnPairVehicleWithMissingData() {
        journeyHandler.setVehicleID(null);

        assertThrows(ProceduralException.class, () -> journeyHandler.unPairVehicle());
    }

    // TESTOS PER BROADCASTSTATIONID
    @Test
    void testBroadcastStationIDSuccess() {
        StationIDdoble2 newStation = new StationIDdoble2();

        assertDoesNotThrow(() -> journeyHandler.broadcastStationID(newStation));
        assertEquals(newStation, journeyHandler.getStation());
    }

    @Test
    void testBroadcastStationIDWithNullStation() {
        assertThrows(ConnectException.class, () -> journeyHandler.broadcastStationID(null));
    }

    // TESTOS PER STARTDRIVING
    @Test
    void testStartDrivingSuccess() {
        journeyHandler.setVehicleID(vehicle);

        assertDoesNotThrow(() -> journeyHandler.startDriving());
    }

    @Test
    void testStartDrivingWithoutVehicle() {
        journeyHandler.setVehicleID(null);

        assertThrows(ProceduralException.class, () -> journeyHandler.startDriving());
    }

    // TESTOS PER STOPDRIVING
    @Test
    void testStopDrivingSuccess() {
        journeyHandler.setStartDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        journeyHandler.setEndDate(LocalDateTime.of(2024, 1, 1, 12, 0));
        try {
            journeyHandler.scanQR();
            journeyHandler.startDriving();
            journeyHandler.broadcastStationID(new StationIDdoble2());
            assertDoesNotThrow(() -> journeyHandler.stopDriving());
        } catch (java.net.ConnectException e) {
            throw new RuntimeException(e);
        } catch (InvalidPairingArgsException e) {
            throw new RuntimeException(e);
        } catch (CorruptedImgException e) {
            throw new RuntimeException(e);
        }

    }

    // TESTOS PER CALCULATEVALUES
    @Test
    void testCalculateValues() {
        journeyHandler.setStartDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        journeyHandler.setEndDate(LocalDateTime.of(2024, 1, 1, 12, 0));
        try {
            journeyHandler.scanQR();
            journeyHandler.startDriving();
            journeyHandler.broadcastStationID(new StationIDdoble2());
            journeyHandler.stopDriving();
        } catch (java.net.ConnectException e) {
            throw new RuntimeException(e);
        } catch (InvalidPairingArgsException e) {
            throw new RuntimeException(e);
        } catch (CorruptedImgException e) {
            throw new RuntimeException(e);
        }



        assertEquals(363.3056, journeyHandler.getDistance(), 0.1f);
        assertEquals(120, journeyHandler.getDuration());
        assertEquals(181.6528, journeyHandler.getAverageSpeed(), 0.1f);
    }

    // TESTOS PER CALCULATEIMPORT
    @Test
    void testCalculateImport() {
        journeyHandler.setDistance(10.5f);
        journeyHandler.setDuration(45);
        journeyHandler.setAverageSpeed(14.0f);
        journeyHandler.setStartDate(LocalDateTime.now());

        journeyHandler.setEndDate(LocalDateTime.now().plusMinutes(45));
        journeyHandler.setVehicleID(new VehicleIDdoble());
        journeyHandler.setStation(new StationIDdoble2());
        try {
            journeyHandler.unPairVehicle();
        } catch (java.net.ConnectException e) {
            throw new RuntimeException(e);
        } catch (InvalidPairingArgsException e) {
            throw new RuntimeException(e);
        }

        BigDecimal expectedAmount = BigDecimal.valueOf(1.5)
                .add(BigDecimal.valueOf(0.5).multiply(BigDecimal.valueOf(10.5)))
                .add(BigDecimal.valueOf(0.2).multiply(BigDecimal.valueOf(45)));

        assertEquals(expectedAmount, journeyHandler.getAmount());
    }
}
