package domain.zombies;

import domain.Game;

import java.io.Serializable;

/**
 * Class to represent a basic zombie.
 * It costs 50 sun points, has 100 life points and does 100 damage points.
 * It moves 1 cell per turn, nothing else.
 */
public class Basic extends Zombie implements Serializable {

    // Attributes
    private static final String name = "basic";


    // Constructor

    /**
     * Constructor of the basic zombie.
     * @param y The initial position of the zombie.
     * @param game The game where the zombie is going to be in.
     */
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