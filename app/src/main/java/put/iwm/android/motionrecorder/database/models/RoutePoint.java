package put.iwm.android.motionrecorder.database.models;

/**
 * Created by Szymon on 2015-04-23.
 */
public class RoutePoint {

    private int id;
    private double latitude;
    private double longitude;
    private double altitude;
    private double moveDistance;
    private long moveTime;
    private int serialNumber;
    private RoutePoint previousPoint;

    public RoutePoint() {

    }

    public RoutePoint(int id, double latitude, double longitude, double altitude, double moveDistance,
                      long moveTime, int serialNumber, RoutePoint previousPoint) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.moveDistance = moveDistance;
        this.moveTime = moveTime;
        this.serialNumber = serialNumber;
        this.previousPoint = previousPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getMoveDistance() {
        return moveDistance;
    }

    public void setMoveDistance(double moveDistance) {
        this.moveDistance = moveDistance;
    }

    public long getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(long moveTime) {
        this.moveTime = moveTime;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public RoutePoint getPreviousPoint() {
        return previousPoint;
    }

    public void setPreviousPoint(RoutePoint previousPoint) {
        this.previousPoint = previousPoint;
    }

}
