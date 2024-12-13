package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.util.Random;

public class PlantsStrategic extends Machine {

    private String[] plantTypes = {"peashooter", "wallnut", "sunflower", "eciplant", "potatomine"};
    private final Game game;


    public PlantsStrategic(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void makeMove(Game game) {
        needSuns();
        Random random = new Random();
        int select = random.nextInt(2);
        switch (select) {
            case 0:
                Random random1 = new Random();
                int select1 = random1.nextInt(4) + 2;
                needWalls(select1);
                break;
            case 1:
                Random random2 = new Random();
                int select2 = random2.nextInt(4) + 5;
                needPotato(select2);
                break;
        }
    }

    private void needSuns() {
        for (int i = 0; i < 5; i++) {
            if (game.getPlant(i, 1) == null) {
                Random random = new Random();
                int select = random.nextInt(2);
                try {
                    if (select == 0) {
                        game.addPlant("sunflower", 1, i);
                    } else {
                        game.addPlant("eciplant", 1, i);
                    }
                } catch (PvZExceptions e) {
                    System.out.println();
                }
            }
        }
    }

    private void needWalls(int col) {
        for (int i = 0; i < 5; i++) {
            if (game.getPlant(col, i) == null) {
                try {
                    game.addPlant("wallnut", col, i);
                } catch (PvZExceptions e) {
                    System.out.println();
                }
            }
        }
    }

    private void needPotato(int col) {
        for (int i = 0; i < 5; i++) {
            if (game.getPlant(col, i) == null) {
                try {
                    game.addPlant("potatomine", col, i);
                } catch (PvZExceptions e) {
                    System.out.println();
                }
            }
        }
    }
}