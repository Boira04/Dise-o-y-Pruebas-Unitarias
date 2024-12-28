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

    public boolean equals(Object o) {
        return this == o;
    }

    public int hashCode() {
        return 223; // Diferent de l'altre
    }

    public String toString() {
        return "Geographic point {" + "latitude='" + "44.1234f" + '\'' +
                "longitude='" + "55.5678f" + '}';
    }
}
