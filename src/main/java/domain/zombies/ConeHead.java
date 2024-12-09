package domain.zombies;

import domain.Game;

/**
 * Represents a ConeHead zombie in the game.
 * It costs 125 sun points, has 370 life points and deals 100 damage points.
 * It's the basic zombie with a cone on its head.
 */
public class ConeHead extends Zombie {

    // Attributes
    private static final String name = "conehead";


    // Constructor

    /**
     * Constructor of the ConeHead class.
     * @param y The position Y of the zombie.
     * @param game The game where the zombie belongs.
     */
    public ConeHead(int y, Game game) {
        super(name);
        this.life = 370;
        this.damage = 100;
        this.cost = 125;
        this.game = game;
        this.positionY = y;
        this.positionX = 10;
    }
}