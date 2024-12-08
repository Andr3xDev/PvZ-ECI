package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.RoundedButton;
import domain.Game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class SelectGUI extends JPanel {

    //** Attributes **//

    // Dimensions & information
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int buttonsWSize = screenSize.width / 8;
    private final int buttonsHSize = screenSize.height / 14;
    private final GameAPP game;

    // Panels
    private JPanel optionsPanel;
    private JPanel pvpPanel;
    private JPanel pvAIPanel;
    private JPanel AIvAIPanel;

    // Action buttons
    protected RoundedButton backButton;
    protected RoundedButton loadButton;

    // Buttons
    protected RoundedButton pvpButton;
    protected RoundedButton pvAIButton;
    protected RoundedButton AIvAIButton;



    /**
     * Constructor, creates the PlayScreen GUI and its elements.
     * This screen allows the players select what kind of game they want to play.
     * 1. Player vs Player
     * 2. Player vs IA
     * 3. IA vs IA
     * Also can go back to the main menu or continue to the game settings.
     */
    public SelectGUI(GameAPP game) {
        this.game = game;
        prepareElements();
        prepareActions();
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

        // Elements
        prepareElementsOptions();
        prepareElementsSelect();
        prepareElementsBack();

        // Add components
        add(optionsPanel);
        add(backgroundImage);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsOptions() {
        // General config
        optionsPanel = new JPanel(new GridLayout(1, 3, 40, 40));
        optionsPanel.setOpaque(false);
        optionsPanel.setBounds(150, (int) (screenSize.height/2.5), screenSize.width - 300, screenSize.height/3);

        // Panels
        pvpPanel = new JPanel();
        pvAIPanel = new JPanel();
        AIvAIPanel = new JPanel();

        // Set panels properties
        pvpPanel.setBackground(new Color(2, 0, 51, 200));
        pvpPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));
        pvAIPanel.setBackground(new Color(2, 0, 51, 200));
        pvAIPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));
        AIvAIPanel.setBackground(new Color(2, 0, 51, 200));
        AIvAIPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Add panels to the options panel
        optionsPanel.add(pvpPanel);
        optionsPanel.add(pvAIPanel);
        optionsPanel.add(AIvAIPanel);
    }


    /**
     * Prepares the elements of the bottom panel. It contains the back and continue buttons.
     */
    private void prepareElementsSelect() {
        // Buttons
        pvpButton = new RoundedButton("P1 vs P2", 35);
        pvAIButton = new RoundedButton("P1 vs AI", 35);
        AIvAIButton = new RoundedButton("AI vs AI", 35);

        // Set buttons properties
        pvpButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        pvAIButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));
        AIvAIButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Add buttons to the panel
        pvpPanel.add(pvpButton);
        pvAIPanel.add(pvAIButton);
        AIvAIPanel.add(AIvAIButton);
    }


    /**
     * Prepares the elements of the bottom panel. It contains only the back button.
     */
    private void prepareElementsBack() {
        // Back panel
        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(2, 0, 51, 200));
        backPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Back button
        backButton = new RoundedButton("Back", 35);
        backButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Load button
        loadButton = new RoundedButton("Load", 35);
        loadButton.setPreferredSize(new Dimension(buttonsWSize, buttonsHSize));

        // Add buttons to the panel
        backPanel.setLayout(new GridLayout(1, 2, 40, 40));
        backPanel.add(backButton);
        backPanel.add(loadButton);
        backPanel.setBounds(
                screenSize.width - buttonsWSize * 5,
                screenSize.height - 2 * buttonsHSize,
                buttonsWSize * 2,
                buttonsHSize + 40
        );
        add(backPanel);
    }


    /**
     * Prepares the only action of the Tutorial GUI, it is the back button to return to main screen
     * and the game mode selectors: "P1 vs P2", "P1 vs AI" and "AI vs AI".
     */
    private void prepareActions() {

        //! All buttons are creating the same game. Just for testing purposes.
        //! Change this to create different games when ConfigGUI is implemented.

        // P1 vs P2 button
        pvpButton.addActionListener(_ -> {
            BoardGUI board = new BoardGUI(game);
            board.setVisible(true);
            game.dispose();
        });

        // P1 vs AI button
        pvAIButton.addActionListener(_ -> {
            BoardGUI board = new BoardGUI(game);
            board.setVisible(true);
            game.dispose();
        });

        // AI vs AI button
        AIvAIButton.addActionListener(_ -> {
            BoardGUI board = new BoardGUI(game);
            board.setVisible(true);
            game.dispose();
        });

        // Back button
        backButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "homePanel");
        });

        //! Missing to implement the load file functionality.
        // Load button
        //loadButton.addActionListener(
        //    new ActionListener() {
        //        public void actionPerformed(ActionEvent e) {
        //            optionOpenAction();
        //        }
        //    });
        // });
    }


    //! Missing to implement the load file functionality.
    /*private void optionOpenAction() {
        try{
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Garden files", "dat");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "Open file: " + fileChooser.getSelectedFile().getName());
                gameBack = Game.open(fileChooser.getSelectedFile().getAbsolutePath());
                photo.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error open file");
        }
    }*/
}