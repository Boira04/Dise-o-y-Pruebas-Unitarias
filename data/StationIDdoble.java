package data.data;

public class StationIDdoble implements StationIDInterface {
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public GeographicPointInterface getgeoPoint() {
        return new GeographicPointDoble();
    }

    public boolean equals (Object o){
        StationIDInterface gP = (StationIDInterface) o;
        return 1== gP.getID();
    }
    public int hashCode(){
        return 221;
    }
    public String toString() {
        return "StationID {" +
                "ID='" + getID() + '\'' +
                ", geoPoint='" + getgeoPoint() + '\'' +
                '}';
    }
}
