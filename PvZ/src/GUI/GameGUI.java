package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameGUI extends JFrame {

    //*  Attributes  *//

    // buttons of the main menu
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Window properties
        setTitle("Plants vs Zombies");
        setSize(1080, 720);
        setLocationRelativeTo(null);

        // Background Elements
        backgroundSound = new BackgroundSound("PvZ/assets/sound/LoonBoon.wav");
        setLayout(new BorderLayout());
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");



        // Buttons
        buttonPanel = new JPanel();
        playButton = new JButton("PLAY");
        tutorialButton = new JButton("TUTORIAL");
        exitButton = new JButton("EXIT");

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(playButton);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundImage);
    }

    private void prepareActions() {
        // Exit button
        exitButton.addActionListener(e -> System.exit(0));
    }


    //*  Methods  *//


    //* Main *//
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        gui.setVisible(true);
    }
}