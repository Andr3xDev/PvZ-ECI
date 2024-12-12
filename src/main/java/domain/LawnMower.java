package domain;

import domain.zombies.Zombie;

import java.util.logging.Logger;

/**
 * Class that represents the LawnMower object.
 * It is used to kill all the zombies in a row when they touch this.
 */
public class LawnMower implements Runnable {

    // Attributes
    private Game game;
    private int positionY;
    private int positionX = 0;
    private boolean isActive = false;
    boolean waitingToActivate = false;
    private Thread thread;
    private static final Logger logger = Logger.getLogger(Game.class.getName());


    // Constructor
    public LawnMower(Game game, int positionY) {
        this.game = game;
        this.positionY = positionY;
        this.thread = new Thread(this);
        thread.start();
    }

    // Methods

    /**
     * Activates the LawnMower after detecting a zombie.
     */
    public void activate() {
        isActive = true;
        logger.info("LawnMower activado en la fila: " + positionY);
    }

    /**
     * Checks if there's a zombie directly in front of the LawnMower.
     * @return True if there's a zombie in front, false otherwise.
     */
    public boolean isZombieInFront() {
        if (positionX + 1 < 11) {
            return game.getZombie(positionX + 1, positionY) != null;
        }
        return false;
    }

    /**
     * Method that kills the zombie in front of the LawnMower.
     */
    public void kill() {
        for (int i = positionX; i < 11; i++) {
            Zombie zombie = game.getZombie(i, positionY);
            if (zombie != null) {
                zombie.takeDamage(9999999);
            }
        }
    }


    /**
     * Moves the LawnMower one step forward.
     */
    public void move() {
        game.getLawnMowers()[positionX][positionY] = null;

        if (positionX + 1 == 10) {
            isActive = false;
            game.getLawnMowers()[positionX][positionY] = null;
            this.thread.interrupt();
        }

        positionX++;
        game.getLawnMowers()[positionX][positionY] = this;
    }

    /**
     * Runnable method that moves the LawnMower when activated.
     */
    @Override
    public void run() {
        while (positionX < 11) {
            try {
                Thread.sleep(10);

                if (!isActive) {
                    if (isZombieInFront()) {
                        activate();
                        Thread.sleep(2000);
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        kill();
                        Thread.sleep(50);
                    }

                    if (positionX + 1 < 11) {
                        move();
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    // Getters
    public int getPositionY() {
        return positionY;
    }
}
