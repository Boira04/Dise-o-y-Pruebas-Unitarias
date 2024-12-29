package data.data;

import data.data.GeographicPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographicPointTest {
    private GeographicPoint point1;
    private GeographicPoint point2;

    @BeforeEach
    void setUp() {
        point1 = new GeographicPoint(40.7128f, -74.0060f); // New York
        point2 = new GeographicPoint(34.0522f, -118.2437f); // Los Angeles
    }

    @Test
    void testGetLatitude() {
        assertEquals(40.7128f, point1.getLatitude());
        assertEquals(34.0522f, point2.getLatitude());
    }

    @Test
    void testGetLongitude() {
        assertEquals(-74.0060f, point1.getLongitude());
        assertEquals(-118.2437f, point2.getLongitude());
    }

    @Test
    void testEquals() {
        GeographicPoint point3 = new GeographicPoint(40.7128f, -74.0060f);
        assertEquals(point1, point3);
        assertNotEquals(point1, point2);
    }

    @Test
    void testHashCode() {
        GeographicPoint point3 = new GeographicPoint(40.7128f, -74.0060f);
        assertEquals(point1.hashCode(), point3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Geographic point {latitude='40.7128'longitude='-74.006}", point1.toString());
    }
}