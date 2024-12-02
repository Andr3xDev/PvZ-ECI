package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.RoundedButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TutorialGUI extends JPanel {

    //** Attributes **//

    // Dimensions
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Elements
    private JPanel infoPanel;
    protected RoundedButton backButton;


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
        prepareElementsGeneral();
        prepareElementsBottom();
        prepareElementsInfo();

        // Add components
        add(backgroundImage);
    }


    private void prepareElementsGeneral() {
        // Panels
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBounds(150, 100, screenSize.width-300, screenSize.height-200);
        infoPanel.setBackground(new Color(2, 0, 51, 200));
        infoPanel.setBorder(new LineBorder(new Color(2, 0, 51), 8));

        add(infoPanel);
    }

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

    private void prepareElementsInfo() {
        JPanel info = new JPanel();
        //info.setOpaque(false);
        info.setBackground(Color.WHITE);    //! Temporal until we have the info image in PNG
        infoPanel.add(info, BorderLayout.CENTER);
    }


    private void prepareActions() {
        // Back button
        backButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "homePanel");
        });
    }
}