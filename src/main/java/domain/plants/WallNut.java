package domain.plants;

import domain.Game;

/**
 * WallNut is a plant that has 4000 life points. It is a defensive plant.
 * It costs 50 suns and has 4000 life points, does not have any special abilities.
 */
public class WallNut extends Plant {

    // Attributes
    private static final String name = "wallnut";


    // Constructor

    /**
     * Constructor of the WallNut class.
     * @param x x-coordinate of the plant.
     * @param y y-coordinate of the plant.
     * @param game Game object.
     */
    public WallNut(int x, int y, Game game) {
        super(name);
        this.life = 4000;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }
}
