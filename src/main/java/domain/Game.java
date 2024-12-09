package domain;

import domain.economy.Brain;
import domain.economy.Sun;
import domain.plants.*;
import domain.zombies.*;

import java.io.*;

public class Game {

    //* Attributes *//

    private final Unit[][] unit;
    private int brains;
    private int suns;
    private final Bullet[][] bullets;
    private boolean isActive = true;


    //* Constructors *//

    /**
     * Constructor for the Game, here we initialize the board and the economy of the game
     */
    public Game() {
        bullets = new Bullet[11][5];
        unit = new Unit[11][5];
        this.suns = 50;
        this.brains = 50;
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
        if (validatePosition("plant", posX, posY)) {
            isPositionEmpty(posX,posY);
            Plant plant = searchPlant(plantName,posX,posY);
            if (plant.getCost() <= this.suns) {
                unit[posX][posY] = plant;
                suns -= plant.getCost();
            }else {
                plant.die();
                throw new PvZExceptions(PvZExceptions.NO_SUNS_EXCEPTION);
            }
        } else {
            throw new PvZExceptions(PvZExceptions.PLANT_OUT_RANGE_EXCEPTION);
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
        if (validatePosition("zombie", posX, posY)) {
            Zombie zombie = searchZombie(zombieName,posY);
            if (zombie.getCost() <= this.brains) {
                unit[posX][posY] = zombie;
                brains -= zombie.getCost();
            }else{
                zombie.die();
                throw new PvZExceptions(PvZExceptions.NO_BRAINS_EXCEPTION);
            }
        } else {
            throw new PvZExceptions(PvZExceptions.ZOMBIE_OUT_RANGE_EXCEPTION);
        }
    }


    /**
     * Method to delete a plant from the board in the given position.
     * @param posX x position of the plant
     * @param posY y position of the plant
     */
    public void deleteUnit(int posX, int posY) throws PvZExceptions {
        if (unit[posX][posY] != null){
            unit[posX][posY] = null;
        } else {
            throw new PvZExceptions(PvZExceptions.NO_UNIT_EXCEPTION);
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

        System.out.println("Bullets:");
        for (int y = 0; y < bullets[0].length; y++) {
            for (int x = 0; x < bullets.length; x++) {
                if (bullets[x][y] != null) {
                    System.out.print("B ");
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
     * Method to update the game status
     */
    public void save(String nameFile)throws PvZExceptions{
        ObjectOutputStream salida = null;
        try {
            salida = new ObjectOutputStream(new FileOutputStream(nameFile));
            salida.writeObject(this);
            salida.flush();
            System.out.println("Partida guardada con éxito. Ruta: " + new File(nameFile).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PvZExceptions(PvZExceptions.SAVE_EXCEPTION);
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Method to open a game from a file
     */
    public static Game open(String nameFile) throws PvZExceptions{
        Game partidaCargada = null;
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nameFile));
            partidaCargada = (Game) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PvZExceptions(PvZExceptions.OPEN_EXCEPTION);
        }
        return partidaCargada;
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
            return posX >= 1 && posX <= 8;
        } else if (unit.equals("zombie")) {
            return posX >= 9 && posX < 11;
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


    //* Getters and Setters *//

    public Unit[][] getUnit() {
        return unit;
    }
    public int getSuns(){return this.suns;}

    public int getBrains() {
        return brains;
    }
}
