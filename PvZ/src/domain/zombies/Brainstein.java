package domain.zombies;

import domain.economy.Brain;
import domain.Game;

public class Brainstein extends Zombie {
    private static final String name = "Brainstein";
    public Brainstein(int y, Game game) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = 7;
    }
    public void generateBrains() {
        Brain brain = new Brain();
        this.game.addBrains(brain);
    }
}
