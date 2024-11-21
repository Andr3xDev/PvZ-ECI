package GUI;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Panel
    private JPanel panels;
    private CardLayout cl;
    private HomeGUI home;
    private SelectGUI select;
    private GameGUI game;


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

        // panels
        panels = new JPanel(new CardLayout());
        home = new HomeGUI();
        select = new SelectGUI();

        // Add Cart Interfaces

        panels.add(home, "homePanel");
        panels.add(select, "selectPanel");
        cl = (CardLayout) panels.getLayout();

        // Add panels to the frame
        add(panels);
    }

    private void prepareActions() {
        prepareActionsHome();
        prepareActionsSelect();
    }

    private void prepareActionsHome() {
        home.playButton.addActionListener(_ -> {
            cl.show(panels, "selectPanel");
        });
    }

    private void prepareActionsSelect() {
        select.backButton.addActionListener(_ -> {
            cl.show(panels, "homePanel");
        });
    }

    public static void main(String[] args) {
        new GameGUI().setVisible(true);
    }
}