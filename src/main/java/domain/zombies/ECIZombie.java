package domain.zombies;

import domain.Game;
import domain.POOmBas;

import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a ECIZombie zombie in the game.
 * It costs 250 sun points, has 200 life points and deals 50 damage points.
 * It's the basic zombie but throws POOmBas.
 */
public class ECIZombie extends Zombie implements Serializable {

    // Attributes
    private static final String name = "ecizombie";
    private transient ScheduledExecutorService scheduler;
    private transient Thread bulletThread;  // This is already transient



    // Constructor

    /**
     * Constructor of the ECIZombie class.
     * @param y The position Y of the zombie.
     * @param game The game where the zombie belongs.
     */
    public ECIZombie(int y, Game game) {
        super(name);
        this.life = 200;
        this.damage = 50;
        this.cost = 250;
        this.game = game;
        this.positionY = y;
        this.positionX = 10;
        startShooting();
    }


    // Methods

    /**
     * Makes the zombie attack. It throws a POOmBas to the plants every 1.5 seconds.
     */
    @Override
    protected void attack() {
        POOmBas poombas = new POOmBas(this.damage, this.positionX, this.positionY, this.game);
        bulletThread = new Thread(poombas);
        bulletThread.start();
    }

    /**
     * Starts the shooting of the zombie when its created.
     */
    private void startShooting() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::attack, 0, 1500, TimeUnit.MILLISECONDS);
    }

    /**
     * Stops the shooting of the zombie when it dies.
     */
    public void stopShooting() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    /**
     * This method is called when the zombie dies. It stops the shooting of the zombie.
     */
    @Override
    public void die() {
        super.die();
        stopShooting();
    }
}