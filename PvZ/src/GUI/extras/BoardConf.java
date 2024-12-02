package GUI.extras;

import javax.swing.*;
import java.awt.*;

public class BoardConf extends JPanel {
    private Image backgroundImage;

    public BoardConf(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}