package GUI;

import GUI.extras.BackgroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectGUI extends JPanel {

    //** Attributes **//

    // Dimensions & information
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int menuSize = screenSize.width / 4;
    private final int buttonsWSize = screenSize.width / 5;
    private final int buttonsHSize = screenSize.height / 9;

    // Panels
    private JPanel actionPanel;
    private JPanel buttonPanel;

    // Action buttons
    protected JButton backButton;
    protected JButton continueButton;

    // Select buttons
    protected JButton pvpButton;
    protected JButton pvAIButton;
    protected JButton AIvAIButton;




    /**
     * Constructor, creates the PlayScreen GUI and its elements.
     * This screen allows the players select what kind of game they want to play.
     * 1. Player vs Player
     * 2. Player vs IA
     * 3. IA vs IA
     * Also can go back to the main menu or continue to the game settings.
     */
    public SelectGUI() {
        prepareElements();
    }

    /**
     * Prepares all elements of the PlayScreen GUI.
     */
    private void prepareElements() {
        // Set layout
        setLayout(null);

        // Background Elements
        BackgroundImage backgroundImage = new BackgroundImage("PvZ/assets/background/backSelect.png");
        backgroundImage.setBounds(0, 0, screenSize.width, screenSize.height);
        add(backgroundImage);

        // Buttons bottom panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(
                screenSize.width/2 - menuSize/2,
                screenSize.height/2 - menuSize/6,
                menuSize,
                menuSize
        );

        prepareElementsBottom();
        prepareElementsSelect();

        add(buttonPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        add(backgroundImage);
    }

    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsBottom() {
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        actionPanel.setOpaque(false);
        actionPanel.setBounds(
                screenSize.width / 2 - buttonsWSize * 5 / 2,
                screenSize.height - buttonsHSize * 2,
                screenSize.width,
                buttonsHSize + 20
        );

        // Action buttons
        backButton = new JButton("B");
        continueButton = new JButton("Let's Play!");
        backButton.setPreferredSize(new Dimension(buttonsHSize, buttonsHSize));
        continueButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        actionPanel.add(backButton);
        actionPanel.add(continueButton);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsSelect() {
        // Buttons
        pvpButton = new JButton("P1 vs P2");
        pvAIButton = new JButton("P1 vs AI");
        AIvAIButton = new JButton("AI vs AI");

        // Set buttons properties
        pvpButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        pvAIButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        AIvAIButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Set buttons position
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add buttons to the panel
        buttonPanel.add(pvpButton, gbc);
        buttonPanel.add(pvAIButton, gbc);
        buttonPanel.add(AIvAIButton, gbc);
    }
}