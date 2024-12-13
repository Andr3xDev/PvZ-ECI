package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.util.Random;

public class PlantsIntelligent extends Machine {

    private final Game game;

    public PlantsIntelligent(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void makeMove(Game game) {
        needSuns();
        Random random = new Random();
        int select = random.nextInt(4);
        switch (select) {
            case 0:
                needShooters();
                break;
            case 1:
                Random random1 = new Random();
                int select1 = random1.nextInt(2) + 4;
                needWalls(select1);
                break;
            case 2:
                needEci();
                break;
            case 3:
                Random random2 = new Random();
                int select2 = random2.nextInt(2) + 6;
                needPotato(select2);
                break;
        }
    }

    private void needSuns() {
        for (int i = 0; i < 5; i++) {
            if (game.getPlant(2, i) == null) {
                try {
                    game.addPlant("sunflower", 2, i);
                } catch (PvZExceptions e) {
                    System.out.println();
                }
            }
        }
    }

    private void needEci() {
        for (int i = 0; i < 5; i++) {
            if (game.getPlant(i, 1) == null) {
                try {
                    game.addPlant("eciplant", 1, i);
                } catch (PvZExceptions e) {
                    System.out.println();
                }
            }
        }
    }

    private void needShooters() {
        for (int i = 0; i < 5; i++) {
            if (game.getPlant(3, i) == null) {
                try {
                    game.addPlant("peashooter", 3, i);
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