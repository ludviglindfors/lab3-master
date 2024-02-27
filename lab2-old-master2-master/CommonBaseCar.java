import java.awt.*;

public abstract class CommonBaseCar implements Movable {

    protected int nrDoors;          // Integer för antal dörrar på en bil.
    protected double enginePower;   // Dubbel för hur mycket kraft motorn har, alltså vad vår maxhastighet kan vara.
    protected double currentSpeed;  // Dubbel för hastigheten i det tillfället.
    protected Color color;          // Color variabel för biles färg.
    protected String modelName;     // Sträng för bilmodellen
    protected double xPosition;     // Dubbel för bilens nuvarande x-position
    protected double yPosition;     // Dubbel för bilens nuvarande y-position
    protected double direction;     // Dubbel för vilket håll bilen pekar åt i grader. (omgörs till radians i beräkningarna)

    private final static double trimFactor = 1.25; // variabel för bilens trim som används i funktionen speedFactor() (Bara för Volvon hittils)

    public double speedFactor() { // Funktion som beräknar bilens hastighetsförändring
        return enginePower * 0.01 * trimFactor;
    }


    // Konstruktor för CommonBaseCar som deklarerar alla variabler som behövs för den generella bilen.
    public CommonBaseCar(int nrDoors, Color color, double enginePower, String modelName, double xPosition, double yPosition, double direction) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;

    }

    public String getModelName(){
        return this.modelName;
    }
    public int getNrDoors() {
        return nrDoors;
    } // Funktion som returnerar antalet dörrar (Integer)

    public Color getColor() {
        return color;
    } // Funktion som returnerar bilens färg (Color)
    public void startEngine() {
        currentSpeed = 0.1;
    } // Funktion som ändrar hastigheten från 0 till 0.1
    public void stopEngine() {
        currentSpeed = 0;
    } // Funktion som ändrar hastigheten från något värde till 0

    public double getCurrentSpeed() { // Funktion som returnerar hastigheten i det tillfället (Dubbel)
        return currentSpeed;
    }

    public double getEnginePower() { // Funktion som returnerar motorns kraft i det tillfället (Dubbel)
        return enginePower;
    }

    public double getXPosition() { // Funktion som returnerar bilens nuvarande x-position (Dubbel)
        return xPosition;
    }

    public double getYPosition() { // Funktion som returnerar bilens nuvarande y-position (Dubbel)
        return yPosition;
    }

    public void setXPosition(double newXPosition) {
        this.xPosition = newXPosition;
    }

    public void setYPosition(double newYPosition) {
        this.yPosition = newYPosition;
    }

    public double getDirection() { // Funktion som returnerar bilens nuvarande färdriktning (Dubbel (Grader))
        return direction;
    }

    public void setDirection(double newDirection) {
        this.direction = newDirection;
    }

    public double incrementSpeed(double amount) { // Funktion som ökar ett fordons hastighet
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        return currentSpeed;
    }

    public double decrementSpeed(double amount) { // Funktion som minskar ett fordons hastighet
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        return currentSpeed;
    }

    private void setCurrentSpeed(double speed) { // Funktion som tar in en variabel speed som sen ändrar currentspeed till speed.
        currentSpeed = speed;
    }

    // Metod för att ändra position på bilen
    public void move() {
        double radians = Math.toRadians(direction);
        double newX = currentSpeed * Math.cos(radians);
        double newY = currentSpeed * Math.sin(radians);

        xPosition += newX;
        yPosition += newY;

    }

    // Metod för att ändra bilens hastighet, till ett maxvärde av Enginepower
    // Inputen amount måste ligga mellan [0,1]
    public void gas(double amount){
        if (amount >= 0 && amount <= 1) {
            double newSpeed = incrementSpeed(amount);
            setCurrentSpeed(Math.min(newSpeed, getEnginePower()));
        } else {
            System.err.println("Invalid gas amount");
        }
    }

    // Metod för att sänka bilens hastighet, kan inte gå under 0.
    // Inputen amount måste ligga mellan [0,1]
    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {
            double newSpeed = decrementSpeed(amount);
            setCurrentSpeed(Math.max(newSpeed, 0));
        } else {
            System.err.println("Invalid brake amount");
        }
    }

    // Metoder för att svänga vänster / höger. I modulo 360 + 360 för att direction inte ska bli negativ.
    public void turnLeft() {
        direction -= 15;
        direction = (direction + 360) % 360;
    }

    public void turnRight() {
        direction += 15;
        direction = (direction + 360) % 360;
    }
}