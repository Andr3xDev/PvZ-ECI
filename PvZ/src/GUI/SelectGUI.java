package GUI;

import GUI.extras.BackgroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectGUI extends JPanel {

    //** Attributes **//

    // Dimensions & information
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int buttonsWSize = screenSize.width / 5;
    private final int buttonsHSize = screenSize.height / 9;
    private int gameMode;

    // Background elements
    private BackgroundImage backgroundImage;

    // Panels
    private JPanel actionPanel;
    private JPanel selectPanel;
    private JPanel buttonPanel;
    private JPanel descriptionPanel;

    // Action buttons
    protected JButton backButton;
    protected JButton continueButton;

    // Select buttons
    protected JButton pvpButton;
    protected JButton pvAIButton;
    protected JButton AIvAIButton;




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
        prepareActions();
    }

    /**
     * Prepares all elements of the PlayScreen GUI.
     */
    private void prepareElements() {
        this.gameMode = 0;
        // Set layout
        setLayout(null);

        // Background Elements
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);
        add(backgroundImage);

        // Buttons bottom panel
        prepareElementsBottom();

        // Select Panel
        prepareElementsSelect();

        backgroundImage.setLayout(null);
        backgroundImage.add(actionPanel);
        backgroundImage.add(selectPanel);
    }

    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsBottom() {
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        actionPanel.setOpaque(false);
        actionPanel.setBounds(
                screenSize.width / 2 - buttonsWSize * 5 / 2,
                screenSize.height - buttonsHSize * 2,
                screenSize.width,
                buttonsHSize + 20
        );

        // Action buttons
        backButton = new JButton("B");
        continueButton = new JButton("Let's Play!");
        backButton.setPreferredSize(new Dimension(buttonsHSize, buttonsHSize));
        continueButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        actionPanel.add(backButton);
        actionPanel.add(continueButton);
    }

    /**
     * Prepares the elements of the select panel.
     */
    private void prepareElementsSelect() {
        selectPanel = new JPanel(new BorderLayout());
        selectPanel.setOpaque(false);
        selectPanel.setBounds(
                screenSize.width / 4,
                screenSize.height / 4,
                screenSize.width / 2,
                screenSize.height / 2
        );

        // Buttons panel
        buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);

        // Add buttons
        pvpButton = new JButton("P1 vs P2");
        pvAIButton = new JButton("P1 vs Bot");
        AIvAIButton = new JButton("Bot vs Bot");
        buttonPanel.add(pvpButton);
        buttonPanel.add(pvAIButton);
        buttonPanel.add(AIvAIButton);

        // Description panel
        descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new FlowLayout());

        // Add components to selectPanel
        selectPanel.add(buttonPanel, BorderLayout.WEST);
        selectPanel.add(descriptionPanel, BorderLayout.CENTER);
    }

    /**
     * Prepares the actions of the buttons.
     * It allows the player select the game mode and see the description of the game mode.
     * Then save the game mode to continue to the game settings.
     */
    private void prepareActions() {
        pvpButton.addActionListener(setGameMode(0));
        pvAIButton.addActionListener(setGameMode(1));
        AIvAIButton.addActionListener(setGameMode(2));
    }


    /**
     * Set the game mode to the configuration in the game creation.
     *
     * @param mode the game mode. 0 for P1 vs P2, 1 for P1 vs Bot, 2 for Bot vs Bot.
     * @return the action listener.
     */
    private ActionListener setGameMode(int mode) {
        this.gameMode = mode;
        return null;
    }

    /**
     * Get the game mode to config the game.
     * @return the game mode. 0 for P1 vs P2, 1 for P1 vs Bot, 2 for Bot vs Bot.
     */
    public int getGameMode() {
        return this.gameMode;
    }
}