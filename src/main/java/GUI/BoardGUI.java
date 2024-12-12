package GUI;

import GUI.extras.*;
import domain.Game;
import domain.PvZExceptions;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Class that represents the Game's Board GUI. It contains all the elements of the game, such as
 * the plants, zombies, bullets, etc.
 */
public class BoardGUI extends JFrame implements Runnable {

    //** Attributes **//

    // Dimensions
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int ROWS = 5;
    private static final int COLS = 11;

    // Panels
    private final JPanel plantsPanel = new JPanel();
    private final JPanel zombiesPanel = new JPanel();
    private final JPanel boardPanel = new BoardConf("src/main/resources/background/board.jpg");
    private final JPanel infoPanel = new JPanel();

    // Buttons
    private SelectButton basicButton;
    private SelectButton bucketButton;
    private SelectButton coneButton;
    private SelectButton brainButton;
    private SelectButton eciZombieButton;

    private SelectButton peaButton;
    private SelectButton sunflowerButton;
    private SelectButton wallNutButton;
    private SelectButton potatoButton;
    private SelectButton eciPlantButton;

    // Labels
    private JLabel timerLabel;
    private JLabel brainsLabel;
    private JLabel sunsLabel;

    // Menu
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem exit;

    // Game
    private final Game game;
    private final GameAPP app;
    private final String gameMode;
    private final BoardBox[][] boxes;
    private boolean shovelMode;
    private String selectedPlant;
    private String selectedZombie;
    private transient Thread guiThread;




    //** Constructors **//

    /**
     * Constructor, creates the Game's elements and actions.
     */
    public BoardGUI(GameAPP app, String gameMode) {
        game = new Game(gameMode);
        this.gameMode = gameMode;
        this.app = app;
        this.boxes = new BoardBox[ROWS][COLS];
        prepareElements();
        prepareActions();
        guiThread = new Thread(this);
        guiThread.start();
    }




    //** Prepare Elements **//

