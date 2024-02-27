import java.awt.*;

public class Saab95 extends SmallCar{

    private boolean turboOn = false; // Attribut boolean specifik för Saaben som används i speedFactor.

    public Saab95() { // Konstruktor för Saab som definerar startvärden och konstanta värden för en Saab95.
        super(2, Color.red, 125, "Saab95", 0.0, 160, 0.0);
    } // Konstruktorn används för att initialisera objekt och utföra nödvändiga förberedelser
      // när en instans (objekt) av en klass skapas.

    @Override // Funktion som beräknar bilens hastighetsförändring, overidar den i CommonBaseCar.
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    public boolean setTurboOn() {
        return turboOn = true;
    }
    public boolean setTurboOff() {
        return turboOn = false;
    }
}
