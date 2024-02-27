import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    private HashMap<CommonBaseCar, Point> points = new HashMap<>();

    public boolean isWithinWorkshopRadius() {
        double xRadiusMax = volvoWorkshopPoint.x + 50;
        double xRadiusMin = volvoWorkshopPoint.x - 50;
        double yRadiusMax = volvoWorkshopPoint.y + 50;
        double yRadiusMin = volvoWorkshopPoint.y - 50;

        for (CommonBaseCar car : points.keySet()) {
            double carX = car.getXPosition();
            double carY = car.getYPosition();

            if (xRadiusMin <= carX && carX <= xRadiusMax && yRadiusMin <= carY && carY <= yRadiusMax) {
                return true;
            }
        }
        return false;
    }

    public void moveit(CommonBaseCar car, int x, int y) {
        points.put(car, new Point(x, y));
        repaint();
    }

    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage volvoImage;


    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CommonBaseCar car : points.keySet()) {
            Point carPoint = points.get(car);
            String modelName = car.getModelName();
            switch (modelName) {
                case ("Saab95"): {
                    g.drawImage(saabImage, carPoint.x, carPoint.y, null);
                    break;
                }
                case ("Scania"): {
                    g.drawImage(scaniaImage, carPoint.x, carPoint.y, null);
                    break;
                }
                case ("Volvo240"): {
                    g.drawImage(volvoImage, carPoint.x, carPoint.y, null);
                    break;
                }
            }
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }

    }
}