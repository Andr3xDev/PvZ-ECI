package domain.plants;

import domain.Game;
import domain.Pea;
import domain.plants.Plant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The Peashooter class represents a plant that shoots peas at regular intervals.
 * It extends the Plant class and implements Serializable.
 */
public class Peashooter extends Plant implements Serializable {

    // Attributes
    private static final String name = "peashooter";
    private transient ScheduledExecutorService scheduler;  // Make this transient
    private transient Thread bulletThread;  // This is already transient

    /**
     * Constructs a new Peashooter with the specified position and game context.
     *
     * @param x the x-coordinate of the Peashooter's position
     * @param y the y-coordinate of the Peashooter's position
     * @param game the game context in which the Peashooter exists
     */
    public Peashooter(int x, int y, Game game) {
        super(name);
        this.life = 300;
        this.damage = 150;
        this.cost = 100;
        this.game = game;
        this.positionY = y;
        this.positionX = x;

        startShooting();
    }

    /**
     * Creates and starts a new thread to shoot a pea.
     */
    private void shoot() {
        Pea pea = new Pea(this.damage, this.positionX, this.positionY, this.game);
        bulletThread = new Thread(pea);
        bulletThread.start();
    }

    /**
     * Starts the shooting process by scheduling the shoot method to be called at fixed intervals.
     */
    private void startShooting() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::shoot, 0, 1500, TimeUnit.MILLISECONDS);
    }

    /**
     * Stops the shooting process by shutting down the scheduler.
     */
    public void stopShooting() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    /**
     * Handles the death of the Peashooter by stopping the shooting process and calling the superclass's die method.
     */
    @Override
    public void die() {
        super.die();
        stopShooting();
    }

    /**
     * Custom serialization method to handle transient fields.
     *
     * @param out the ObjectOutputStream
     * @throws IOException if an I/O error occurs
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * Custom deserialization method to handle transient fields.
     *
     * @param in the ObjectInputStream
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        startShooting();
    }
}