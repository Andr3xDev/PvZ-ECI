package domain;

public abstract class Zombie implements Unit {
    private String name;
    protected int life;
    protected int damage;
    protected int cost;
    protected int positionX;
    protected int positionY;
    protected Game game;

    public Zombie(String name){
        this.name = name;
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

    @Override
    public void takeDamage(int dmg) {
        this.life -= dmg;
        if (this.life < 0) {
            this.life = 0;
        }
    }
    public void makeDamage(){
        if (this.positionX-1 >= 0 && this.game.getUnit()[positionX-1][positionY] != null){
            while (this.game.getUnit()[positionX-1][positionY].getLife() > 0) {
                this.game.getUnit()[positionX-1][positionY].takeDamage(this.damage);
            }
        }
    }
    public String getName() {
        return name;
    }
}

