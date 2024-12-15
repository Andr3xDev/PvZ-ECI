package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.RoundedButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * Class that represents the Tutorial GUI of the game. It contains basic information of the game,
 * like how to play, the rules and controls.
 */
public final class TutorialGUI extends JPanel {

    //** Attributes **//

    // Dimensions
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Elements
    private JPanel infoPanel;
    private RoundedButton backButton;



    /**
     * Constructor, creates the Tutorial GUI and its elements.
     */
    public TutorialGUI() {
        prepareElements();
        prepareActions();
    }


    /**
     * Prepares all elements of the Tutorial GUI, the background and the elements are added here.
     */
    private void prepareElements() {
        // Set layout
        setLayout(null);

        // Background Elements
        BackgroundImage backgroundImage = new BackgroundImage("src/main/resources/background/backDefault.jpeg");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);
        add(backgroundImage);

        // Panels
        prepareElementsGeneral();
        prepareElementsBottom();
        prepareElementsInfo();

        // Add components
        add(backgroundImage);
    }


    /**
     * Prepares the general panel of the Tutorial GUI.
     * This panel contains the information of the tutorial and the exit button.
     */
    private void prepareElementsGeneral() {
        // Panels
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBounds(150, 100, screenSize.width-300, screenSize.height-200);
        infoPanel.setBackground(new Color(2, 0, 51, 200));
        infoPanel.setBorder(new LineBorder(new Color(2, 0, 51), 8));

        add(infoPanel);
    }


    /**
     * Prepares the bottom panel of the Tutorial GUI.
     * This panel contains the back button to return to the main menu.
     */
    private void prepareElementsBottom() {
        // Button panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, screenSize.height-100, screenSize.width, 200);
        bottomPanel.setOpaque(false);

        // Back button
        backButton = new RoundedButton("¡Okay!", 30);
        backButton.setPreferredSize(new Dimension(300, 100));

        // Add components
        bottomPanel.add(backButton);
        infoPanel.add(bottomPanel, BorderLayout.SOUTH);
    }


    /**
     * Prepares the info panel of the Tutorial GUI.
     * This panel contains the information of the tutorial.
     */
    private void prepareElementsInfo() {
        BackgroundImage info = new BackgroundImage("src/main/resources/background/tutorial.jpg");
        info.setOpaque(false);
        info.setBorder(new LineBorder(new Color(2, 0, 51), 10));
        infoPanel.add(info, BorderLayout.CENTER);
    }


    /**
     * Prepares the only action of the Tutorial GUI, it is the back button to return to main screen.
     */
    private void prepareActions() {
        // Back button
        backButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "homePanel");
        });
    }
}