package data.data;

import data.data.StationID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationIDTest {
    private StationID station1;
    private StationID station2;
    private GeographicPointDoble geoPoint1;
    private GeographicPointDoble geoPoint2;

    @BeforeEach
    void setUp() {
        geoPoint1 = new GeographicPointDoble(); // Paris
        geoPoint2 = new GeographicPointDoble(); // London
        station1 = new StationID(1, geoPoint1);
        station2 = new StationID(2, geoPoint2);
    }

    @Test
    void testGetID() {
        assertEquals(1, station1.getID());
        assertEquals(2, station2.getID());
    }

    @Test
    void testGetGeoPoint() {
        assertEquals(geoPoint1, station1.getgeoPoint());
        assertEquals(geoPoint2, station2.getgeoPoint());
    }

    @Test
    void testEquals() {
        StationID station3 = new StationID(1, geoPoint1);
        assertEquals(station1, station3);
        assertNotEquals(station1, station2);
    }

    @Test
    void testHashCode() {
        StationID station3 = new StationID(1, geoPoint1);
        assertEquals(station1.hashCode(), station3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("StationID {ID='1'geoPoint='Geographic point {latitude='46.7158', longitude='52.7335'}}", station1.toString());
    }

    @Test void testExceptionHandling() { Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        // Code that is expected to throw IllegalArgumentException 
        StationID station3 = new StationID(-1, geoPoint1);
    });
        assertEquals("L'ID de la estació ha de ser un nombre positiu.", exception.getMessage()); }
}
