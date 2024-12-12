package domain;

import domain.plants.Plant;

import java.io.Serializable;

/**
 * Class that represents the POOmBas bullet.
 * It implements the Bullet interface and the Runnable interface.
 * It has the attributes damage, posX, posY, board and isActive.
 * It has the methods run and move.
 */
public class POOmBas implements Bullet,Runnable, Serializable {

    // Attributes
    private int damage;
    private int posX;
    private int posY;
    private Game board;
    private boolean isActive = true;


    // Constructor

    /**
     * Constructor of the POOmBas class.
     * @param damage int that represents the damage from the bullet.
     * @param posX int that represents the x position of the bullet.
     * @param posY int that represents the y position of the bullet.
     * @param board Game that represents the game board.
     */
    public POOmBas(int damage, int posX, int posY, Game board) {
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.board.getBullets()[posX][posY] = this;
    }


    // Methods

    /**
     * This method creates a new thread to move the bullet.
     */
    @Override
    public void run() {
        while (isActive && posX >= 0) {
            try {
                Thread.sleep(1000); // Mover cada segundo
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * This method moves the bullet and checks if it hits a plant.
     */
    @Override
    public void move() {
        board.getBullets()[posX][posY] = null;

        if (posX - 1 >= 0 && board.getUnit()[posX - 1][posY] instanceof Plant) {
            Plant plant = (Plant) board.getUnit()[posX - 1][posY];
            plant.takeDamage(damage);
            isActive = false;
            this.board.getBullets()[posX][posY] = null;
            return;
        } else if (posX-1 == -1) {
            isActive = false;
            this.board.getBullets()[posX][posY] = null;
            return;
        }
        posX--;
        board.getBullets()[posX][posY] = this;
    }
}