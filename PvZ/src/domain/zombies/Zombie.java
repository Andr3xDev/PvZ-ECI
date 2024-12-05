package domain.zombies;

import domain.Game;
import domain.Unit;
import domain.plants.Plant;

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

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void die() {
        this.game.getUnit()[positionX][positionY] = null;
        this.isActive = false;
    }

    @Override
    public void takeDamage(int dmg) {
        this.life -= dmg;
        if (this.life <= 0) {
            this.die();
        }
    }

    public void move() {
        if (isActive && positionX > 0) {
            // Si no hay una planta delante, avanza
            if (!(game.getUnit()[positionX - 1][positionY] instanceof Plant)) {
                game.getUnit()[positionX][positionY] = null;
                positionX--;
                game.getUnit()[positionX][positionY] = this;
            }
        }
    }

    protected void attack() {
        if (positionX > 0 && game.getUnit()[positionX - 1][positionY] instanceof Plant) {
            Plant plant = (Plant) game.getUnit()[positionX - 1][positionY];
            plant.takeDamage(this.damage); // La planta recibe daño
            if (plant.getLife() <= 0) {
                game.deletePlant(positionX - 1, positionY); // Elimina la planta si muere
            }
        }
    }

    @Override
    public void run() {
        Thread attackThread = new Thread(() -> {
            while (isActive) {
                try {
                    Thread.sleep(500); // Golpea cada 0.5 segundos
                    attack();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        attackThread.start();

        while (isActive) {
            try {
                Thread.sleep(2500); // Mueve cada 2.5 segundos
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Detener el hilo de ataque cuando el zombie ya no esté activo
        attackThread.interrupt();
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getCost() {
        return this.cost;
    }
}



