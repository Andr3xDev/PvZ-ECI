package GUI.extras;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectButton extends JButton {
    private BufferedImage image;

    // Constructor
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int ancho = getWidth();
            int alto = getHeight();
            g.drawImage(image, 0, 0, ancho, alto, this);
        }
    }
}