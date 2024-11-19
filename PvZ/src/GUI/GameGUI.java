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
    // Initial Screen
    private JPanel panel;
    private JButton PLAYButton;
    private JButton OPTIONSButton;
    private JButton EXITButton;

    private CardLayout layout;
    private JPanel principal;
    private StartScreen initial;

    public GameGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        panel = new JPanel();
        PLAYButton = new JButton("PLAY");
        OPTIONSButton = new JButton("OPTIONS");
        EXITButton = new JButton("EXIT");
        setSize(new Dimension(1080, 720));
        initial = new StartScreen();
        startSound();
    }

    private void prepareActions() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Plants vs Zombies");
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