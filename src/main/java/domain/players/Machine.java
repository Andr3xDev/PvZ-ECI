package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.io.Serializable;
import java.util.Random;

public abstract class Machine implements Player, Serializable, Runnable {

    private boolean isActive;
    private Game game;
    private String role;

    public Machine(Game game) {
        this.game = game;
        this.isActive = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (isActive) {
            try {
                Thread.sleep(1000);
                makeMove(game);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}