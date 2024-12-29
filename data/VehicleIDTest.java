package data.data;

import data.data.VehicleID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleIDTest {
    private VehicleID vehicle1;
    private VehicleID vehicle2;
    private StationIDdoble station1;
    private StationIDdoble station2;


    @BeforeEach
    void setUp() {
        station1 = new StationIDdoble();
        station2 = new StationIDdoble();
        vehicle1 = new VehicleID(100, station1);
        vehicle2 = new VehicleID(200, station2);
    }

    @Test
    void testGetId() {
        assertEquals(100, vehicle1.getId());
        assertEquals(200, vehicle2.getId());
    }

    @Test
    void testGetStation() {
        assertEquals(station1, vehicle1.getStation());
        assertEquals(station2, vehicle2.getStation());
    }

    @Test
    void testEquals() {
        VehicleID vehicle3 = new VehicleID(100, station1);
        assertEquals(vehicle1, vehicle3);
        assertNotEquals(vehicle1, vehicle2);
    }

    @Test
    void testHashCode() {
        VehicleID vehicle3 = new VehicleID(100, station1);
        assertEquals(vehicle1.hashCode(), vehicle3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("data.data.VehicleID{id=100, station=StationID {ID='1', geoPoint='Geographic point {latitude='46.7158', longitude='52.7335'}'}}", vehicle1.toString());
    }

    @Test
    void testExceptionHandling() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // Code that is expected to throw IllegalArgumentException
            VehicleID vehicle3 = new VehicleID(-1, station1);
        });
        assertEquals("L'ID del vehicle ha de ser un nombre positiu.", exception.getMessage());
    }
}
