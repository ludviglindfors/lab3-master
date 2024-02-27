public class GeneralFlap implements Flap {
    private double flapAngle;
    private double maxAngle;


    public GeneralFlap(double max) {
        this.flapAngle = 0;
        this.maxAngle = max;
    }

    public double getFlapAngle() {
        return flapAngle;
    }

    public void raiseFlap() {
        this.flapAngle = Math.min(flapAngle + 10, maxAngle);
    }

    public void lowerFlap() {
        this.flapAngle = Math.max(flapAngle - 10, 0);
    }

    public void raiseToMax() {
        this.flapAngle = maxAngle;
    }

    public void lowerToMin() {
        this.flapAngle = 0;
    }

    public boolean isMax() {
        return flapAngle == maxAngle;
    }
}