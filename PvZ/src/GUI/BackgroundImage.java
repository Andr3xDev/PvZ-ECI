package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BackgroundImage extends JPanel {
    private final Image backgroundImage;

    public BackgroundImage(String fileName) throws IOException {
        ImageIcon image = new ImageIcon(fileName);
        backgroundImage = image.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
