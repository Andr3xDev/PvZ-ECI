package GUI;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int menuSize = screenSize.width / 4;
    int buttonsWSize = screenSize.width / 5;
    int buttonsHSize = screenSize.height / 9;

    // Buttons of the main menu
    private JPanel buttonPanel;
    private JButton playButton;
    private JButton tutorialButton;
    private JButton exitButton;

    // Background elements
    private BackgroundImage backgroundImage;
    private BackgroundSound backgroundSound;



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

        // Set layout
        setLayout(new BorderLayout());

        // Background Image
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        backgroundSound = new BackgroundSound("PvZ/assets/sound/LoonBoon.wav");

        // Buttons Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(
                screenSize.width/2 - menuSize/2,
                screenSize.height/2 - menuSize/6,
                menuSize,
                menuSize
        );

        // Buttons
        playButton = new JButton("PLAY");
        tutorialButton = new JButton("TUTORIAL");
        exitButton = new JButton("EXIT");

        playButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        tutorialButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        exitButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        buttonPanel.add(playButton, gbc);
        buttonPanel.add(tutorialButton, gbc);
        buttonPanel.add(exitButton, gbc);

        // Add components
        add(buttonPanel, BorderLayout.CENTER);
        add(backgroundImage);
    }

    private void prepareActions() {
        // Play button
        playButton.addActionListener(e -> {
            PlayScreen playScreen = new PlayScreen();
            playScreen.setVisible(true);
            this.dispose();
        });

        // Tutorial button

        // Exit button
        exitButton.addActionListener(e -> System.exit(0));
    }

    // Main
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        gui.setVisible(true);
    }
}
