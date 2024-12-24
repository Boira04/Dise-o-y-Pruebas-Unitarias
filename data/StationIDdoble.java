package data.data;

public class StationIDdoble implements StationIDInterface {
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public GeographicPointInterface getgeoPoint() {
        return null;
    }
    public boolean equals (Object o){
        return this == o;
    }
    public int hashCode(){
        return 221;
    }
    public String toString(){
        return "Geographic point {" + "ID='" + "1" + '\'' + "geoPoint='" + "Geographic point {" + "latitude='" + "33" + '\'' +
                "longitude='" + "22" + '}' + '}';

    }
}
