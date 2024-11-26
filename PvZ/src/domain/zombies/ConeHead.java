package domain.zombies;

import domain.Game;

public class ConeHead extends Zombie {
    private static final String name = "ConeHead";
    public ConeHead(int y, Game game) {
        super(name);
        this.life = 370;
        this.damage = 100;
        this.cost = 125;
        this.game = game;
        this.positionY = y;
        this.positionX = 7;
    }
}
