package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.util.Random;

/**
 * Class that represents the original zombies player machine, it only drops zombies randomly in the game
 */
public class ZombiesOriginal extends Machine {

    //** Attributes **//

    private final String[] zombieTypes = {"basic", "conehead", "buckethead", "brainstein", "ecizombie"};
    private int cantZombies;


    //** Constructor **//

    /**
     * Constructor of the original zombies player machine
     * @param game Game where the player is going to play
     */
    public ZombiesOriginal(Game game) {
        super(game);
        cantZombies = 0;
    }


    /**
     * Method that makes a move in the game, it drops zombies randomly in the game
     * @param game Game where the player is going to play
     */
    @Override
    public void makeMove(Game game) {
        if (cantZombies == 0 || cantZombies % 5 == 0) {
            try {
                Random rand = new Random();
                int row = rand.nextInt(5);
                game.addZombie("brainstein", 10, row);
                cantZombies++;
            } catch (PvZExceptions e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                Random rand = new Random();
                int row = rand.nextInt(5);
                int column = rand.nextInt(2) + 9;
                String zombieType = zombieTypes[rand.nextInt(zombieTypes.length)];
                game.addZombie(zombieType, column, row);
                cantZombies++;
            } catch (PvZExceptions e) {
                System.out.println("Error to put zombie: " + e.getMessage());
            }
        }
    }
}