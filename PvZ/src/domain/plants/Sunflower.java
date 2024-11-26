package domain.plants;

import domain.Game;
import domain.economy.Sun;

public class Sunflower extends Plant {
    private static final String name = "Sunflower";
    public Sunflower(int x, int y, Game game) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }
    public void GenerateSun() {
        Sun sun = new Sun();
        this.game.addSuns(sun);
    }
}
