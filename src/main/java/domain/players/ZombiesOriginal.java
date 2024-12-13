package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.util.Random;

public class ZombiesOriginal extends Machine {

    private final String[] zombieTypes = {"basic", "conehead", "buckethead", "brainstein", "ecizombie"};
    private int cantZombies;

    public ZombiesOriginal(Game game) {
        super(game);
        cantZombies = 0;
    }

    @Override
    public void makeMove(Game game) {
        if (cantZombies % 5 == 0) {
            try {
                Random rand = new Random();
                int row = rand.nextInt(5);
                game.addZombie("brainstein", 10, row);
            } catch (PvZExceptions e) {
                throw new RuntimeException(e);
            }
        }
        // Select a random zombie type
    }
}