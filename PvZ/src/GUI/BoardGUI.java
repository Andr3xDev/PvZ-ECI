package GUI;

import GUI.extras.BackgroundImage;
import GUI.extras.BackgroundSound;
import GUI.extras.Board;
import GUI.extras.Sprite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Class that represents the Game's Board GUI. It contains all the elements of the game, such as
 * the plants, zombies, bullets, etc.
 */
public class BoardGUI extends JFrame implements Runnable {
    //! Missing keylistener

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Panels
    JPanel plantsPanel = new JPanel();
    JPanel zombiesPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    // Background elements
    private BackgroundImage backgroundImage;
    private BackgroundSound backgroundSound;

    // Game elements
    private Board board;
    private ArrayList<Sprite> bullets;
    private ArrayList<Sprite> plants;
    private ArrayList<Sprite> players;
    private ArrayList<Sprite> zombies;
    private ArrayList<Sprite> suns;
    private ArrayList<Sprite> brains;



    /**
     * Constructor, creates the Game's elements and actions.
     */
    public BoardGUI() {
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
        setTitle("Plants vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);

        // Background Elements
        backgroundImage = new BackgroundImage("PvZ/assets/background/start.jpeg");
        //! Menu sound doesn't stop and the audio duplicates
        //backgroundSound = new BackgroundSound("PvZ/assets/sound/LoonBoon.wav");

        // Prepare all Elements
        prepareElementsBoard();
        prepareElementsPlayers();

        // Add Panels
        add(backgroundImage);
    }


    private void prepareElementsBoard() {
    }

    private void prepareElementsPlayers() {
    }





    //** Update Elements **//

    private void update() {
        // Update all elements
        updatePlayers();
        updateZombies();
        updateBullets();
        updateSuns();
        updateBrains();
        //board.repaint();
    }

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