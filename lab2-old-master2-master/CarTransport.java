import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends CommonBaseCar implements Flap {

    private GeneralFlap flap;
    private double loadingRadius = 14;
    private boolean flapUp;
    private ArrayList<CommonBaseCar> carsLoaded = new ArrayList<>();
    private final int maxCars = 6;

    public CarTransport() {
        super(2, Color.blue, 220, "carTransport", 0.0, 0.0, 0.0);
        this.flap = new GeneralFlap(90);
        this.flapUp = true;
    }

    private boolean isRampUp() {
        return flapUp;
    }

    public double getFlapAngle() {
        return flap.getFlapAngle();
    }

    public void raiseFlap() {
        if (getCurrentSpeed() == 0) {
            flap.raiseToMax();
        }
    }

    public void lowerFlap() {
        if (getCurrentSpeed() == 0) {
            flap.lowerToMin();
        }
    }

    public int getLoadedCars() {
        if (carsLoaded.size() <= maxCars) {
            return carsLoaded.size();
        } else {
            return maxCars;
        }
    }

    public void loadCar(CommonBaseCar car) {
        if (car instanceof Flap) {
            if (isInVicinity(car) && !isRampUp() && getCurrentSpeed() == 0 && carsLoaded.size() < maxCars) {
                carsLoaded.add(car);
            } else {
                System.err.println("CarTransport cannot load car");
            }
        } else {
            System.err.println("CarTransport cannot load another carTransport");
        }
    }

    public void unloadCar() {
        int lastCar = carsLoaded.size() - 1;
        if (!isRampUp() && getCurrentSpeed() == 0 && !carsLoaded.isEmpty()) {
            CommonBaseCar unloadedCar = carsLoaded.remove(lastCar);
            unloadedCar.yPosition = unloadedCar.getYPosition() - 18;
        } else {
            System.err.println("Cannot unload car");
        }
    }

    public boolean isInVicinity(CommonBaseCar car) {
        double xValueVicinityMax = getXPosition() + loadingRadius;
        double xValueVicinityMin = getXPosition() - loadingRadius;

        double yValueVicinityMax = getYPosition() + loadingRadius;
        double yValueVicinityMin = getYPosition() - loadingRadius;

        if (xValueVicinityMin <= car.xPosition && car.xPosition <= xValueVicinityMax) {
            if (yValueVicinityMin <= car.yPosition && car.yPosition <= yValueVicinityMax) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}