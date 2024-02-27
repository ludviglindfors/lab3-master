import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    private static ArrayList<CommonBaseCar> cars;
    private static Workshop<Volvo240> volvoWorkshop;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cars = new ArrayList<>();
        volvoWorkshop = new VolvoWorkshop(10);

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (CommonBaseCar car : cars) {
                car.move();

                if (car.getXPosition() < 0) {
                    car.setXPosition(0);
                    car.stopEngine();
                    car.setDirection((car.getDirection() + 180) % 360);
                    car.startEngine();
                }
                if (car.getXPosition() > CarView.X - 100) {
                    car.setXPosition(CarView.X - 100);
                    car.stopEngine();
                    car.setDirection((car.getDirection() + 180) % 360);
                    car.startEngine();
                }
                if (car.getYPosition() < 0) {
                    car.setYPosition(0);
                    car.stopEngine();
                    car.setDirection((car.getDirection() + 180) % 360);
                    car.startEngine();
                }
                if (car.getYPosition() > CarView.Y - 60) {
                    car.setYPosition(CarView.Y - 60);
                    car.stopEngine();
                    car.setDirection((car.getDirection() + 180) % 360);
                    car.startEngine();
                }

                int x = (int) Math.round(car.getXPosition());
                int y = (int) Math.round(car.getYPosition());

                frame.drawPanel.moveit(car, x, y);
                frame.drawPanel.repaint();

                if (car instanceof Volvo240) {
                    if (frame.drawPanel.isWithinWorkshopRadius()) {
                        volvoWorkshop.receiveCar((Volvo240) car);
                        cars.remove(car);
                        break;
                    }
                }
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (CommonBaseCar car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (CommonBaseCar car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (CommonBaseCar car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (CommonBaseCar car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void raiseFlap() {
        for (CommonBaseCar car : cars) {
            if (car instanceof Flap) {
                ((Flap) car).raiseFlap();
            }
        }
    }

    void lowerFlap() {
        for (CommonBaseCar car : cars) {
            if (car instanceof Flap) {
                ((Flap) car).lowerFlap();
            }
        }
    }

    void startEngine() {
        for (CommonBaseCar car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (CommonBaseCar car : cars) {
            car.stopEngine();
        }
    }
}