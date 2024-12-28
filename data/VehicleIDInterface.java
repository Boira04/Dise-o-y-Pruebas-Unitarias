package data.data;

public interface VehicleIDInterface {
    public int getId();
    public StationIDInterface getStation();

    public boolean equals(Object o) ;

    public int hashCode() ;
    public String toString() ;
}
