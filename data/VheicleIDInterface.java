package data.data;

import java.util.Objects;

public interface VheicleIDInterface {
    public int getId();
    public StationIDInterface getStation();

    public boolean equals(Object o) ;

    public int hashCode() ;
    public String toString() ;
}
