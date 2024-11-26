package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the Game GUI.
 * This class is the main class of the game, it contains all the elements of the game.
 * Also controls the transitions between the different screens of the game,
 * it includes the Home Screen, Select Screen, tutorial screen and game screen.
 */
public class GameGUI extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Panel
    private JPanel panels;
    private CardLayout cl;

    private HomeGUI home;
    private SelectGUI select;
    private GameGUI game;
    private TutorialGUI tutorial;


    /**
     * Constructor, creates the Game's elements and actions.
     */
    public GameGUI() {
        prepareElements();
        prepareActions();
    }

    /**
     * Prepares all elements of the Game GUI.
     * Also, determinate the properties of the application.
     * It includes the Home Screen, Select Screen, tutorial screen and game screen.
     */
    private void prepareElements() {
        // Window actions
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Window properties
        setTitle("Plants vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);

        // panels
        panels = new JPanel(new CardLayout());
        home = new HomeGUI();
        select = new SelectGUI();

        // Add Cart Interfaces

        panels.add(home, "homePanel");          // Initial menu
        //panels.add(tutorial, "tutorialPanel");  // Tutorial screen
        panels.add(select, "selectPanel");      // Select screen
        cl = (CardLayout) panels.getLayout();

        // Add panels to the frame
        add(panels);
    }

    /**
     * Prepares the actions of the GUI.
     * It includes the actions of the Home Screen, Select Screen, tutorial screen and game screen.
     */
    private void prepareActions() {
        prepareActionsHome();
        prepareActionsSelect();
    }


    /**
     * Prepares the actions of the Home Screen.
     * This screen allows the players to start the game or go to the tutorial.
     * The exit button is not implemented here, but it closes the game.
     */
    private void prepareActionsHome() {
        home.playButton.addActionListener(_ -> cl.show(panels, "selectPanel"));
        home.tutorialButton.addActionListener(_ -> cl.show(panels, "tutorialPanel"));
    }


    /**
     * Prepares the actions of the Select Screen.
     * This screen allows the players select what kind of game they want to play.
     * Then start the game or go back to the main menu.
     */
    private void prepareActionsSelect() {
        select.backButton.addActionListener(_ -> cl.show(panels, "homePanel"));
        //! missing continue button
        //select.continueButton.addActionListener(_ -> cl.show(panels, "gamePanel"));
        select.continueButton.addActionListener(_ -> {
            BoardGUI board = new BoardGUI();
            board.setVisible(true);
            this.dispose();
        });
    }



    //* Main Method *//
    public static void main(String[] args) {
        new GameGUI().setVisible(true);
    }
}