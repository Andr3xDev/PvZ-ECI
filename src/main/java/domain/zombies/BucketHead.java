package domain.zombies;

import domain.Game;

import java.io.Serializable;

/**
 * BucketHead is a zombie that has a bucket on its head. It has 1100 life points and deals 100 damage.
 * It costs 200 suns.
 */
public class BucketHead extends Zombie implements Serializable {

    // Attributes
    private static final String name = "buckethead";


    // Constructor

    /**
     * Constructor of the BucketHead class.
     * @param y y-coordinate of the zombie.
     * @param game Game object.
     */
    public BucketHead(int y, Game game) {
        super(name);
        this.life = 1100;
        this.damage = 100;
        this.cost = 200;
        this.game = game;
        this.positionY = y;
        this.positionX = 10;
    }
}