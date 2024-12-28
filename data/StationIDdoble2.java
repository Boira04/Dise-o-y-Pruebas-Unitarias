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

    public boolean equals(Object o) {
        return this == o;
    }

    public int hashCode() {
        return 222;
    }

    public String toString() {
        return "Geographic point {" + "ID='" + "2" + '\'' + "geoPoint='" + "Geographic point {" + "latitude='" + "44" + '\'' +
                "longitude='" + "55" + '}' + '}';
    }
}
