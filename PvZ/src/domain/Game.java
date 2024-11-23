package domain;

public class Game {
    private Unit[][] unit;
    private int brains = 50;
    private int suns = 50;
    public Game() {
        unit = new Unit[8][5];
    }

    public void addPlant(Plant plant,int posX,int posY) {
        if (unit[posX][posY] == null){
            unit[posX][posY] = plant;
            suns -= plant.getCost();
        }
    }
    public void addZombie(Zombie zombie,int posX,int posY) {
        if (unit[posX][posY] == null){
            unit[posX][posY] = zombie;
            brains -= zombie.getCost();
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

    public Unit[][] getUnit() {
        return unit;
    }
}
