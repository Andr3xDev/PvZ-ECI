package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.BackgroundSound;
import GUI.extras.RoundedButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * Class that represents the Home GUI of the game. It contains the main menu of the game.
 * This is the first screen that the player sees when the game starts.
 */
public class HomeGUI extends JPanel {

    //** Attributes **//

    // Dimensions
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int menuSize = screenSize.width / 4;
    private final int buttonsWSize = screenSize.width / 5;
    private final int buttonsHSize = screenSize.height / 9;

    // Buttons of the main menu
    private JPanel buttonPanel;
    protected JButton playButton;
    protected JButton tutorialButton;
    private JButton exitButton;


    /**
     * Constructor, creates the main menu GUI and its elements calling the prepare and action methods.
     */
    public HomeGUI() {
        prepareElements();
        prepareActions();
    }

    /**
     * Prepares all elements of the main menu GUI, also adds the background image, music and properties.
     */
    private void prepareElements() {

        // Set layout
        setLayout(new BorderLayout());

        // Background Elements
        BackgroundImage backgroundImage = new BackgroundImage("PvZ/assets/background/backHome.png");
        new BackgroundSound("PvZ/assets/sound/LoonBoon.wav");

        // buttons panel
        prepareElementsButtonsPanel();
        prepareElementsButtons();

        // Add components
        add(buttonPanel, BorderLayout.CENTER);
        add(backgroundImage);
    }

    /**
     * Prepares the buttons panel of the main menu. It is a JPanel with a GridBagLayout that contains the buttons.
     */
    private void prepareElementsButtonsPanel() {
        // Buttons Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBounds(
                screenSize.width/2 - menuSize/2,
                screenSize.height/2 - menuSize/6,
                menuSize,
                menuSize
        );
        buttonPanel.setBackground(new Color(2, 0, 51, 200));
        buttonPanel.setBorder(new LineBorder(new Color(2, 0, 51), 8));
    }

    /**
     * Prepares the buttons of the main menu, these are generated with the RoundedButton
     * class and added to the buttonPanel.
     */
    private void prepareElementsButtons() {
        // Buttons
        playButton = new RoundedButton("PLAY", 20);
        tutorialButton = new RoundedButton("TUTORIAL", 20);
        exitButton = new RoundedButton("EXIT", 20);

        // Set buttons properties
        playButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        tutorialButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        exitButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Set buttons position
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add buttons to the panel
        buttonPanel.add(playButton, gbc);
        buttonPanel.add(tutorialButton, gbc);
        buttonPanel.add(exitButton, gbc);
    }

    /**
     * Prepares the actions of the buttons of the menu,
     * calling the CardLayout of the main frame allowing to change the panel.
     */
    private void prepareActions() {
        // Play button
        playButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "selectPanel");
        });

        // Tutorial button
        tutorialButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "tutorialPanel");
        });

        // Exit button
        exitButton.addActionListener(_ -> System.exit(0));
    }
}