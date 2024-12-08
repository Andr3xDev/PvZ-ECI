package domain.plants;
import domain.Game;
import domain.zombies.Zombie;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PotatoMine extends Plant {
    private boolean isActive;
    private static final String name = "PotatoMine";
    private static final int ACTIVATION_DELAY = 14;
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
    public void activate() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> {
            this.isActive = true;
            System.out.println("tamo activo papi");
            scheduler.shutdown();
        }, ACTIVATION_DELAY, TimeUnit.SECONDS);
    }
    public void explode(int zombieX, int zombieY) {

        if (game.getUnit()[zombieX][zombieY] instanceof Zombie) {
            Zombie zombie = (Zombie) game.getUnit()[zombieX][zombieY];
            zombie.takeDamage(9999);
            System.out.println("POW :D");
        }
        this.game.deleteUnit(this.positionX, this.positionY);
        this.life = 0;

    }
    @Override
    public void die() {
        super.die();
        isActive = false;
    }
    @Override
    public void takeDamage(int dmg) {
        if (isActive) {
            explode(positionX + 1, positionY);
        } else {
            super.takeDamage(dmg);
        }
    }
}
