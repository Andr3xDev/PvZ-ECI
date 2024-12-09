package domain.plants;

import domain.Game;

public class WallNut extends Plant {
    private static final String name = "wallnut";
    public WallNut(int x, int y, Game game) {
        super(name);
        this.life = 4000;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }
}