    /**
     * Prepares all basic elements of the Game GUI.
     */
    private void prepareElements() {
        // Window actions
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Window properties
        setTitle("POOB vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Prepare all Elements
        prepareElementsPlayerPlants();
        prepareElementsPlayerZombies();
        prepareElementsBoard();
        prepareElementsPlayers();
        prepareElementsInfo();
        prepareElementsOthers();
        prepareElementsMowers();
        prepareElementsMenu();
    }


    /**
     * Prepares the Board elements like the boxes and their functionality.
     */
    private void prepareElementsBoard() {
        boardPanel.setLayout(new GridLayout(ROWS, COLS));
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                boxes[i][j] = new BoardBox(i, j);
                boardPanel.add(boxes[i][j]);
            }
        }
        boardPanel.setBorder(new EmptyBorder(
                screenSize.height / 9,
                screenSize.width / 8 + 5,
                screenSize.height / 19,
                screenSize.width / 8
        ));
        add(boardPanel, BorderLayout.CENTER);
    }


    /**
     * Prepares the elements of the Players, like the plants and zombies depending on the game mode.
     */
    private void prepareElementsPlayers() {
        switch (gameMode) {
            case "pvp" -> {
                add(zombiesPanel, BorderLayout.EAST);
                add(plantsPanel, BorderLayout.WEST);
            }
            case "pvAI" -> add(plantsPanel, BorderLayout.WEST);
            case "AIvAI" -> {System.out.println("AI playing");}
        }
    }


    /**
     * Prepares the buttons and labels of the Player Zombies Panel.
     */
    private void prepareElementsPlayerZombies() {
        //* Zombies Panel
        zombiesPanel.setLayout(new GridLayout(6, 2));
        zombiesPanel.setPreferredSize(new Dimension(screenSize.width / 7, screenSize.height / 2));
        zombiesPanel.setBackground(new Color(2, 0, 51, 200));
        zombiesPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Elements
        zombiesPanel.add(new RoundedLabel("Zombies"));
        zombiesPanel.add(brainsLabel = new RoundedLabel("Brains"));
        basicButton = new SelectButton("src/main/resources/zombies/basic.png");
        zombiesPanel.add(basicButton);
        zombiesPanel.add(new RoundedLabel(": 100"));
        coneButton = new SelectButton("src/main/resources/zombies/conehead.png");
        zombiesPanel.add(coneButton);
        zombiesPanel.add(new RoundedLabel(": 150"));
        bucketButton = new SelectButton("src/main/resources/zombies/buckethead.png");
        zombiesPanel.add(bucketButton);
        zombiesPanel.add(new RoundedLabel(": 200"));
        brainButton = new SelectButton("src/main/resources/zombies/brainstein.png");
        zombiesPanel.add(brainButton);
        zombiesPanel.add(new RoundedLabel(": 50"));
        eciZombieButton = new SelectButton("src/main/resources/zombies/ecizombie.png");
        zombiesPanel.add(eciZombieButton);
        zombiesPanel.add(new RoundedLabel(": 250"));
    }


    /**
     * Prepares the buttons and labels of the Player Plants Panel.
     */
    private void prepareElementsPlayerPlants() {
        plantsPanel.setLayout(new GridLayout(6, 2));
        plantsPanel.setPreferredSize(new Dimension(screenSize.width / 7, screenSize.height / 2));
        plantsPanel.setBackground(new Color(2, 0, 51, 200));
        plantsPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Elements
        plantsPanel.add(new RoundedLabel("Plants"));
        plantsPanel.add(sunsLabel = new RoundedLabel("Suns"));
        sunflowerButton = new SelectButton("src/main/resources/plants/sunflower.png");
        plantsPanel.add(sunflowerButton);
        plantsPanel.add(new RoundedLabel(": 50"));
        peaButton = new SelectButton("src/main/resources/plants/peashooter.png");
        plantsPanel.add(peaButton);
        plantsPanel.add(new RoundedLabel(": 100"));
        wallNutButton = new SelectButton("src/main/resources/plants/wallnut.png");
        plantsPanel.add(wallNutButton);
        plantsPanel.add(new RoundedLabel(": 50"));
        potatoButton = new SelectButton("src/main/resources/plants//potatomine.png");
        plantsPanel.add(potatoButton);
        plantsPanel.add(new RoundedLabel(": 25"));
        eciPlantButton = new SelectButton("src/main/resources/plants/eciplant.png");
        plantsPanel.add(eciPlantButton);
        plantsPanel.add(new RoundedLabel(": 75"));
    }


    /**
     * Prepares elements that have no importance in the game, like the refill panel.
     */
    private void prepareElementsOthers() {
        JPanel refillPanel = new JPanel();
        refillPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 7));
        refillPanel.setBackground(new Color(2, 0, 51, 200));
        refillPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));
        add(refillPanel, BorderLayout.SOUTH);
    }


    /**
     * Prepares the elements of the Info Panel, like the points and the timer.
     */
    private void prepareElementsInfo() {
        infoPanel.setLayout(new GridLayout(1, 3, 300, 50));
        infoPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 7));
        infoPanel.setBackground(new Color(2, 0, 51, 200));
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Elements
        JLabel plantPoints = new RoundedLabel("Points: ");
        timerLabel = new RoundedLabel("Time: ");
        JLabel zombiesPoints = new RoundedLabel("Points: ");
        infoPanel.add(plantPoints);
        infoPanel.add(timerLabel);
        infoPanel.add(zombiesPoints);

        prepareElementsTimer();

        add(infoPanel, BorderLayout.NORTH);
    }


    /**
     * Prepares the timer of the game. It changes depending on the game. Also updates the brains and suns.
     */
    private void prepareElementsTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            int seconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                timerLabel.setText("Time: " + (120 - seconds) + " S");
            }
        });
        timer.start();
    }


    /**
     * Prepares the mower elements of the game.
     */
    private void prepareElementsMowers() {
        for (int i = 0; i < 5; i++) {
            boxes[i][0].addLawnMower();
        }
    }


    /**
     * Prepares the elements of the Menu from ESC key.
     */
    private void prepareElementsMenu() {
        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        save = new JMenuItem("Save");
        open = new JMenuItem("Load");
        exit = new JMenuItem("Exit");
        menu.add(save);
        menu.add(open);
        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }




    //** Prepare Actions **//

    /**
     * Prepares all the actions of the Game GUI.
     */
    private void prepareActions() {
        prepareActionsSelect();
        prepareActionsPlants();
        prepareActionsZombies();
        prepareActionsMenu();
    }


    /**
     * Prepares the actions for the buttons of the Menu.
     */
    private void prepareActionsMenu() {
        open.addActionListener(
                _ -> {
                    try {
                        OpenAction();
                    } catch (PvZExceptions ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        save.addActionListener(
                _ -> {
                    try {
                        SaveAction();
                    } catch (PvZExceptions ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        exit.addActionListener(
                _ -> {
                    app.setVisible(true);
                    dispose();
                }
        );
    }


    //** Save and Open Actions **//

    /**
     * Saves the current game state to a file.
     * @throws PvZExceptions if the game cannot be saved.
     */
    private void SaveAction() throws PvZExceptions {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PvZ Save Files (*.PvZ)", "PvZ");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Extension check
            if (!filePath.endsWith(".PvZ")) {
                filePath += ".PvZ";
            }

            // Save game
            game.save(filePath);
            JOptionPane.showMessageDialog(null, "Game saved: " + filePath);
        }
    }


    /**
     * Opens a game state from a file.
     * @throws PvZExceptions if the game cannot be opened.
     */
    private void OpenAction() throws PvZExceptions {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PvZ Save Files (*.PvZ)", "PvZ");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Load Game
            Game.open(selectedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Game loaded: " + selectedFile.getName());
        }
    }


    /**
     * Prepares the actions of the Select Buttons from the panels.
     */
    private void prepareActionsSelect() {
        if (Objects.equals(gameMode, "pvp")) {
            prepareActionsZombieSelect();
            prepareActionsPlantSelect();
        } else if (Objects.equals(gameMode, "pvAI")) {
            prepareActionsPlantSelect();
        } else if (Objects.equals(gameMode, "AIvAI")) {
            prepareActionsZombieSelect();
            prepareActionsPlantSelect();
        }
    }


    /**
     * Prepares the actions of the Plant Select Buttons from the panel.
     */
    private void prepareActionsZombieSelect() {
        peaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedPlant = "peashooter";
            }
        });
        sunflowerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedPlant = "sunflower";
            }
        });
        wallNutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedPlant = "wallnut";
            }
        });
        potatoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedPlant = "potatomine";
            }
        });
        eciPlantButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedPlant = "eciplant";
            }
        });
    }


    /**
     * Prepares the actions of the Zombie Select Buttons from the panel if the game mode is pvp.
     */
    private void prepareActionsPlantSelect() {
        basicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedZombie = "basic";
            }
        });
        bucketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedZombie = "buckethead";
            }
        });
        coneButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedZombie = "conehead";
            }
        });
        brainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedZombie = "brainstein";
            }
        });
        eciZombieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedZombie = "ecizombie";
            }
        });
    }


    /**
     * Prepares the actions of the Plant Boxes from the board to allow select or plant.
     */
    private void prepareActionsPlants() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                int finalI = i;
                int finalJ = j;
                boxes[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (selectedPlant == null) {
                            System.out.println("Select a plant first");
                        } else if (shovelMode) {       //! missing add shovel
                            boxes[finalI][finalJ].remove();
                        } else {
                            try {
                                game.addPlant(selectedPlant, finalJ, finalI);
                                boxes[finalI][finalJ].addPlant(selectedPlant);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    }


    /**
     * Prepares the actions of the Zombie Boxes from the board to allow select or put zombies.
     */
    private void prepareActionsZombies() {
        for (int i = 0; i < 5; i++) {
            for (int j = 9; j < 11; j++) {
                int finalI = i;
                int finalJ = j;
                boxes[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (selectedZombie == null) {
                            System.out.println("Select a zombie first");
                        } else {
                            try {
                                game.addZombie(selectedZombie, finalJ, finalI);
                                boxes[finalI][finalJ].addZombie(selectedZombie);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    }




    //** Update Elements **//

    /**
     * Updates the zombie elements of the game.
     */
    private void updateZombies() {
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 11; j++) {
                boxes[i][j].clear();
            }
        }
    }


    /**
     * Updates the bullet elements of the game.
     */
    private void updateBullets() {
    }


    /**
     * Updates the economy elements of the game.
     */
    private void updateEconomy() {
        // brains
        int brains = game.getBrains();
        brainsLabel.setText("Brains: " + brains);

        // suns
        int suns = game.getSuns();
        sunsLabel.setText("Suns: " + suns);
    }



    //** Paint Elements **//

    /**
     * Paints the elements of the game, because the thread updates and allow the movement.
     */
    @Override
    public void run() {
        System.out.println("Starting GUI update thread...");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                game.updateZombies();
                updateEconomy();
                SwingUtilities.invokeLater(this::updateBoard);
                //updateBoard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }


    /**
     * Updates the board GUI to reflect the current state of the game.
     */
    private void updateBoard() {
        updateZombies();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                // Plants
                if (game.getPlant(j, i) != null) {
                    boxes[i][j].addPlant(game.getPlant(j, i).getName());
                }

                // Zombies
                if (game.getZombie(j, i) != null) {
                    boxes[i][j].addZombie(game.getZombie(j, i).getName());
                }
                boxes[i][j].repaint();
            }
        }
    }
}