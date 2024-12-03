package domain.zombies;

import domain.Game;
import domain.Unit;

public abstract class Zombie implements Unit, Runnable {
    private String name;
    protected int life;
    protected int damage;
    protected int cost;
    protected int positionX;
    protected int positionY;
    protected Game game;
    private boolean isActive = true;

    public Zombie(String name) {
        this.name = name;
    }

    @Override
    public void die() {
        this.game.getUnit()[positionX][positionY] = null;
        this.isActive = false;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void takeDamage(int dmg) {
        this.life -= dmg;
        if (this.life <= 0) {
            this.die();
        }
    }

    public void makeDamage() {
        if (positionX - 1 >= 0 && game.getUnit()[positionX - 1][positionY] != null) {
            Unit target = game.getUnit()[positionX - 1][positionY];
            target.takeDamage(this.damage);

            if (target.getLife() <= 0) {
                game.getUnit()[positionX - 1][positionY] = null;
            }
        }
    }

    public void move() {
        if (isActive) {
            if (positionX - 1 >= 0 && game.getUnit()[positionX - 1][positionY] == null) {
                game.getUnit()[positionX][positionY] = null;
                positionX--;
                game.getUnit()[positionX][positionY] = this;
            } else {
                makeDamage();
            }
        }
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(2500);
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public String getName() {
        return name;
    }
}


