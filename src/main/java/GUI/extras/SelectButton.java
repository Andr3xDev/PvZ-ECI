package GUI.extras;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;

/**
 * Class to set a button with an image.
 */
public class SelectButton extends JButton {

    // Attributes
    private BufferedImage image;


    /**
     * Constructor to create a button with an image.
     * @param imagePath The path of the image file.
     */
    public SelectButton(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }


    /**
     * Method to paint the button with the image.
     * @param g The graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int width = getWidth();
            int height = getHeight();
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}