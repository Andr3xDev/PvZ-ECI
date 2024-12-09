package domain;

import domain.zombies.Zombie;

/**
 * Class that represents a Pea bullet
 * It implements the Bullet interface and the Runnable interface
 */
public class Pea implements Bullet,Runnable {

    // Attributes
    private final int damage;
    private int posX;
    private final int posY;
    private final Game board;
    private boolean isActive = true;


    // Constructor

    /**
     * Constructor of the Pea class
     * @param damage Damage from the Pea
     * @param posX X position of the Pea
     * @param posY Y position of the Pea
     * @param board Game where the Pea is
     */
    public Pea(int damage, int posX, int posY, Game board) {
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.board.getBullets()[posX][posY] = this;
    }


    // Methods

    /**
     * This method creates a new thread to move the Pea bullet in the game and check if it hits a Zombie.
     */
    @Override
    public void run() {
        while (isActive && posX < 11) {
            try {
                Thread.sleep(1000);
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * This method moves the Pea bullet in the game and checks if it hits a Zombie.
     */
    @Override
    public void move() {
        board.getBullets()[posX][posY] = null;

        if (posX + 1 < 11 && board.getUnit()[posX + 1][posY] instanceof Zombie) {
            Zombie zombie = (Zombie) board.getUnit()[posX + 1][posY];
            zombie.takeDamage(damage);
            isActive = false;
            this.board.getBullets()[posX][posY] = null;
            return;
        } else if (posX+1 == 10) {
            isActive = false;
            this.board.getBullets()[posX][posY] = null;
            return;
        }
        posX++;
        board.getBullets()[posX][posY] = this;
    }
}