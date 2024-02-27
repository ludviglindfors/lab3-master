import java.awt.*;

public class Scania extends CommonBaseCar implements Flap {

    private GeneralFlap flap;
    private int flapAngle;
    private boolean currentlyMoving;

    public Scania() {
        super(2, Color.blue, 220, "Scania", 0.0, 0.0, 0.0);
        this.flap = new GeneralFlap(70);
        this.currentlyMoving = false;
    }

    public void setFlapAngle(int angle) {
        this.flapAngle = angle;
    }

    public double getFlapAngle() {
        return flap.getFlapAngle();
    }

    public void raiseFlap() {
        if (currentSpeed == 0) {
            flap.raiseFlap();
            this.enginePower = 0;
        }
    }

    public void lowerFlap() {
        if (currentSpeed == 0) {
            flap.lowerFlap();
            this.enginePower = 220;
        }
    }

    private boolean isFlapDown() {
        return flap.getFlapAngle() == 0;
    }
}