package domain.plants;

import domain.Game;
import domain.PvZExceptions;
import domain.economy.Sun;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Sunflower is a plant that generates suns every 3 seconds. It is a passive plant.
 * It costs 50 suns and has 300 life points.
 */
public class Sunflower extends Plant implements Runnable, Serializable {

    // Attributes
    private static final String name = "sunflower";
    private boolean isActive = true;
    private transient Thread thread;
    private static final Logger logger = Logger.getLogger(Game.class.getName());



    // Constructor

    /**
     * Constructor of the Sunflower class.
     * @param x x-coordinate of the plant.
     * @param y y-coordinate of the plant.
     * @param game Game object.
     */
    public Sunflower(int x, int y, Game game) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = x;

        thread = new Thread(this);
        thread.start();
    }


    // Methods

    /**
     * Generates a sun and adds it to the list of suns in the game.
     * @throws PvZExceptions if the sun cannot be generated.
     */
    public void generateSun() throws PvZExceptions {
        Sun sun = new Sun(25);
        this.game.addSuns(sun);
        logger.info("sol generado");
    }

    /**
     * This is the thread that generates suns every 3 seconds.
     */
    @Override
    public void run() {
        while (isActive) {
            try {
                generateSun();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (PvZExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This method is called when the plant dies, disable the plant.
     */
    @Override
    public void die() {
        super.die();
        isActive = false;
    }
}