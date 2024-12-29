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
        GeographicPointInterface gP = (GeographicPointInterface) o;
        return ((46.7158f == gP.getLatitude()) && (52.7335f == gP.getLongitude()));
    }
    public int hashCode(){
        return 221;
    }
    public String toString() {
        return "Geographic point {" +
                "latitude='" + getLatitude() + '\'' +
                ", longitude='" + getLongitude() + '\'' +
                '}';
    }
}
