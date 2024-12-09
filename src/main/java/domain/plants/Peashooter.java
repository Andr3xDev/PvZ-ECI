package domain.plants;

import domain.Game;
import domain.Pea;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class represents the Peashooter plant.
 * It is a plant that shoots peas to the zombies to kill them.
 * It costs 100 sun points and has 300 life points.
 */
public class Peashooter extends Plant {

    // Attributes
    private static final String name = "peashooter";
    private ScheduledExecutorService scheduler;


    // Constructor

    /**
     * Constructor of the Peashooter class.
     * @param x x-coordinate of the plant.
     * @param y y-coordinate of the plant.
     * @param game Game where the plant is.
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


    // Methods

    /**
     * This method creates a new Pea object and starts a new thread for its functionality.
     */
    private void shoot() {
        Pea pea = new Pea(this.damage, this.positionX, this.positionY, this.game);
        Thread bulletThread = new Thread(pea);
        bulletThread.start();
    }

    /**
     * This method starts a new thread that shoots peas every 1.5 seconds.
     * If the plant dies or is removed, the thread stops.
     * The peas make 150 damage to the zombies.
     */
    private void startShooting() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::shoot, 0, 1500, TimeUnit.MILLISECONDS);
    }

    /**
     * This method stops the thread that shoots peas.
     */
    public void stopShooting() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    /**
     * This method is called when the plant dies. It stops the thread and disable the plant.
     */
    @Override
    public void die() {
        super.die();
        stopShooting();
    }
}
