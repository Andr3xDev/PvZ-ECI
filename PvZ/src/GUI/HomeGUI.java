package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.BackgroundSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JPanel {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int menuSize = screenSize.width / 4;
    private int buttonsWSize = screenSize.width / 5;
    private int buttonsHSize = screenSize.height / 9;

    // Buttons of the main menu
    private JPanel buttonPanel;
    protected JButton playButton;
    protected JButton tutorialButton;
    private JButton exitButton;

    // Background elements
    private BackgroundImage backgroundImage;
    private BackgroundSound backgroundSound;



    public HomeGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {

        // Set layout
        setLayout(new BorderLayout());

        // Background Elements
        //backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        backgroundImage = new BackgroundImage("PvZ/assets/background/backHome.png");
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

        prepareElementsButtons();

        // Add components
        add(buttonPanel, BorderLayout.CENTER);
        add(backgroundImage);
    }

    private void prepareElementsButtons() {
        // Buttons
        playButton = new JButton("PLAY");
        tutorialButton = new JButton("TUTORIAL");
        exitButton = new JButton("EXIT");

        // Set buttons properties
        playButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        tutorialButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        exitButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Set buttons images
        ImageIcon buttonPlay = new ImageIcon("PvZ/assets/button_play.png");
        ImageIcon buttonTutorial = new ImageIcon("ruta/de/tu/imagen.png");
        ImageIcon buttonExit = new ImageIcon("ruta/de/tu/imagen.png");

        playButton.setIcon(buttonPlay);


        // Set buttons possition
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
     * Prepares the actions of the buttons.
     * Only those buttons that doesn't change screen.
     */
    private void prepareActions() {
        // Exit button
        exitButton.addActionListener(_ -> System.exit(0));
    }
}