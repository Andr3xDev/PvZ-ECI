package GUI;

import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();



    /**
     * Constructor, creates the Game's elements and actions.
     */
    public BoardGUI() {
        prepareElements();
    }


    /**
     * Prepares all elements of the Game GUI.
     */
    private void prepareElements() {
        // Window actions
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Window properties
        setTitle("Plants vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);


    }
}