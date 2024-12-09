package domain;

import domain.plants.Plant;


public class POOmBas implements Bullet,Runnable{
    private int damage;
    private int posX;
    private int posY;
    private Game board;
    private boolean isActive = true;

    public POOmBas(int damage, int posX, int posY, Game board) {
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.board.getBullets()[posX][posY] = this;
    }

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

