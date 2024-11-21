package GUI;

import javax.swing.*;
import java.awt.*;

public class SelectGUI extends JPanel {

    //** Attributes **//

    // Dimensions
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int buttonsWSize = screenSize.width / 5;
    private int buttonsHSize = screenSize.height / 9;

    // Background elements
    private BackgroundImage backgroundImage;

    // Panels
    private JPanel actionPanel;
    private JPanel selectPanel;

    // Action buttons
    protected JButton backButton;
    protected JButton continueButton;

    // Select buttons
    private JButton Select1;
    private JButton Select2;
    private JButton Select3;




    /**
     * Constructor, creates the PlayScreen GUI and its elements.
     * This screen allows the players select what kind of game they want to play.
     * 1. Player vs Player
     * 2. Player vs IA
     * 3. IA vs IA
     * Also can go back to the main menu or continue to the game settings.
     */
    public SelectGUI() {
        prepareElements();
    }

    /**
     * Prepares all elements of the PlayScreen GUI.
     */
    private void prepareElements() {

        // Set layout
        setLayout(null);

        // Background Elements
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);

        // Buttons bottom panel
        prepareElementsBottom();

        // Select Panel
        prepareElementsSelect();

        // Add elements to the frame
        add(backgroundImage);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsBottom() {

        // Action panel
        //! Missing Organize the buttons in a better way
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        actionPanel.setOpaque(false);
        actionPanel.setBounds(
                screenSize.width / 2 - buttonsWSize * 5/2,
                screenSize.height - buttonsHSize * 2,
                screenSize.width,
                buttonsHSize + 20
        );

        // Action buttons
        backButton = new JButton("B");
        continueButton = new JButton("Let's Play!");
        backButton.setPreferredSize(new Dimension(buttonsHSize, buttonsHSize));
        continueButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // add buttons to the main panel screen
        actionPanel.add(backButton);
        actionPanel.add(continueButton);

        // Add the action panel to the frame
        add(actionPanel);
    }


    /**
     * Prepares the elements of the select panel. It contains the buttons to select the game mode.
     * Also, it shows a text area with the description of the game mode.
     */
    private void prepareElementsSelect() {

    }

}