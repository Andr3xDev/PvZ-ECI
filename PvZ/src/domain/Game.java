package domain;

import domain.economy.Brain;
import domain.economy.Sun;
import domain.plants.*;
import domain.zombies.*;

public class Game {
    private Unit[][] unit;
    private int brains;
    private int suns;
    private Bullet[][] bullets;
    private boolean isActive = true;
    public Game() {
        bullets = new Bullet[11][5];
        unit = new Unit[11][5];
        this.suns = 50;
        this.brains = 50;

    }

    public void addPlant(String plantName, int posX, int posY) {
        if (unit[posX][posY] == null){
            Plant plant = searchPlant(plantName,posX,posY);
            if (plant.getCost() <= this.suns) {
                unit[posX][posY] = plant;
                suns -= plant.getCost();
            }else {
                System.out.println("no hay suficientes soles");
                plant.die();
            }
        }
    }
    public void addZombie(String zombieName, int posY) {
        Zombie zombie = searchZombie(zombieName,posY);
        if (zombie.getCost() <= this.brains) {
            unit[10][posY] = zombie;
            brains -= zombie.getCost();
        }else{
            System.out.println("no hay suficientes cerebros");
            zombie.die();
        }
    }
    public void deletePlant(int posX,int posY) {
        if (unit[posX][posY] != null){
            unit[posX][posY] = null;
        }
    }
    public void deleteZombie(int posX,int posY) {
        if (unit[posX][posY] != null){
            unit[posX][posY] = null;
        }
    }

    public void addSuns(Sun sun) {
        this.suns += sun.getValue();
    }
    public void addBrains(Brain brain) {
        this.brains += brain.getValue();
    }

    public Bullet[][] getBullets() {
        return bullets;
    }

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
    public Plant searchPlant(String plant,int posX,int posY){
        switch (plant){
            case "peashooter":
                return new Peashooter(posX,posY,this);
            case "sunflower":
                return new Sunflower(posX,posY,this);
            case "wallnut":
                return new WallNut(posX,posY,this);
            case "potatomine":
                return new PotatoMine(posX,posY,this);
            case "eciplant":
                return new ECIPlant(posX,posY,this);
            default:
                System.out.println("nonas");
                break;
        }
        return null;
    }
    public Zombie searchZombie(String zombie,int posY){
        switch (zombie){
            case "basic":
                return new Basic(posY,this);
            case "brainstein":
                return new Brainstein(posY,this);
            case "conehead":
                return new ConeHead(posY,this);
            case "buckethead":
                return new BucketHead(posY,this);
            case "ecizombie":
                return new ECIZombie(posY,this);
            default:
                System.out.println("nonas");
                break;
        }
        return null;
    }

    public Unit[][] getUnit() {
        return unit;
    }
    public int getSuns(){return this.suns;}

    public int getBrains() {
        return brains;
    }
}
