import java.awt.*;

public abstract class Truck extends CommonBaseCar implements Flap {
    public Truck(int nrDoors, Color color, double enginePower, String modelName,
                 double xPosition, double yPosition, double direction) {
        super(nrDoors, color, enginePower, modelName, xPosition, yPosition, direction);
    }

    public void operateRamp(boolean raise) {
        if (getCurrentSpeed() == 0) {
            if (raise) {
                raiseFlap();
            } else {
                lowerFlap();
            }
        }
    }

    public abstract void raiseFlap();

    public abstract void lowerFlap();
}

