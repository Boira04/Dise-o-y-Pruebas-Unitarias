public class StationID {
    private final int ID;
    private final GeographicPoint geoPoint;

    public StationID(int ID, GeographicPoint geoPoint){
        this.ID = ID;
        this.geoPoint = geoPoint;
    }
    public int getID(){return ID;}
    public GeographicPoint getgeoPoint(){return geoPoint;}
    public boolean equals (Object o) {
        boolean eq;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationID gP = (StationID) o;
        eq = ((ID == gP.ID) && (GeographicPoint == gP.GeographicPoint));
        return eq;
    }
    public int hashCode() {
        int result = Integer.hashCode(ID);
        result = 31 * result + (geoPoint != null ? geoPoint.hashCode() : 0);
        return result;
    }
    public String toString(){
        return "Geographic point {" + "ID='" + ID + '\'' + "geoPoint='" + geoPoint.toString() + '}';
    }
}
