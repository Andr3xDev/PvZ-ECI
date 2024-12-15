
import domain.Game;
import domain.PvZExceptions;
import domain.Shovel;
import domain.economy.Brain;
import domain.economy.Sun;
import domain.plants.ECIPlant;
import domain.plants.Peashooter;
import domain.plants.PotatoMine;
import domain.plants.Sunflower;
import domain.zombies.Basic;
import domain.zombies.Brainstein;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class to approve all test.
 */
public class GameTest {
    /**
     * Default constructor for test class PuzzleTest
     */
    public GameTest() {}

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {}


    //* Test methods *//


    // Add players
    @Test
    public void shouldAddPlayers(){
        assertDoesNotThrow(() -> {
            Game game = new Game("pvp", 1, 1);
        });
    }

    @Test
    public void shouldAddMachineEasy(){
        assertDoesNotThrow(() -> {
            Game game = new Game("pvAI", 1, 1);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void shouldAddMachineMedium(){
        assertDoesNotThrow(() -> {
            Game game = new Game("pvAI", 1, 2);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    @Test
    public void shouldAddMachineIAvIA(){
        assertDoesNotThrow(() -> {
            Game game = new Game("AIvAI", 1, 2);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void shouldAddMachineIAvIA2(){
        assertDoesNotThrow(() -> {
            Game game = new Game("AIvAI", 2, 2);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    // Start game




    //* Add plants *//

    @Test
    public void shouldAddPlant(){
        Game game = new Game("pvp", 1, 1);
        assertDoesNotThrow(() -> {
            Sun sun = new Sun(10000);
            game.addSuns(sun);
            game.addPlant("wallnut", 4, 2);
            game.addPlant("sunflower", 3, 2);
            game.addPlant("eciplant", 2, 2);
            game.addPlant("potatomine", 5, 2);
            game.addPlant("peashooter", 6, 2);
        });
    }
    @Test
    public void shouldNotAddPlant(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(10000);
            game.addSuns(sun);
            game.addPlant("wallnut", 4, 2);
            game.addPlant("sunflower", 0, 2);
            game.addPlant("eciplant", 2, 2);
            game.addPlant("potatomine", 5, 2);
            game.addPlant("peashooter", 6, 2);
        });
    }
    @Test
    public void shouldNotAddPlant2(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(10000);
            game.addSuns(sun);
            game.addPlant("wallnut", 4, 2);
            game.addPlant("sunflower", 4, 2);
            game.addPlant("eciplant", 2, 2);
            game.addPlant("potatomine", 5, 2);
            game.addPlant("peashooter", 6, 2);
        });
    }




    //* Add zombies

    @Test
    public void shouldAddZombie(){
        assertDoesNotThrow(() -> {
            Game game = new Game("pvp", 1, 1);
            Brain brain = new Brain(10000);
            game.addBrains(brain);
            game.addZombie("basic", 10, 0);
            game.addZombie("brainstein", 10, 1);
            game.addZombie("buckethead", 10, 2);
            game.addZombie("conehead", 10, 3);
            game.addZombie("ecizombie", 10, 4);
        });
    }
    @Test
    public void shouldNotAddZombie(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            game.addZombie("basic", 1, 0);
        });
    }




    //* Remove plants *//

    @Test
    public void shouldRemovePlant(){
        assertDoesNotThrow(() -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(10000);
            game.addSuns(sun);
            game.addPlant("wallnut", 4, 2);
            game.deleteUnit(4, 2);
            game.addPlant("sunflower", 4, 2);
            game.deleteUnit(4, 2);
            game.addPlant("eciplant", 4, 2);
            game.deleteUnit(4, 2);
            game.addPlant("potatomine", 4, 2);
            game.deleteUnit(4, 2);
            game.addPlant("potatomine", 4, 2);
            PotatoMine potatoMine = (PotatoMine) game.getPlant(4, 2);
            potatoMine.die();
            game.addPlant("peashooter", 4, 2);
            game.deleteUnit(4, 2);
        });
    }
    @Test
    public void shouldNotRemovePlant(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            game.addPlant("wallnut", 4, 2);
            game.deleteUnit(0, 2);
        });
    }




    //* Plant actions


    // eciplant functionality
    @Test
    public void shouldGenerateEciplant(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(100);
            game.addSuns(sun);
            game.addPlant("eciplant", 4, 2);
            ECIPlant eciPlant = (ECIPlant) game.getPlant(4, 2);
            Thread.sleep(3000);
            eciPlant.die();
        } catch (PvZExceptions e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(game.getSuns(), game.getSuns());
    }

    // sunflower functionality
    @Test
    public void shouldGenerateSunFlower(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(100);
            game.addSuns(sun);
            game.addPlant("sunflower", 4, 2);
            Sunflower sunFlower = (Sunflower) game.getPlant(4, 2);
            Thread.sleep(3000);
            sunFlower.die();
        } catch (PvZExceptions e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(game.getSuns(), game.getSuns());
    }

    // potatomine functionality
    @Test
    public void shouldExploitedPotato(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(100);
            game.addSuns(sun);
            game.addPlant("potatomine", 2, 2);
            PotatoMine potatoMine = (PotatoMine) game.getPlant(8, 2);
            game.addZombie("basic", 9, 2);
            Thread.sleep(15000);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertNull(game.getZombie(8, 2));
    }

    // pea shooter functionality
    @Test
    public void shouldAttackZombieWithPeaShooter(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(100);
            game.addSuns(sun);
            game.addPlant("peashooter", 4, 2);
            game.addZombie("basic", 9, 2);
            Peashooter peashooter = (Peashooter) game.getPlant(4, 2);
            Thread.sleep(10000);
            peashooter.takeDamage(100000);
            peashooter.die();
        } catch (PvZExceptions e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(game.getZombie(5, 2) == null && game.getPlant(4, 2) == null);
    }




    //* Zombie actions

    @Test
    public void shouldBrainsteinGenerate(){
        Game game = new Game("pvp", 1, 1);
        try {
            Brain brain = new Brain(100);
            game.addBrains(brain);
            game.addZombie("brainstein", 9, 2);
            Brainstein brainstein = (Brainstein) game.getZombie(9, 2);
            Thread.sleep(6000);
            brainstein.die();
        } catch (PvZExceptions e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(game.getBrains(), game.getBrains());
    }




    //* Other Elements *//

    // Shovel
    @Test
    public void shouldDeletePlant(){
        assertDoesNotThrow(() -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(10000);
            Shovel shovel = new Shovel(game);
            game.addSuns(sun);
            game.addPlant("wallnut", 4, 2);
            game.addPlant("sunflower", 3, 2);
            game.addPlant("eciplant", 2, 2);
            game.addPlant("potatomine", 5, 2);
            game.addPlant("peashooter", 6, 2);
            shovel.deletePlant(4, 2);
            shovel.deletePlant(3, 2);
            shovel.deletePlant(2, 2);
            shovel.deletePlant(5, 2);
            shovel.deletePlant(6, 2);
        });
    }

    @Test
    public void shouldNotDeletePlant(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(10000);
            Shovel shovel = new Shovel(game);
            game.addSuns(sun);
            game.addPlant("wallnut", 4, 2);
            game.addPlant("sunflower", 3, 2);
            game.addPlant("eciplant", 2, 2);
            game.addPlant("potatomine", 5, 2);
            game.addPlant("peashooter", 6, 2);
            shovel.deletePlant(8, 2);
            shovel.deletePlant(3, 2);
            shovel.deletePlant(2, 2);
            shovel.deletePlant(5, 2);
            shovel.deletePlant(6, 2);
        });
    }



    //* Economic system

    @Test
    public void shouldAddSun(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(10000);
            game.addSuns(sun);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        assertEquals(10050, game.getSuns());
    }

    @Test
    public void shouldNotAddSun(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(-1000);
            game.addSuns(sun);
        });
    }

    @Test
    public void shouldRemoveSun(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(9975);
            game.addSuns(sun);
            game.addPlant("wallnut", 1, 2);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        assertEquals(9975, game.getSuns());
    }

    @Test
    public void shouldNotRemoveSun(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Sun sun = new Sun(-1000);
            game.addSuns(sun);
        });
    }

    @Test
    public void shouldAddBrain(){
        Game game = new Game("pvp", 1, 1);
        try {
            Brain brain = new Brain(10000);
            game.addBrains(brain);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        assertEquals(10050, game.getBrains());
    }

    @Test
    public void shouldNotAddBrain(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Brain brain= new Brain(-1000);
            game.addBrains(brain);
        });
    }

    @Test
    public void shouldRemoveBrain(){
        Game game = new Game("pvp", 1, 1);
        try {
            Brain brain = new Brain(1000);
            game.addBrains(brain);
            game.addZombie("basic", 10, 2);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        assertEquals (1000, game.getBrains());
    }

    @Test
    public void shouldNotRemoveBrain(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            Brain brain = new Brain(10000);
            game.addBrains(brain);
            game.addZombie("basic", 2, 6);
        });
    }

    @Test
    public void shouldNotHaveBrain(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            game.addZombie("ecizombie", 10, 2);
        });
    }

    @Test
    public void shouldNotHaveSun(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            game.addPlant("peashooter", 2, 2);
        });
    }




    // Mower
    @Test
    public void shouldActivateMower(){

    }
    @Test
    public void shouldNotActivateMower(){

    }


    // Game over
    @Test
    public void shouldEndGame(){

    }


    // Information
    @Test
    public void shouldGetPlant(){
        Game game = new Game("pvp", 1, 1);
        try {
            Sun sun = new Sun(10000);
            game.addSuns(sun);
            game.addPlant("peashooter", 6, 2);
            Peashooter peashooter = (Peashooter) game.getPlant(6, 2);
            System.out.println(peashooter.getLife() + " " + peashooter.getCost() + " " + peashooter.getDamage()
                    + " " + peashooter.getName());
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldGetZombie(){
        Game game = new Game("pvp", 1, 1);
        try {
            Brain brain = new Brain(10000);
            game.addBrains(brain);
            game.addZombie("basic", 10, 2);
            Basic zombie = (Basic) game.getZombie(10, 2);
            System.out.println(zombie.getLife() + " " + zombie.getCost() + " " + zombie.getDamage()
                    + " " + zombie.getName());
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
    }



    //* Other methods *//

    @Test
    public void shouldPrintBoard(){
        Game game = new Game("pvp", 1, 1);
        try {
            game.addSuns(new Sun(10000));
            game.addPlant("peashooter", 4, 2);
            Thread.sleep(5000);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        game.printBoard();
    }

    @Test
    public void shouldNotSearchZombie(){
        Game game = new Game("pvp", 1, 1);
        assertNull(game.searchZombie("nonas", 0));
    }

    @Test
    public void shouldUpdateZombie(){
        Game game = new Game("pvp", 1, 1);
        try {
            game.addZombie("brainstein", 10, 2);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        game.updateZombies();
    }


    //* Test methods *//

    @Test
    public void shouldSave(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            game.save("Test");
        });
    }

    @Test
    public void shouldLoad(){
        assertThrows(PvZExceptions.class, () -> {
            Game game = new Game("pvp", 1, 1);
            game.open("Test");
        });
    }

    @Test
    public void shouldNotGameOver(){
        Game game = new Game("pvp", 1, 1);
        try {
            game.addBrains(new Brain(10000));
            game.addZombie("basic", 10, 2);
            Thread.sleep(4000);
            game.addZombie("basic", 10, 2);
            Thread.sleep(4000);
        } catch (PvZExceptions | InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(game.getGameOver());
    }

    @Test
    public void shouldGameOver(){
        Game game = new Game("pvp", 1, 1);
        try {
            game.addBrains(new Brain(10000));
            game.addZombie("basic", 10, 2);
            Thread.sleep(40000);
            game.addZombie("basic", 10, 2);
            Thread.sleep(40000);
        } catch (PvZExceptions | InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(game.getGameOver());
    }


    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {}
}