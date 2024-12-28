package data.data;

public class VehicleIDdoble implements VehicleIDInterface {
    @Override
    public int getId() {
        return 3;
    }

    @Override
    public StationIDInterface getStation() {
        return null;
    }
    public boolean equals (Object o){
        return this == o;
    }
    public int hashCode(){
        return 251;
    }
    public String toString(){
        return "data.data.VehicleID{" +
                "id=" + "3" +
                ", station=" + "Geographic point {" + "ID='" + "1" + '\'' + "geoPoint='" + "Geographic point {" + "latitude='" + "33" + '\'' +
                "longitude='" + "22" + '}' + '}' +
                '}';

    }
}
