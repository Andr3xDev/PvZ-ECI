package GUI.extras;

import javax.swing.*;
import java.awt.*;

/**
 * Class to set a background image to a JPanel and imitate the same size as the JPanel as a background image.
 */
public class BoardConf extends JPanel {
    // Attributes
    private final Image backgroundImage;


    /**
     * Constructor to set the background image to the JPanel.
     * @param imagePath The path of the image file.
     */
    public BoardConf(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }


    /**
     * Method to paint the background image on the JPanel.
     * @param g The graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}