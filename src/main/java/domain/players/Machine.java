package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.io.Serializable;
import java.util.Random;

public class Machine implements Player, Serializable,Runnable {
    private boolean isActive = true;
    private Game game;
    private String  role;
    public Machine(Game game,String role) {
        this.role = role;
        this.game = game;
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void makeMove(Game game) {
        Random random = new Random();
        int row = random.nextInt(5);
        if(role == "zombie") {
            int col = random.nextInt(8, 11);

            String[] zombieTypes = {"basic", "conehead", "buckethead", "brainstein", "ecizombie"};

            String zombieType = zombieTypes[random.nextInt(zombieTypes.length)];

            try {
                game.addZombie(zombieType, col, row);
            } catch (PvZExceptions e) {
                System.out.println("Error al colocar un zombie: " + e.getMessage());
            }
        } else if (role == "plant") {
            int col = random.nextInt(4);
            String[] plantTypes = {"peashooter", "wallnut", "sunflower", "eciplant", "potatomine"};

            String plantType = plantTypes[random.nextInt(plantTypes.length)];

            try {
                game.addPlant(plantType, col, row);
            } catch (PvZExceptions e) {
                System.out.println("Error al colocar una planta: " + e.getMessage());
            }
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
