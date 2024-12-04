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
        this.board.getBullets()[posX][posY] = this; // Registrar bala en el tablero
    }

    @Override
    public void run() {
        while (isActive && posX < 11) {
            try {
                Thread.sleep(1000); // Mover cada segundo
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void move() {
        // Eliminar bala de la posición actual
        board.getBullets()[posX][posY] = null;

        // Verificar si hay un zombie adelante
        if (posX + 1 < 11 && board.getUnit()[posX + 1][posY] instanceof Zombie) {
            Zombie zombie = (Zombie) board.getUnit()[posX + 1][posY];
            zombie.takeDamage(damage);
            isActive = false; // La bala se destruye al impactar
            this.board.getBullets()[posX][posY] = null;
            return;
        } else if (posX+1 == 10) {
            isActive = false; // La bala se destruye al impactar
            this.board.getBullets()[posX][posY] = null;
            return;
        }
        posX++;
        board.getBullets()[posX][posY] = this;
    }
}


