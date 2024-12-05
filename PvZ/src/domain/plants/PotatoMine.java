package domain.plants;
import domain.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PotatoMine extends Plant {
    private boolean active;
    private static final String name = "PotatoMine";
    private static final int ACTIVATION_DELAY = 14;
    public PotatoMine(int x, int y, Game game) {
        super(name);
        this.life = 100;
        this.cost = 25;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
        this.active = false;
        activate();
    }
    public void activate() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> {
            this.active = true;
            scheduler.shutdown();
        }, ACTIVATION_DELAY, TimeUnit.SECONDS);
    }
    public void explode() {
        if (this.active) {
            this.life = 0;
            this.game.deletePlant(this.positionX, this.positionY);
        }
    }


}
