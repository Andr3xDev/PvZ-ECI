package domain.zombies;

import domain.PvZExceptions;
import domain.economy.Brain;
import domain.Game;

/**
 * Class that represents the Brainstein zombie.
 * This costs 50 suns and has 300 life points.
 * It moves until the start of the grass and keep the position generating brains.
 */
public class Brainstein extends Zombie implements Runnable{

    // Attributes
    private static final String name = "brainstein";
    private boolean isActive = true;


    // Constructor

    /**
     * Constructor method of Brainstein.
     * @param y Position in Y of the zombie.
     * @param game Game where the zombie is going to be in.
     */
    public Brainstein(int y, Game game) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = 10;

    }


    // Methods

    /**
     * This method creates a new thread for the Brainstein zombie.
     */
    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(1000);
                move();
            } catch (InterruptedException | PvZExceptions e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * This method moves the Brainstein zombie.
     * If the zombie has 0 life points, it is set as inactive.
     * If the zombie is active and its position is greater than 7, it moves to the left.
     * If the zombie is not active, it generates a brain.
     * @throws PvZExceptions Exception that can be thrown.
     */
    @Override
    public void move() throws PvZExceptions {
        if(this.getLife() <= 0){
            this.isActive = false;
        }
        else if (isActive && positionX > 7){
            game.getUnit()[positionX][positionY] = null;
            positionX--;
            game.getUnit()[positionX][positionY] = this;
        }else {
            Brain brain = new Brain(25);
            this.game.addBrains(brain);
        }
    }
}