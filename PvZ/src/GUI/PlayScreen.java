package GUI;

import javax.swing.*;
import java.awt.*;

public class PlayScreen extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int menuSize = screenSize.width / 4;
    int buttonsWSize = screenSize.width / 5;
    int buttonsHSize = screenSize.height / 12;

    // Background elements
    private BackgroundImage backgroundImage;

    // Panels
    private JPanel actionPanel;
    private JPanel selectPanel;

    // Action buttons
    private JButton backButton;
    private JButton continueButton;

    // Select buttons
    private JButton Select1;
    private JButton Select2;
    private JButton Select3;
    private JButton Select4;
    private JButton Select5;
    private JButton Select6;




    public PlayScreen() {
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
        setLayout(null);

        // Background Elements
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);

        // Action Panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        actionPanel.setOpaque(false);
        actionPanel.setBounds(
                screenSize.width / 2 - buttonsWSize * 5/2,
                screenSize.height - buttonsHSize * 2,
                screenSize.width,
                buttonsHSize + 20
        );

        // Action buttons
        backButton = new JButton("Back");
        continueButton = new JButton("Let's Play!");
        backButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        continueButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        actionPanel.add(backButton);
        actionPanel.add(continueButton);

        // Select Panel
        selectPanel = new JPanel();

        // Select buttons

        // Add elements to the frame
        add(actionPanel);
        add(backgroundImage);

    }

    private void prepareActions() {
        backButton.addActionListener(_ -> {
            homeGUI gui = new homeGUI();
            gui.setVisible(true);
            dispose();
        });

        continueButton.addActionListener(_ -> {
            GameGUI gameGUI = new GameGUI();
            gameGUI.setVisible(true);
            dispose();
        });
    }
}