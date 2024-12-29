package data.data;

public class VehicleIDdoble implements VehicleIDInterface {
    @Override
    public int getId() {
        return 3;
    }

    @Override
    public StationIDInterface getStation() {
        return new StationIDdoble();
    }
    public boolean equals (Object o){
        return this == o;
    }
    public int hashCode(){
        return 251;
    }
    public String toString() {
        return "data.data.VehicleID{" +
                "id=" + getId() +
                ", station=" + getStation() +
                '}';
    }
}
