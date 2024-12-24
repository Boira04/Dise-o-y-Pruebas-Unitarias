package data.data;

public class GeographicPointDoble implements GeographicPointInterface{
    @Override
    public float getLatitude() {
        return 46.7158f;
    }

    @Override
    public float getLongitude() {
        return 52.7335f;
    }
    public boolean equals (Object o){
        return this == o;
    }
    public int hashCode(){
        return 221;
    }
    public String toString(){
        return "Geographic point {" + "latitude='" + "46.7158f" + '\'' +
                "longitude='" + "52.7335f" + '}';

    }
}
