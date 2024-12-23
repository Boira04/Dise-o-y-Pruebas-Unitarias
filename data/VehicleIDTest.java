package data.data;

import data.data.VehicleID;
import data.data.StationID;
import data.data.GeographicPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleIDTest {
    private VehicleID vehicle1;
    private VehicleID vehicle2;
    private StationID station1;
    private StationID station2;

    @BeforeEach
    void setUp() {
        GeographicPoint geoPoint1 = new GeographicPoint(37.7749f, -122.4194f); // San Francisco
        GeographicPoint geoPoint2 = new GeographicPoint(34.0522f, -118.2437f); // Los Angeles
        station1 = new StationID(1, geoPoint1);
        station2 = new StationID(2, geoPoint2);
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
        assertEquals("data.data.VehicleID{id=100, location=Geographic point {ID='1geoPoint='Geographic point {latitude='37.7749longitude='-122.4194'}}}", vehicle1.toString());
    }
}
