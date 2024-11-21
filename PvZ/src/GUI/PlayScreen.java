package GUI;

import javax.swing.*;
import java.awt.*;

public class PlayScreen extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int buttonsWSize = screenSize.width / 5;
    int buttonsHSize = screenSize.height / 9;

    // Background elements
    private BackgroundImage backgroundImage;

    // Panels
    private JPanel actionPanel;
    private JPanel selectPanel;
    private JTextArea textoArea;

    // Action buttons
    private JButton backButton;
    private JButton continueButton;

    // Select buttons
    private JButton Select1;
    private JButton Select2;
    private JButton Select3;




    /**
     * Constructor, creates the PlayScreen GUI and its elements.
     * This screen allows the players select what kind of game they want to play.
     * 1. Player vs Player
     * 2. Player vs IA
     * 3. IA vs IA
     * Also can go back to the main menu or continue to the game settings.
     */
    public PlayScreen() {
        prepareElements();
        prepareActions();
    }

    /**
     * Prepares all elements of the PlayScreen GUI.
     */
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

        // Buttons bottom panel
        prepareElementsBottom();

        // Select Panel
        prepareElementsSelect();

        // Add elements to the frame
        add(backgroundImage);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsBottom() {

        // Action panel
        //! Missing Organize the buttons in a better way
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        actionPanel.setOpaque(false);
        actionPanel.setBounds(
                screenSize.width / 2 - buttonsWSize * 5/2,
                screenSize.height - buttonsHSize * 2,
                screenSize.width,
                buttonsHSize + 20
        );

        // Action buttons
        backButton = new JButton("B");
        continueButton = new JButton("Let's Play!");
        backButton.setPreferredSize(new Dimension(buttonsHSize, buttonsHSize));
        continueButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // add buttons to the main panel screen
        actionPanel.add(backButton);
        actionPanel.add(continueButton);

        // Add the action panel to the frame
        add(actionPanel);
    }


    /**
     * Prepares the elements of the select panel. It contains the buttons to select the game mode.
     * Also, it shows a text area with the description of the game mode.
     */
    private void prepareElementsSelect() {

        // Select panel
        selectPanel = new JPanel();
        selectPanel.setLayout(new GridLayout(3, 1, 10, 10));
        selectPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        selectPanel.setOpaque(false);

        // Select buttons
        Select1 = new JButton("P1 vs P2");
        Select2 = new JButton("P1 vs IA");
        Select3 = new JButton("AI vs AI");

        Select1.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        Select2.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        Select3.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Add buttons to the select panel
        selectPanel.add(Select1);
        selectPanel.add(Select2);
        selectPanel.add(Select3);

        // Text Area
        textoArea = new JTextArea("Select an option to play the game.");
        textoArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textoArea.setLineWrap(true);
        textoArea.setWrapStyleWord(true);
        textoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textoArea);

        // Div panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, selectPanel, scrollPane);
        splitPane.setDividerLocation(200);
        splitPane.setResizeWeight(0.3); // Prioridad de redimensionado (30% botones, 70% texto)

        // Position of the split panel
        splitPane.setBounds(
                screenSize.width / 2 - buttonsWSize,
                screenSize.height / 2 - buttonsHSize * 2,
                screenSize.width / 2,
                buttonsHSize * 3
        );

        // Add the select panel to the frame
        add(splitPane);
    }

    private void prepareActions() {
        prepareActionsBottom();
        prepareActionsSplitPanel();
    }

    private void prepareActionsBottom(){
        backButton.addActionListener(_ -> {
            homeGUI gui = new homeGUI();
            gui.setVisible(true);
            dispose();
        });

        continueButton.addActionListener(_ -> {
            //! Missing set the initial configuration of the game
            GameGUI gameGUI = new GameGUI();
            gameGUI.setVisible(true);
            dispose();
        });
    }

    private void prepareActionsSplitPanel(){
        Select1.addActionListener(e -> textoArea.setText("P1 vs P2"));
        Select2.addActionListener(e -> textoArea.setText("P1 vs AI"));
        Select3.addActionListener(e -> textoArea.setText("AI vs AI"));
    }
}