import java.util.Objects;

/**
 * Classe immutable que representa un identificador de vehicle.
 * Accepta qualsevol objecte que implementi la interfície LocationProvider.
 */
public final class VehicleID {
    private final int id;
    private final StationIDInterface station;

    public VehicleID(int id, StationIDInterface locationProvider) {
        if (locationProvider == null) {
            throw new IllegalArgumentException("La ubicació no pot ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID del vehicle ha de ser un nombre positiu.");
        }
        this.id = id;
        this.station = locationProvider;
    }

    public int getId() {
        return id;
    }

    public StationIDInterface getStation() {
        return station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleID vehicleID = (VehicleID) o;
        return id == vehicleID.id && station.equals(vehicleID.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, station);
    }

    @Override
    public String toString() {
        return "VehicleID{" +
                "id=" + id +
                ", location=" + station.toString() +
                '}';
    }
}
