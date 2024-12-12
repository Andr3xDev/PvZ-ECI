package GUI.extras;

import javax.swing.*;
import java.awt.*;

/**
 * Class to set a background image to a JPanel and imitate the same size as the JPanel as a background image.
 */
public class BackgroundImage extends JPanel {

    // Attributes
    private final Image backgroundImage;


    /**
     * Constructor to set the background image to the JPanel.
     * @param fileName The path of the image file.
     */
    public BackgroundImage(String fileName) {
        try {
            ImageIcon image = new ImageIcon(fileName);
            backgroundImage = image.getImage();
        } catch (Exception e) {
            throw new RuntimeException("Image not found");
        }
    }


    /**
     * Method to paint the background image on the JPanel.
     * @param g The graphics object.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth()+10, getHeight(), this);
    }
}