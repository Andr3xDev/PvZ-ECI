package domain;

import domain.economy.Brain;
import domain.economy.Sun;
import domain.plants.*;
import domain.players.Human;
import domain.players.Machine;
import domain.players.Player;
import domain.zombies.*;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {

    //* Attributes *//

    private final Unit[][] unit;
    private int brains;
    private int suns;
    private final Bullet[][] bullets;
    private final boolean isActive = true;
    private final LawnMower[][] lawnMowers;
    private final ArrayList<Player> players;
    private String gameMode;
    private static final Logger logger = Logger.getLogger(Game.class.getName());


    //* Constructors *//

    /**
     * Constructor for the Game, here we initialize the board and the economy of the game
     */
    public Game(String gameMode) {
        players = new ArrayList<>();
        this.gameMode = gameMode;
        lawnMowers = new LawnMower[11][5];
        bullets = new Bullet[11][5];
        unit = new Unit[11][5];
        this.suns = 50;
        this.brains = 50;
        generatePlayers(gameMode);
        initializeLawnMowers();
        try {
            FileHandler fileHandler = new FileHandler("game.log", true); // true para append
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.severe("No se pudo configurar el FileHandler para el logger.");
        }
    }


    private void generatePlayers(String gameMode){
        assert players != null;
        switch (gameMode){
            case "pvp" -> {
                players.add(new Human());
                players.add(new Human());
            }
            case "pvAI" -> {
                players.add(new Human());
                players.add(new Machine(this,"zombie"));
            }
            case "AIvAI" -> {
                players.add(new Machine(this,"plant"));
                players.add(new Machine(this,"zombie"));
            }
        }
    }


    //* Methods *//

    /**
     * Method to add a plant to the board in the given position.
     * @param plantName the name of the plant to add
     * @param posX x position of the plant
     * @param posY y position of the plant
     * @throws PvZExceptions if the position is out of range for the plant or if there is already
     * a unit in the cell or if there are not enough suns to buy the plant
     */
    public void addPlant(String plantName, int posX, int posY) throws PvZExceptions {
        logger.info("Intentando añadir una planta: " + plantName + " en (" + posX + ", " + posY + ")");
        try {
            if (validatePosition("plant", posX, posY)) {
                isPositionEmpty(posX, posY);
                Plant plant = searchPlant(plantName, posX, posY);
                if (plant.getCost() <= this.suns) {
                    unit[posX][posY] = plant;
                    suns -= plant.getCost();
                    logger.info("Planta añadida exitosamente: " + plantName + " en (" + posX + ", " + posY + ")");
                } else {
                    logger.warning("No hay suficientes soles para añadir la planta: " + plantName);
                    plant.die();
                    throw new PvZExceptions(PvZExceptions.NO_SUNS_EXCEPTION);
                }
            } else {
                logger.warning("Posición inválida para la planta: (" + posX + ", " + posY + ")");
                throw new PvZExceptions(PvZExceptions.PLANT_OUT_RANGE_EXCEPTION);
            }
        } catch (PvZExceptions e) {
            logger.severe("Error al añadir planta: " + e.getMessage());
            throw e;
        }
    }


    /**
     * Method to add a zombie to the board in the given position.
     * @param zombieName the name of the zombie to add
     * @param posX x position of the zombie
     * @param posY y position of the zombie
     * @throws PvZExceptions if the position is out of range for the zombie or if there is already
     */
    public void addZombie(String zombieName, int posX, int posY) throws PvZExceptions {
        logger.info("Intentando añadir un zombie: " + zombieName + " en (" + posX + ", " + posY + ")");
        try {
            if (validatePosition("zombie", posX, posY)) {
                Zombie zombie = searchZombie(zombieName, posY);
                if (zombie.getCost() <= this.brains) {
                    unit[posX][posY] = zombie;
                    brains -= zombie.getCost();
                    logger.info("Zombie añadido exitosamente: " + zombieName + " en (" + posX + ", " + posY + ")");
                } else {
                    zombie.die();
                    logger.warning("No hay suficientes cerebros para añadir el zombie: " + zombieName);
                    throw new PvZExceptions(PvZExceptions.NO_BRAINS_EXCEPTION);
                }
            } else {
                logger.warning("Posición inválida para el zombie: (" + posX + ", " + posY + ")");
                throw new PvZExceptions(PvZExceptions.ZOMBIE_OUT_RANGE_EXCEPTION);
            }
        } catch (PvZExceptions e) {
            logger.severe("Error al añadir zombie: " + e.getMessage());
            throw e; // Re-lanzar la excepción para manejarla en el nivel superior
        }
    }


    /**
     * Method to delete a plant from the board in the given position.
     * @param posX x position of the plant
     * @param posY y position of the plant
     */
    public void deleteUnit(int posX, int posY) throws PvZExceptions {
        try {
            if (unit[posX][posY] != null) {
                unit[posX][posY] = null;
                logger.info("Unidad eliminada exitosamente en la posición (" + posX + ", " + posY + ")");
            } else {
                logger.warning("No hay unidad para eliminar en la posición (" + posX + ", " + posY + ")");
                throw new PvZExceptions(PvZExceptions.NO_UNIT_EXCEPTION);
            }
        } catch (PvZExceptions e) {
            logger.severe("Error al eliminar unidad: " + e.getMessage());
            throw e; // Re-lanzar la excepción para que sea manejada a nivel superior
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe("Posición fuera de los límites del tablero: (" + posX + ", " + posY + ")");
            throw new PvZExceptions("Posición fuera de los límites del tablero.");
        }
    }


    /**
     * Method to add suns to the economy of the game
     * @param sun Quantity of suns to add
     */
    public void addSuns(Sun sun) {
        this.suns += sun.getValue();
    }


    /**
     * Method to add brains to the economy of the game
     * @param brain Quantity of brains to add
     */
    public void addBrains(Brain brain) throws PvZExceptions {
        if (brain.getValue() < 0) {
            throw new PvZExceptions(PvZExceptions.NEGATIVE_COIN_EXCEPTION);
        }
        this.brains += brain.getValue();
    }


    /**
     * Method to get the bullets in the board
     * @return matrix of bullets
     */
    public Bullet[][] getBullets() {
        return bullets;
    }


    /**
     * Method to get the status of the game as a matrix printed in the console
     */
    public void printBoard() {
        System.out.println("Game Board:");
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 11; x++) {
                if (unit[x][y] != null) {
                    System.out.print(unit[x][y].getClass().getSimpleName().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }

        System.out.println("LawnMowers:");
        for (int y = 0; y < lawnMowers[0].length; y++) {
            for (LawnMower[] lawnMower : lawnMowers) {
                if (lawnMower[y] != null) {
                    System.out.print("L ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     * Method to get a plant from the board in the given position to added to the board
     */
    public Plant searchPlant(String plant,int posX,int posY){
        return switch (plant) {
            case "peashooter" -> new Peashooter(posX, posY, this);
            case "sunflower" -> new Sunflower(posX, posY, this);
            case "wallnut" -> new WallNut(posX, posY, this);
            case "potatomine" -> new PotatoMine(posX, posY, this);
            case "eciplant" -> new ECIPlant(posX, posY, this);
            default -> null;
        };
    }


    /**
     * Method to get a zombie from the board in the given position to added to the board
     */
    public Zombie searchZombie(String zombie,int posY){
        return switch (zombie) {
            case "basic" -> new Basic(posY, this);
            case "brainstein" -> new Brainstein(posY, this);
            case "conehead" -> new ConeHead(posY, this);
            case "buckethead" -> new BucketHead(posY, this);
            case "ecizombie" -> new ECIZombie(posY, this);
            default -> null;
        };
    }


    /**
     * Method to update the game board for every time unit
     */
    public void updateZombies() {
        for (int i = 0; i < unit.length; i++) {
            for (int j = 0; j < unit[i].length; j++) {
                if (unit[i][j] instanceof Zombie zombie && zombie.getLife() <= 0) {
                    unit[i][j] = null;
                }
            }
        }
    }


    /**
     * Method to get a unit plant from the board in the given position
     */
    public Plant getPlant(int i, int j) {
        Unit unit = getUnit()[i][j];
        if (unit instanceof Plant) {
            return (Plant) unit;
        }
        return null;
    }


    /**
     * Method to get a unit zombie from the board in the given position
     */
    public Zombie getZombie(int i, int j) {
        Unit unit = getUnit()[i][j];
        if(unit instanceof Zombie) {
            return (Zombie) unit;
        }
        return null;
    }


    /**
     * Method to validate the position of the unit in the board from the given position
     * @param unit the unit type to validate
     * @param posX x position of the unit
     * @param posY y position of the unit
     * @return true if the position is valid, false otherwise
     */
    private boolean validatePosition(String unit, int posX, int posY) {
        if (unit.equals("plant")) {
            return posX >= 1 && posX <= 8 && posY >= 0 && posY <= 4;
        } else if (unit.equals("zombie")) {
            return posX >= 9 && posX < 11 && posY >= 0 && posY <= 4;
        }
        return false;
    }


    /**
     * Method to validate if the position is empty
     * @param posX x position of the unit
     * @param posY y position of the unit
     * @throws PvZExceptions if the position is not empty
     */
    private void isPositionEmpty(int posX, int posY) throws PvZExceptions {
        if (unit[posX][posY] != null) {
            throw new PvZExceptions(PvZExceptions.EXISTENT_UNIT_EXCEPTION);
        }
    }
    private void initializeLawnMowers(){
        for (int i = 0; i < 5; i++) {
            this.lawnMowers[0][i] = new LawnMower(this,i);
        }
    }


    //* Getters and Setters *//

    public Unit[][] getUnit() {
        return unit;
    }
    public LawnMower[][] getLawnMowers(){return lawnMowers;}
    public int getSuns(){return this.suns;}

    public int getBrains() {
        return brains;
    }



    //* Persistence *//

    /**
     * Method to update the game status
     */
    public void save(String nameFile)throws PvZExceptions{
        ObjectOutputStream exitFile = null;
        try {
            exitFile = new ObjectOutputStream(new FileOutputStream(nameFile));
            exitFile.writeObject(this);
            exitFile.flush();
            System.out.println("Game saved successfully. Path: " + new File(nameFile).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PvZExceptions(PvZExceptions.SAVE_EXCEPTION);
        } finally {
            if (exitFile != null) {
                try {
                    exitFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Method to open a game from a file
     */
    public static void open(String nameFile) throws PvZExceptions{
        Game loadGame;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(nameFile));
            loadGame = (Game) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PvZExceptions(PvZExceptions.OPEN_EXCEPTION);
        }
    }
}
