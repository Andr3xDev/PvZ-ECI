package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.BackgroundSound;
import GUI.extras.BoardBox;
import GUI.extras.BoardConf;

import javax.swing.*;
import java.awt.*;


/**
 * Class that represents the Game's Board GUI. It contains all the elements of the game, such as
 * the plants, zombies, bullets, etc.
 */
public class BoardGUI extends JFrame implements Runnable {
    //! Missing keylistener and this attributes
    private BoardBox boardBox;           // Singleton que maneja la lógica del juego

    //** Attributes **//

    // Dimensions
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int ROWS = 5;
    private static final int COLS = 10;

    // Panels
    JPanel plantsPanel = new JPanel();
    JPanel zombiesPanel = new JPanel();
    JPanel boardPanel = new BoardConf("PvZ/assets/background/board.jpg");

    JPanel infoPanel = new JPanel();

    // Background elements
    private BackgroundImage backgroundImage;
    private BackgroundImage backgroundBoard;
    private BackgroundSound backgroundSound;

    // Game elements
    private GameAPP app;
    private String gameMode;
    private BoardBox[][] boxes;
    private JLabel timerLabel;
    private int remainingTime;
    private boolean shovelMode;
    private JLabel sunsLabel;
    private JLabel brainLabel;
    private String selectedPlant;
    private String selectedZombie;




    /**
     * Constructor, creates the Game's elements and actions.
     */
    public BoardGUI(GameAPP app) {
        this.app = app;
        this.boxes = new BoardBox[ROWS][COLS];
        prepareElements();
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

        // Background Elements
        //! Menu sound doesn't stop and the audio duplicates
        //backgroundSound = new BackgroundSound("PvZ/assets/sound/LoonBoon.wav");

        // Panels organization


        // Prepare all Elements
        prepareElementsBoard();
        prepareElementsPlayers();

        // Add Panels
        add(boardPanel, BorderLayout.CENTER);
        add(plantsPanel, BorderLayout.WEST);
        add(zombiesPanel, BorderLayout.EAST);
        add(infoPanel, BorderLayout.NORTH);
    }


    private void prepareElementsBoard() {
        boardPanel.setLayout(new GridLayout(ROWS, COLS));
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                boxes[i][j] = new BoardBox(i,j);
                boardPanel.add(boxes[i][j]);
            }
        }
    }

    private void prepareElementsPlayers() {

        plantsPanel.setBackground(Color.GREEN);
        zombiesPanel.setBackground(Color.RED);
        infoPanel.setBackground(Color.BLUE);


        //Plants Panel
        plantsPanel.setLayout(new GridLayout(7, 1));
        plantsPanel.add(new JLabel("Plants"));
        plantsPanel.add(new JLabel("Suns"));
        plantsPanel.add(new JButton("Sunflower"));
        plantsPanel.add(new JButton("Peashooter"));
        plantsPanel.add(new JButton("Wallnut"));
        plantsPanel.add(new JButton("Cherrybomb"));
        plantsPanel.add(new JButton("Snowpea"));

        //Zombies Panel
        zombiesPanel.setLayout(new GridLayout(7, 1));
        zombiesPanel.add(new JLabel("Zombies"));
        zombiesPanel.add(new JLabel("Brains"));
        zombiesPanel.add(new JButton("Zombie"));
        zombiesPanel.add(new JButton("Conehead"));
        zombiesPanel.add(new JButton("Buckethead"));
        zombiesPanel.add(new JButton("Brainstain"));
        zombiesPanel.add(new JButton("Football"));

    }

    private void prepareElementsInfo() {
        infoPanel.setLayout(new GridLayout(1, 3));
        timerLabel = new JLabel("Time: 0");
        sunsLabel = new JLabel("Points: 0");
        brainLabel = new JLabel("points: 0");
        infoPanel.add(timerLabel);
        infoPanel.add(sunsLabel);
        infoPanel.add(brainLabel);
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

    public void run() {
        //! Missing to implement the game loop
        /*try {
               while (!juego.gameOver() && !juego.finished()) {
                   actualizar();
                    actualizarBloques();
                    cargaNivel();
                    Thread.sleep(3000);
                    quitarNivel();
                    while (!juego.nivelAcabado() && !juego.gameOver()) {
                        if (!juego.enPausa()) {
                            if (juego.creoBola()) Thread.sleep(1000);
                            juego.mover();
                            if (juego.actualizarBloques()) {
                                actualizarBloques();
                            }
                            actualizar();
                        }
                    }
                }
                terminar(juego.gameOver());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
    }

}