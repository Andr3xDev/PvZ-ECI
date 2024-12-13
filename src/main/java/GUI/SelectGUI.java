package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.RoundedButton;
import GUI.extras.RoundedLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the Select GUI of the game. It contains the game mode selection screen.
 * This screen allows the players select what kind of game they want to play.
 * 1. Player vs Player
 * 2. Player vs IA
 * 3. IA vs IA
 * Also can go back to the main menu or continue to the game settings.
 */
public class SelectGUI extends JPanel {

    //** Attributes **//

    // Dimensions & information
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int buttonsWSize = screenSize.width / 8;
    private final int buttonsHSize = screenSize.height / 14;
    private final GameAPP game;

    // Panels
    private JPanel optionsPanel;
    private JPanel pvpPanel;
    private JPanel pvAIPanel;
    private JPanel AIvAIPanel;

    // Action buttons
    private RoundedButton backButton;
    private RoundedButton loadButton;

    // Buttons
    private RoundedButton pvpButton;
    private RoundedButton pvAIButton;
    private RoundedButton AIvAIButton;

    // Difficult buttons
    private RoundedButton easyButton1;
    private RoundedButton mediumButton1;
    private RoundedButton easyButton2;
    private RoundedButton mediumButton2;
    private RoundedButton easyButton3;
    private RoundedButton mediumButton3;

    private int difficulty1 = 0;
    private int difficulty2 = 0;
    private int difficulty3 = 0;



    /**
     * Constructor, creates the PlayScreen GUI and its elements.
     * This screen allows the players select what kind of game they want to play.
     * 1. Player vs Player
     * 2. Player vs IA
     * 3. IA vs IA
     * Also can go back to the main menu or continue to the game settings.
     */
    public SelectGUI(GameAPP game) {
        this.game = game;
        prepareElements();
        prepareActions();
    }


    /**
     * Prepares all elements of the PlayScreen GUI.
     */
    private void prepareElements() {
        // Set layout
        setLayout(null);

        // Background Elements
        BackgroundImage backgroundImage = new BackgroundImage("src/main/resources/background/backSelect.png");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);

        // Elements
        prepareElementsOptions();
        prepareElementsSelect();
        prepareElementsBack();

