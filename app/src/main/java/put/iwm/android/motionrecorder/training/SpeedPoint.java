package put.iwm.android.motionrecorder.training;

/**
 * Created by Szymon on 2015-05-17.
 */
public class SpeedPoint {

    private long id;
    private double value;
    private int serialNumber;

    public SpeedPoint() {
    }

    public SpeedPoint(double value, int serialNumber) {
        this.value = value;
        this.serialNumber = serialNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
