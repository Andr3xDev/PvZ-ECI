package domain.zombies;

import domain.PvZExceptions;
import domain.economy.Brain;
import domain.Game;

public class Brainstein extends Zombie implements Runnable{
    private static final String name = "brainstein";
    private boolean isActive = true;
    public Brainstein(int y, Game game) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.game = game;
        this.positionY = y;
        this.positionX = 10;

    }
    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(1000); // Mueve cada segundo
                move();
            } catch (InterruptedException | PvZExceptions e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
}
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
