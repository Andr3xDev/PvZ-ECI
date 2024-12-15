package domain.plants;

import domain.Game;
import domain.PvZExceptions;
import domain.zombies.Zombie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a PotatoMine in the game.
 * It is a plant that explodes when a zombie steps on it after a certain amount of time.
 * It costs 25 suns and has 100 life points.
 */
public class PotatoMine extends Plant implements Serializable {

    // Attributes
    private boolean isActive;
    private static final String name = "potatomine";
    private static final int ACTIVATION_DELAY = 14;
    private transient ScheduledExecutorService scheduler;

    // Constructor

    /**
     * Creates a PotatoMine in the specified coordinates.
     * @param x the x-coordinate of the plant.
     * @param y the y-coordinate of the plant.
     * @param game the game where the plant belongs.
     */
    public PotatoMine(int x, int y, Game game) {
        super(name);
        this.life = 100;
        this.cost = 25;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
        this.isActive = false;
        activate();
    }

    // Methods

    /**
     * Activates the PotatoMine after a certain amount of time.
     */
    public void activate() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            this.isActive = true;
            System.out.println("Activated!");
            scheduler.shutdown();
        }, ACTIVATION_DELAY, TimeUnit.SECONDS);
    }

    /**
     * Explodes the PotatoMine, killing the zombie in the specified coordinates.
     * @param zombieX the x-coordinate of the zombie.
     * @param zombieY the y-coordinate of the zombie.
     */
    public void explode(int zombieX, int zombieY) {
        try {
            if (game.getUnit()[zombieX][zombieY] instanceof Zombie) {
                Zombie zombie = (Zombie) game.getUnit()[zombieX][zombieY];
                zombie.takeDamage(9999);
                System.out.println("POW :D");
            }
            this.game.deleteUnit(this.positionX, this.positionY);
            this.life = 0;
        } catch (PvZExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called when the PotatoMine dies.
     */
    @Override
    public void die() {
        super.die();
        isActive = false;
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    /**
     * This method is called when the PotatoMine takes damage.
     * If the PotatoMine is active, it explodes.
     * @param dmg the amount of damage taken.
     */
    @Override
    public void takeDamage(int dmg) {
        if (isActive) {
            explode(positionX + 1, positionY);
        } else {
            super.takeDamage(dmg);
        }
    }
}