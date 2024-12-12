package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.io.Serializable;
import java.util.Random;

public class Machine implements Player, Serializable,Runnable {
    private boolean isActive = true;
    private Game game;
    public Machine(Game game) {
        this.game = game;
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void makeMove(Game game) {
        Random random = new Random();
        int row = random.nextInt(5);
        int col = random.nextInt(8,11);

        String[] zombieTypes = {"basic", "conehead", "buckethead","brainstein","ecizombie"};

        String zombieType = zombieTypes[random.nextInt(zombieTypes.length)];

        try {
            game.addZombie(zombieType, col, row);
        } catch (PvZExceptions e) {
            System.out.println("Error al colocar un zombie: " + e.getMessage());
        }
    }
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
