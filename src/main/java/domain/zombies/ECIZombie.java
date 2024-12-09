package domain.zombies;

import domain.Game;
import domain.POOmBas;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ECIZombie extends Zombie {
    private static final String name = "ecizombie";
    private ScheduledExecutorService scheduler;
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
    @Override
    protected void attack() {
        POOmBas poombas = new POOmBas(this.damage, this.positionX, this.positionY, this.game);
        Thread bulletThread = new Thread(poombas);
        bulletThread.start();
    }

    private void startShooting() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::attack, 0, 1500, TimeUnit.MILLISECONDS);
    }

    public void stopShooting() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    @Override
    public void die() {
        super.die();
        stopShooting();
    }
}
