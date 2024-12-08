package domain.plants;

import domain.Game;
import domain.Pea;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Peashooter extends Plant {
    private static final String name = "Peashooter";
    private ScheduledExecutorService scheduler;

    public Peashooter(int x, int y, Game game) {
        super(name);
        this.life = 300;
        this.damage = 20;
        this.cost = 100;
        this.game = game;
        this.positionY = y;
        this.positionX = x;

        startShooting();
    }

    private void shoot() {
        Pea pea = new Pea(this.damage, this.positionX, this.positionY, this.game);
        Thread bulletThread = new Thread(pea);
        bulletThread.start();
    }

    private void startShooting() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::shoot, 0, 1500, TimeUnit.MILLISECONDS);
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
