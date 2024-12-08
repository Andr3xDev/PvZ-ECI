package GUI;

import GUI.extras.*;
import domain.Game;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JPanel plantsPanel = new JPanel();
    JPanel zombiesPanel = new JPanel();
    JPanel boardPanel = new BoardConf("PvZ/assets/background/board.jpg");
    JPanel infoPanel = new JPanel();

    // Buttons
    SelectButton basicButton;
    SelectButton bucketButton;
    SelectButton coneButton;
    SelectButton brainButton;
    SelectButton eciZombieButton;

    SelectButton peaButton;
    SelectButton sunflowerButton;
    SelectButton wallNutButton;
    SelectButton potatoButton;
    SelectButton eciPlantButton;

    // Game elements
    private GameAPP app;
    private Game game;
    private String gameMode;
    private BoardBox[][] boxes;
    private JLabel timerLabel;
    private boolean shovelMode;
    private JLabel plantPoints;
    private JLabel zombiesPoints;
    private String selectedPlant;
    private String selectedZombie;


    /**
     * Constructor, creates the Game's elements and actions.
     */
    public BoardGUI(GameAPP app) {
        this.app = app;
        this.boxes = new BoardBox[ROWS][COLS];
        prepareElements();
        prepareActions();
        game = new Game();
        Thread guiThread = new Thread(this);
        guiThread.start();
    }



    //** Prepare Elements **//

    /**
     * Prepares all basic elements of the Game GUI.
     */
    private void prepareElements() {
        // Window actions
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Window properties
        setTitle("POOB vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Prepare all Elements
        prepareElementsBoard();
        prepareElementsPlayerZombies();
        prepareElementsPlayerPlants();
        prepareElementsInfo();
        prepareElementsOthers();
        prepareElementsMowers();
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
     * Prepares the buttons and labels of the Player Zombies Panel.
     */
    private void prepareElementsPlayerZombies() {
        //* Zombies Panel
        zombiesPanel.setLayout(new GridLayout(7, 1));
        zombiesPanel.setPreferredSize(new Dimension(screenSize.width / 9, screenSize.height / 2));
        zombiesPanel.setBackground(new Color(2, 0, 51, 200));
        zombiesPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Elements
        zombiesPanel.add(new RoundedLabel("Zombies"));
        zombiesPanel.add(new RoundedLabel("Brains"));
        basicButton = new SelectButton("PvZ/assets/zombies/basic.png");
        zombiesPanel.add(basicButton);
        bucketButton = new SelectButton("PvZ/assets/zombies/buckethead.png");
        zombiesPanel.add(bucketButton);
        coneButton = new SelectButton("PvZ/assets/zombies/conehead.png");
        zombiesPanel.add(coneButton);
        brainButton = new SelectButton("PvZ/assets/zombies/brainstein.png");
        zombiesPanel.add(brainButton);
        eciZombieButton = new SelectButton("PvZ/assets/zombies/ecizombie.png");
        zombiesPanel.add(eciZombieButton);

        add(zombiesPanel, BorderLayout.EAST);
    }


    /**
     * Prepares the buttons and labels of the Player Plants Panel.
     */
    private void prepareElementsPlayerPlants() {
        plantsPanel.setLayout(new GridLayout(7, 1));
        plantsPanel.setPreferredSize(new Dimension(screenSize.width / 9, screenSize.height / 2));
        plantsPanel.setBackground(new Color(2, 0, 51, 200));
        plantsPanel.setBorder(BorderFactory.createLineBorder(new Color(2, 0, 51), 8));

        // Elements
        plantsPanel.add(new RoundedLabel("Plants"));
        plantsPanel.add(new RoundedLabel("Suns"));
        sunflowerButton = new SelectButton("PvZ/assets/plants/sunflower.png");
        plantsPanel.add(sunflowerButton);
        peaButton = new SelectButton("PvZ/assets/plants/peashooter.png");
        plantsPanel.add(peaButton);
        wallNutButton = new SelectButton("PvZ/assets/plants/wallnut.png");
        plantsPanel.add(wallNutButton);
        potatoButton = new SelectButton("PvZ/assets/plants/potatomine.png");
        plantsPanel.add(potatoButton);
        eciPlantButton = new SelectButton("PvZ/assets/plants/eciplant.png");
        plantsPanel.add(eciPlantButton);

        add(plantsPanel, BorderLayout.WEST);
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
        plantPoints = new RoundedLabel("Points: ");
        timerLabel = new RoundedLabel("Time: ");
        zombiesPoints = new RoundedLabel("Points: ");
        infoPanel.add(plantPoints);
        infoPanel.add(timerLabel);
        infoPanel.add(zombiesPoints);

        prepareElementsTimer();

        add(infoPanel, BorderLayout.NORTH);
    }


    /**
     * Prepares the timer of the game. It changes depending the game.
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



    //** Prepare Actions **//

    /**
     * Prepares all the actions of the Game GUI.
     */
    private void prepareActions() {
        prepareActionsSelect();
        prepareActionsPlants();
        prepareActionsZombies();
    }


    /**
     * Prepares the actions of the Select Buttons from the panels.
     */
    private void prepareActionsSelect() {
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
     * Prepares the actions of the Plant Boxes from the board to allow select or plant.
     */
    private void prepareActionsPlants() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                boxes[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (selectedPlant == null) {
                            System.out.println("Select a plant first");
                        } else if (shovelMode) {
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
                                game.addZombie(selectedZombie, finalI, finalJ);
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

    private void updatePlayers() {
    }

    private void updateZombies() {
    }

    private void updateBullets() {
    }

    private void updateSuns() {
    }

    private void updateBrains() {
    }



    //** Paint Elements **//

    @Override
    public void run() {
        System.out.println("Starting GUI update thread...");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                game.updateZombies();
                SwingUtilities.invokeLater(this::updateBoard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }


    /**
     * Updates the board GUI to reflect the current state of the game.
     */
    private void updateBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                boxes[i][j].clear();

                // Plants
                if (game.getPlant(j, i) != null) {
                    boxes[i][j].addPlant(game.getPlant(j, i).getName());
                }

                // Zombies
                if (game.getZombie(j, i) != null) {
                    boxes[i][j].addZombie(game.getZombie(j, i).getName());
                }

                //game.printBoard();
            }
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                boxes[i][j].revalidate();
                boxes[i][j].repaint();
            }
        }
    }
}