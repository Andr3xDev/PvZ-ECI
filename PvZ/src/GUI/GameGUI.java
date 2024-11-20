package GUI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
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

        // General properties
        startSound();
        setLayout(new BorderLayout());
        try {
            backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    private void startSound() {
        try {
            File audioFile = new File("PvZ/assets/sound/LoonBoon.mp4");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //* Main *//
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        gui.setVisible(true);
    }
}