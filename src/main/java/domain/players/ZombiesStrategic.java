package domain.players;

import domain.Game;
import domain.PvZExceptions;

import java.util.Random;

/**
 * Class that represents the original zombies player machine, it drops zombies strategically in the game
 */
public class ZombiesStrategic extends Machine {

    //** Attributes **//

    private final String[] zombieTypes = {"basic", "conehead", "buckethead"};
    private int cantZombies;
    private Game game;
    private int attackPoint;


    //** Constructor **//

    /**
     * Constructor of the strategic zombies player machine
     * @param game Game where the player is going to play
     */
    public ZombiesStrategic(Game game) {
        super(game);
        this.cantZombies = 0;
        this.game = game;
        this.attackPoint = new Random().nextInt(5);
    }

    @Override
    public void makeMove(Game game) {
        if (cantZombies < 2 || cantZombies % 4 == 0) {
            try {
                int row = scanWeakness();
                game.addZombie("brainstein", 10, row);
                cantZombies++;
            } catch (PvZExceptions e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                if (this.game.getBrains() > 400) {
                    attack();
                } else {
                    Random rand = new Random();
                    int row = rand.nextInt(5);
                    int column = rand.nextInt(2) + 9;
                    game.addZombie("basic", column, row);
                    cantZombies++;
                }
            } catch (PvZExceptions e) {
                System.out.println("Error to put zombie: " + e.getMessage());
            }
        }
    }


    //** Methods **//

    private int scanWeakness() {
        int rowWithFewestZombies = -1;
        int fewestZombiesCount = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            int plantCount = 0;
            for (int j = 0; j < 5; j++) {
                if (this.game.getPlant(i,j) != null) {
                    plantCount++;
                }
            }
            if (plantCount < fewestZombiesCount) {
                fewestZombiesCount = plantCount;
                rowWithFewestZombies = i;
            }
        }
        return rowWithFewestZombies;
    }


    private void attack() {
        try {
            Random rand = new Random();
            String zombieType = zombieTypes[rand.nextInt(zombieTypes.length)];
            game.addZombie(zombieType, 9, attackPoint);
            cantZombies++;
        } catch (PvZExceptions e) {
            System.out.println("Error to put zombie: " + e.getMessage());
        }
    }
}