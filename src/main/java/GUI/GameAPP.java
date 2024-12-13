package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the Game GUI.
 * This class is the main class of the game, it contains all the elements of the game.
 */
public class GameAPP extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    /**
     * Constructor, creates the Game's elements and actions.
     */
    public GameAPP() {
        prepareElements();
    }

    /**
     * Prepares all elements of the Game GUI.
     * Also, determinate the properties of the application.
     * It includes the Home Screen, Select Screen, tutorial screen and game screen.
     */
    private void prepareElements() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        setTitle("POOB vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);

        JPanel panels = new JPanel(new CardLayout());
        HomeGUI home = new HomeGUI();
        SelectGUI select = new SelectGUI(this);
        TutorialGUI tutorial = new TutorialGUI();


        panels.add(home, "homePanel");
        panels.add(tutorial, "tutorialPanel");
        panels.add(select, "selectPanel");
        add(panels);
    }



    //* Main Method *//
    public static void main(String[] args) {
        new GameAPP().setVisible(true);
    }
}