package domain.plants;

import domain.Game;
import domain.PvZExceptions;
import domain.economy.Sun;

import java.io.Serializable;

/**
 * ECIPlant is a plant that generates suns every 3 seconds.
 * It costs 75 suns and has 150 life points.
 * It does not attack zombies or do any move.
 */
public class ECIPlant extends Plant implements Runnable, Serializable {

    // Attributes
    private static final String name = "eciplant";
    private boolean isActive = true;
    private transient Thread thread;



    // Constructor

    /**
     * Constructor of the ECIPlant class.
     * @param x x-coordinate of the plant.
     * @param y y-coordinate of the plant.
     * @param game Game where the plant is.
     */
    public ECIPlant(int x, int y, Game game) {
        super(name);
        this.life = 150;
        this.cost = 75;
        this.game = game;
        this.positionY = y;
        this.positionX = x;

        thread = new Thread(this);
        thread.start();
    }


    // Methods

    /**
     * Generates a sun and adds it to the game.
     * @throws PvZExceptions if the sun cannot be added to the game.
     */
    public void generateSun () throws PvZExceptions {
        Sun sun = new Sun(50);
        this.game.addSuns(sun);
    }

    /**
     * Thread that generates suns every 3 seconds.
     * If the plant dies, the thread stops.
     */
    @Override
    public void run() {
        while (isActive) {
            try {
                generateSun();
                Thread.sleep(3000);
            } catch (InterruptedException | PvZExceptions e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * This method is called when the plant dies. It stops the thread and disable the plant.
     */
    @Override
    public void die() {
        super.die();
        isActive = false;
    }
}
