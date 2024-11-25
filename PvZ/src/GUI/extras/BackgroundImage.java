package GUI.extras;

import javax.swing.*;
import java.awt.*;

public class BackgroundImage extends JPanel {
    private final Image backgroundImage;

    public BackgroundImage(String fileName) {
        try {
            ImageIcon image = new ImageIcon(fileName);
            backgroundImage = image.getImage();
        } catch (Exception e) {
            throw new RuntimeException("Image not found");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth()+10, getHeight(), this);
    }
}
