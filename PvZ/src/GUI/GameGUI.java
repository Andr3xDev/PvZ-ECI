package GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class GameGUI extends JFrame {

    //*  Attributes  *//

    // buttons of the main menu
    private JPanel buttonPanel;
    private JButton playButton;
    private JButton tutorialButton;
    private JButton exitButton;



    public GameGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
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
    }

    private void prepareActions() {
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
    }


    //*  Methods  *//

    private void startSound() {
        try {
            FileInputStream is = new FileInputStream("PvZ/assets/sounds/LoonBoon.mp4");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            Clip ini = (Clip) AudioSystem.getLine(info);
            ini.open();
            ini.start();
            ini.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //* Main *//
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        gui.setVisible(true);
    }
}