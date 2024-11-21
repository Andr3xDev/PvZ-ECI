package GUI;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Background elements
    private BackgroundImage backgroundImage;


    public GameGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        // Window actions
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Window properties
        setTitle("Plants vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);

        // Background Elements
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);

        // Add background image
        add(backgroundImage);
    }

    private void prepareActions() {

    }
}