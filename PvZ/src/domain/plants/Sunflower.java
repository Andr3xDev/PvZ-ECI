package domain.plants;

import domain.Game;
import domain.economy.Sun;

public class Sunflower extends Plant implements Runnable {
    private static final String name = "Sunflower";
    private boolean isActive = true;

    public Sunflower(int x, int y, Game game) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }

    public void generateSun() {
        Sun sun = new Sun();
        this.game.addSuns(sun);
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                generateSun(); // Generar un sol
                Thread.sleep(3000); // Esperar 3 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    };
}

