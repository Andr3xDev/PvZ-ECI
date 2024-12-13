package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.io.Serializable;
import java.util.Random;

public abstract class Machine implements Player, Serializable, Runnable {

    private boolean isActive = true;
    private Game game;
    private String role;

    public Machine(Game game) {
        this.game = game;
    }

    public abstract void makeMove(Game game);

    public void run() {
        while (isActive) {
            try {
                makeMove(game);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}