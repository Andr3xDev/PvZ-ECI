package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class TutorialGUI extends JPanel {

    //** Attributes **//

    // Dimensions
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Panels
    private JPanel actionPanel;
    private JPanel buttonPanel;

    // Action buttons
    protected RoundedButton backButton;

    // Information
    private JLabel infoLabel;



    public TutorialGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        // Set layout
        setLayout(null);

        // Background Elements
        BackgroundImage backgroundImage = new BackgroundImage("PvZ/assets/background/backDefault.jpeg");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);
        add(backgroundImage);

        // Panels
        prepareElementsBottom();
        prepareElementsInfo();

        // Add components
        add(backgroundImage);
    }


    private void prepareElementsBottom() {
        // Back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBounds(0, screenSize.height - 100, screenSize.width, 200);

        backButton = new RoundedButton("¡Okay!", 30);
        buttonPanel.add(backButton, BorderLayout.CENTER);
        add(buttonPanel);
    }

    private void prepareElementsInfo() {
    }

    private void prepareActions() {
        backButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "homePanel");
        });
    }
}