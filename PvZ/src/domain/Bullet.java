package domain;

import domain.zombies.Zombie;

public class Bullet implements Runnable {
    private int damage;
    private int posX;
    private int posY;
    private Game board;
    private boolean isActive = true;

    public Bullet(int damage, int posX, int posY, Game board) {
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
    }

    public void makeDamage() {
        if (board.getUnit()[posX + 1][posY] != null
                && board.getUnit()[posX + 1][posY] instanceof Zombie) {
            board.getUnit()[posX + 1][posY].takeDamage(this.damage);
            this.isActive = false;
        }
    }

    public void move() {
        if (isActive) {
            makeDamage();
            if (isActive) {
                posX++;
                if (posX >= 8) {
                    isActive = false;
                }
            }
        }
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(1000);
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }
}

