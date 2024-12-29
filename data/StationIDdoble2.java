package data.data;

public class StationIDdoble2 implements StationIDInterface {
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public GeographicPointInterface getgeoPoint() {
        return new GeographicPointDoble2();
    }

    public boolean equals (Object o){
        StationIDInterface gP = (StationIDInterface) o;
        return 2== gP.getID();
    }
    public int hashCode() {
        return 222;
    }
    public String toString() {
        return "StationID {" +
                "ID='" + getID() + '\'' +
                ", geoPoint='" + getgeoPoint() + '\'' +
                '}';
    }
}