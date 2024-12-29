package data.micromobility;

import data.data.GeographicPointDoble;
import data.data.GeographicPointInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyServiceTest {

    private JourneyService journeyService;

    @BeforeEach
    void setUp() {
        journeyService = new JourneyService();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(journeyService.getInitDate());
        assertEquals(0, journeyService.getDuration());
        assertEquals(0, journeyService.getDistance());
        assertEquals(0, journeyService.getAvgSpeed());
        assertNull(journeyService.getOriginPoint());
        assertNull(journeyService.getEndPoint());
        assertNull(journeyService.getEndDate());
        assertNull(journeyService.getImportCost());
        assertFalse(journeyService.isInProgress());
    }

    @Test
    void testSetServiceInit() {
        LocalDateTime initDate = LocalDateTime.now();
        GeographicPointInterface mockOriginPoint = new GeographicPointDoble();

        journeyService.setServiceInit(initDate, mockOriginPoint);

        assertEquals(initDate, journeyService.getInitDate());
        assertEquals(mockOriginPoint, journeyService.getOriginPoint());
        assertTrue(journeyService.isInProgress());
    }

    @Test
    void testSetServiceFinish() {
        LocalDateTime endDate = LocalDateTime.now();
        GeographicPointInterface mockEndPoint = new GeographicPointDoble();
        BigDecimal cost = new BigDecimal("25.50");
        float avgSpeed = 15.2f;
        float distance = 12.4f;
        float duration = 30.5f;

        journeyService.setServiceFinish(endDate, mockEndPoint, cost, avgSpeed, distance, duration);

        assertEquals(endDate, journeyService.getEndDate());
        assertEquals(mockEndPoint, journeyService.getEndPoint());
        assertEquals(cost, journeyService.getImportCost());
        assertEquals(avgSpeed, journeyService.getAvgSpeed());
        assertEquals(distance, journeyService.getDistance());
        assertEquals(duration, journeyService.getDuration());
        assertFalse(journeyService.isInProgress());
    }
}
