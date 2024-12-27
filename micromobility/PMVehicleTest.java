package data.micromobility;

import data.data.GeographicPoint;
import data.micromobility.PMVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PMVehicle class.
 */
public class PMVehicleTest {

    private PMVehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new PMVehicle("VehicleID123", new GeographicPoint(41.40338F, 2.17403F), PMVState.Available);
    }

    @Test
    void testInitialState() {
        assertEquals(PMVState.Available, vehicle.getState(), "Initial state should be Available.");
    }

    @Test
    void testSetNotAvailb() {
        vehicle.setNotAvailb();
        assertEquals(PMVState.NotAvailable, vehicle.getState(), "State should be NotAvailable after calling setNotAvailb.");
    }

    @Test
    void testSetUnderWay() {
        vehicle.setUnderWay();
        assertEquals(PMVState.UnderWay, vehicle.getState(), "State should be UnderWay after calling setUnderWay.");
    }

    @Test
    void testSetAvailb() {
        vehicle.setNotAvailb(); // Change to a different state first
        vehicle.setAvailb();
        assertEquals(PMVState.Available, vehicle.getState(), "State should be Available after calling setAvailb.");
    }

    @Test
    void testSetLocation() {
        GeographicPoint newLocation = new GeographicPoint(42.3601f, -71.0589f); // Example coordinates
        vehicle.setLocation(newLocation);
        assertEquals(newLocation, vehicle.getLocation(), "Location should match the new location set.");
    }

    @Test
    void testGetLocation() {
        GeographicPoint initialLocation = new GeographicPoint(0, 0);
        assertEquals(initialLocation, vehicle.getLocation(), "Initial location should match the one provided in the constructor.");
    }
}
