package data.data;

public class GeographicPointDoble2 implements GeographicPointInterface {
    @Override
    public float getLatitude() {
        return 44.1234f; // Latitud diferent
    }

    @Override
    public float getLongitude() {
        return 55.5678f; // Longitud diferent
    }

    public boolean equals (Object o){
        GeographicPointInterface gP = (GeographicPointInterface) o;
        return ((44.1234f == gP.getLatitude()) && (55.5678f == gP.getLongitude()));
    }
    public int hashCode() {
        return 223; // Diferent de l'altre
    }

    public String toString() {
        return "Geographic point {" +
                "latitude='" + getLatitude() + '\'' +
                ", longitude='" + getLongitude() + '\'' +
                '}';
    }
}