        // Add components
        add(optionsPanel);
        add(backgroundImage);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsOptions() {
        // General config
        optionsPanel = new JPanel(new GridLayout(1, 3, 40, 40));
        optionsPanel.setOpaque(false);
        optionsPanel.setBounds(150, (int) (screenSize.height/2.5), screenSize.width - 300, screenSize.height/3);

        // Panels
        pvpPanel = new JPanel(new FlowLayout());
        pvAIPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        AIvAIPanel = new JPanel(new BorderLayout());

        // Set panels properties
        pvpPanel.setBackground(new Color(2, 0, 51, 200));
        pvpPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));
        pvAIPanel.setBackground(new Color(2, 0, 51, 200));
        pvAIPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));
        AIvAIPanel.setBackground(new Color(2, 0, 51, 200));
        AIvAIPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Add panels to the options panel
        optionsPanel.add(pvpPanel);
        optionsPanel.add(pvAIPanel);
        optionsPanel.add(AIvAIPanel);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsSelect() {
        // Buttons
        pvpButton = new RoundedButton("P1 vs P2", 35);
        pvpButton.setBackground(new Color(203, 225, 192));
        pvAIButton = new RoundedButton("P1 vs AI", 35);
        pvAIButton.setBackground(new Color(203, 225, 192));
        AIvAIButton = new RoundedButton("AI vs AI", 35);
        AIvAIButton.setBackground(new Color(203, 225, 192));

        // Difficult buttons
        easyButton1 = new RoundedButton("Easy", 35);
        mediumButton1 = new RoundedButton("Medium", 35);
        easyButton2 = new RoundedButton("Easy", 35);
        mediumButton2 = new RoundedButton("Medium", 35);
        easyButton3 = new RoundedButton("Easy", 35);
        mediumButton3 = new RoundedButton("Medium", 35);
        // Set buttons properties
        pvpButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        pvAIButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        AIvAIButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Add buttons to the panel
        pvpPanel.add(pvpButton);

        pvAIPanel.add(pvAIButton);
        pvAIPanel.add(easyButton1);
        pvAIPanel.add(mediumButton1);

        AIvAIPanel.add(AIvAIButton, BorderLayout.NORTH);

        JPanel easyPanel = new JPanel(new GridLayout(2, 3, 0, 0));
        easyPanel.add(new RoundedLabel("Zombie:"));
        easyPanel.add(easyButton2);
        easyPanel.add(mediumButton2);
        easyPanel.add(new RoundedLabel("Plant:"));
        easyPanel.add(easyButton3);
        easyPanel.add(mediumButton3);

        AIvAIPanel.add(easyPanel, BorderLayout.CENTER);
    }


    /**
     * Prepares the elements of the bottom panel. It contains only the back button.
     */
    private void prepareElementsBack() {
        // Back panel
        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(2, 0, 51, 200));
        backPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Back button
        backButton = new RoundedButton("Back", 35);
        backButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Load button
        loadButton = new RoundedButton("Load", 35);
        loadButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Add buttons to the panel
        backPanel.setLayout(new GridLayout(1, 2, 40, 40));
        backPanel.add(backButton);
        backPanel.add(loadButton);
        backPanel.setBounds(
                screenSize.width - buttonsWSize * 5,
                screenSize.height - 2 * buttonsHSize,
                buttonsWSize * 2,
                buttonsHSize + 40
        );
        add(backPanel);
    }


    /**
     * Prepares the only action of the Tutorial GUI, it is the back button to return to main screen
     * and the game mode selectors: "P1 vs P2", "P1 vs AI" and "AI vs AI".
     */
    private void prepareActions() {

        // P1 vs P2 button
        pvpButton.addActionListener(_ -> {
            BoardGUI board = new BoardGUI(game, "pvp", 1, difficulty1);
            board.setVisible(true);
            game.dispose();
        });

        // P1 vs AI button
        pvAIButton.addActionListener(_ -> {
            if (difficulty1 != 0) {
                BoardGUI board = new BoardGUI(game, "pvAI", 1, difficulty1);
                board.setVisible(true);
                game.dispose();
            }
        });

        // AI vs AI button
        AIvAIButton.addActionListener(_ -> {
            if (difficulty2 != 0 && difficulty3 != 0) {
                BoardGUI board = new BoardGUI(game , "AIvAI", difficulty3, difficulty2);
                board.setVisible(true);
                game.dispose();
            }
        });

        easyButton1.addActionListener(_ -> setDifficulty(1));

        mediumButton1.addActionListener(_ -> setDifficulty(2));

        easyButton2.addActionListener(_ -> setDifficulty2(1));

        mediumButton2.addActionListener(_ -> setDifficulty2(2));

        easyButton3.addActionListener(_ -> setDifficulty3(1));

        mediumButton3.addActionListener(_ -> setDifficulty3(2));


        // Back button
        backButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "homePanel");
        });

        // Load button
        loadButton.addActionListener(_ -> System.out.println("Load button pressed"));
    }



    /**
     * Method to set the difficulty of the IA. only for PvAI.
     * @param difficulty The difficulty of the IA.
     */
    private void setDifficulty(int difficulty) {
        if (difficulty == 1) {
            easyButton1.setBackground(new Color(219, 255, 0, 234));
            mediumButton1.setBackground(new Color(252, 250, 227));
            difficulty1 = 1;
        } else {
            easyButton1.setBackground(new Color(252, 250, 227));
            mediumButton1.setBackground(new Color(219, 255, 0, 234));
            difficulty1 = 2;
        }
    }


    /**
     * Method to set the plants difficulty of the AIvAI.
     * @param difficulty The difficulty to set.
     */
    private void setDifficulty2(int difficulty) {
        if (difficulty == 1) {
            easyButton2.setBackground(new Color(219, 255, 0, 234));
            mediumButton2.setBackground(new Color(252, 250, 227));
            difficulty2 = 1;
        } else {
            easyButton2.setBackground(new Color(252, 250, 227));
            mediumButton2.setBackground(new Color(219, 255, 0, 234));
            difficulty2 = 2;
        }
    }


    /**
     * Method to set the zombies difficulty of the AIvAI.
     * @param difficulty The difficulty to set.
     */
    private void setDifficulty3(int difficulty) {
        if (difficulty == 1) {
            easyButton3.setBackground(new Color(219, 255, 0, 234));
            mediumButton3.setBackground(new Color(252, 250, 227));
            difficulty3 = 1;
        } else {
            easyButton3.setBackground(new Color(252, 250, 227));
            mediumButton3.setBackground(new Color(219, 255, 0, 234));
            difficulty3 = 2;
        }
    }
}