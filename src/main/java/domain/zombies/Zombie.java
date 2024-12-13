package domain.zombies;

import domain.Game;
import domain.PvZExceptions;
import domain.Unit;
import domain.plants.Plant;

import java.io.Serializable;

/**
 * Represents a zombie in the game.
 * It has a name, life points, damage points, cost, position X, position Y and a game.
 * It's the abstract zombie class.
 */
public abstract class Zombie implements Unit, Runnable, Serializable {

    // Attributes
    private String name;
    protected int life;
    protected int damage;
    protected int cost;
    protected int positionX;
    protected int positionY;
    protected Game game;
    private boolean isActive = true;
    private transient Thread thread;


    // Constructor

    /**
     * Constructor of the Zombie class.
     * @param name The name of the zombie.
     */
    public Zombie(String name) {
        this.name = name;

        thread = new Thread(this);
        thread.start();
    }


    // Methods

    /**
     * Makes the zombie die. It sets the zombie as inactive and removes it from the game.
     */
    @Override
    public void die() {
        this.game.getUnit()[positionX][positionY] = null;
        this.isActive = false;
    }

    /**
     * Makes the zombie take damage. It decreases the life points of the zombie.
     * If the life points are less than or equal to 0, the zombie dies.
     * @param dmg The damage points to take.
     */
    @Override
    public void takeDamage(int dmg) {
        this.life -= dmg;
        if (this.life <= 0) {
            this.die();
        }
    }

    /**
     * Makes the zombie move. It moves the zombie to the left.
     * If there is a plant in front of the zombie, the zombie attacks it.
     * @throws PvZExceptions If the zombie can't move.
     */
    public void move() throws PvZExceptions {
        if (isActive && positionX > 0) {
            if (!(game.getUnit()[positionX - 1][positionY] instanceof Plant)) {
                game.getUnit()[positionX][positionY] = null;
                positionX--;
                game.getUnit()[positionX][positionY] = this;
            }
        } else if (isActive && positionX == 0) {
            game.setGameOver();
        }
    }

    /**
     * Makes the zombie attack. It attacks the plant in front of the zombie.
     * If there is a plant in front of the zombie, the plant takes damage.
     * @throws PvZExceptions If the zombie can't attack.
     */
    protected void attack() throws PvZExceptions {
        if (positionX > 0 && game.getUnit()[positionX - 1][positionY] instanceof Plant) {
            Plant plant = (Plant) game.getUnit()[positionX - 1][positionY];
            plant.takeDamage(this.damage);
        }
    }

    /**
     * This method create the thead. It makes the zombie move and attack.
     * It sleeps 2.5 seconds between each move and attack.
     * It sleeps 0.5 seconds after each attack.
     */
    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(2500);
                move();
                attack();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (PvZExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Gets the position X of the zombie.
     * @return The position X of the zombie.
     */
    @Override
    public int getLife() {
        return this.life;
    }

    /**
     * Gets the damage the zombie.
     * @return The damage from the zombie.
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * Gets the cost of the zombie.
     * @return The cost of the zombie.
     */
    @Override
    public int getCost() {
        return this.cost;
    }

    /**
     * Gets the name of the zombie.
     * @return The name of the zombie.
     */
    public String getName(){return this.name;}
}