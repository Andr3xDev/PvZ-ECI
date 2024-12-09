package domain.plants;

import domain.Game;
import domain.Unit;

/**
 * This class represents the Plant class.
 * It is an abstract class that represents all plants in the game.
 */
public abstract class Plant implements Unit {

    // Attributes
    private final String name;
    protected int life;
    protected int damage;
    protected int cost;
    protected int positionX;
    protected int positionY;
    protected Game game;


    // Constructor

    /**
     * Constructor of the Plant class.
     * @param name Name of the plant.
     */
    public Plant(String name ) {
        this.name = name;
    }


    // Methods

    /**
     * This method makes the plant take damage.
     * If the plant's life is less than or equal to 0, the plant dies.
     * @param dmg Damage to be taken.
     */
    @Override
    public void takeDamage(int dmg) {
        this.life -= dmg;
        if (this.life <= 0) {
            this.life = 0;
            this.die();
        }
    }

    /**
     * This method makes the plant die.
     */
    @Override
    public void die() {
        this.game.getUnit()[positionX][positionY] = null;
    }

    /**
     * This method returns the life of the plant.
     * @return Life of the plant.
     */
    @Override
    public int getLife() {
        return life;
    }

    /**
     * This method returns the damage to the plant.
     * @return Damage to the plant.
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * This method returns the cost of the plant.
     * @return Cost of the plant.
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * This method returns the x-coordinate of the plant.
     * @return x-coordinate of the plant.
     */
    public String getName() {
        return name;
    }
}