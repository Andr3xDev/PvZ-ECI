package domain.zombies;

import domain.Game;

public class Basic extends Zombie {
    private static final String name = "basic";
    public Basic(int y, Game game) {
        super(name);
        this.life = 100;
        this.damage = 100;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = 10;
    }

}
