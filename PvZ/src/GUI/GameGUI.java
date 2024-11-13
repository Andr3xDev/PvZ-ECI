package GUI;

import javax.swing.*;
import java.awt.font.GlyphMetrics;

public class GameGUI extends JFrame {
    private JPanel panel;
    private JButton PLAYButton;
    private JButton OPTIONSButton;
    private JButton EXITButton;

    public GameGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        panel = new JPanel();
        PLAYButton = new JButton("PLAY");
        OPTIONSButton = new JButton("OPTIONS");
        EXITButton = new JButton("EXIT");
    }

    private void prepareActions() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Plants vs Zombies");
    }

    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        gui.setVisible(true);
    }
}