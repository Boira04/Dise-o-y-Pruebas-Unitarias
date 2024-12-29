package data.micromobility;

import data.data.GeographicPoint;
import data.data.GeographicPointInterface;
import data.micromobility.PMVState;

/**
 * Internal classes involved in the use of the service.
 */
public class PMVehicle {
    // The class members
    private String vehicleID;
    private GeographicPointInterface location;
    private PMVState state;

    // Constructor
    public PMVehicle(String vehicleID, GeographicPointInterface initialLocation, PMVState initialState) {
        if (vehicleID == null || vehicleID.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty.");
        }
        if (initialLocation == null) {
            throw new IllegalArgumentException("Initial location cannot be null.");
        }
        if (initialState == null) {
            throw new IllegalArgumentException("Initial state cannot be null.");
        }

        this.vehicleID = vehicleID;
        this.location = initialLocation;
        this.state = initialState;
    }

    // Getter methods
    public String getVehicleID() {
        return vehicleID;
    }

    public GeographicPointInterface getLocation() {
        return location;
    }

    public PMVState getState() {
        return state;
    }

    // Setter methods
    public void setNotAvailb() {
        this.state = PMVState.NotAvailable;
    }

    public void setUnderWay() {
        this.state = PMVState.UnderWay;
    }

    public void setAvailb() {
        this.state = PMVState.Available;
    }

    public void setLocation(GeographicPointInterface gP) {
        if (gP == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this.location = gP;
    }

    @Override
    public String toString() {
        return "PMVehicle{" +
                "vehicleID='" + vehicleID + '\'' +
                ", location=" + location +
                ", state=" + state +
                '}';
    }
}



