package domain.plants;

import domain.Game;
import domain.Unit;

public abstract class Plant implements Unit {
    private String name;
    protected int life;
    protected int damage;
    protected int cost;
    protected int positionX;
    protected int positionY;
    protected Game game;


    public Plant(String name ) {
        this.name = name;
    }

    @Override
    public void takeDamage(int dmg) {
        this.life -= dmg;
        if (this.life < 0) {
            this.life = 0;
        }
    }
    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getCost() {
        return cost;
    }



    public String getName() {
        return name;
    }
}
