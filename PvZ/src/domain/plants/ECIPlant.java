package domain.plants;

import domain.Game;
import domain.economy.SuperSun;

public class ECIPlant extends Plant {
    private static final String name = "ECIPlant";
    public ECIPlant(int x, int y, Game game) {
        super(name);
        this.life = 150;
        this.cost = 75;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }
    public void GenerateSun() {
        SuperSun sun = new SuperSun();
        this.game.addSuns(sun);
    }
}
