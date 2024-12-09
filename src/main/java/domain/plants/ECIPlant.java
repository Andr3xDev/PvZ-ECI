package domain.plants;

import domain.Game;
import domain.PvZExceptions;
import domain.economy.Sun;

public class ECIPlant extends Plant implements Runnable{
    private static final String name = "eciplant";
    private boolean isActive = true;
    public ECIPlant(int x, int y, Game game) {
        super(name);
        this.life = 150;
        this.cost = 75;
        this.game = game;
        this.positionY = y;
        this.positionX = x;

        Thread thread = new Thread(this);
        thread.start();
    }
    public void generateSun () throws PvZExceptions {
        Sun sun = new Sun(50);
        this.game.addSuns(sun);
    }
    @Override
    public void run() {
        while (isActive) {
            try {
                generateSun();
                Thread.sleep(3000);
            } catch (InterruptedException | PvZExceptions e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    };
    @Override
    public void die() {
        super.die();
        isActive = false;
    }
}
