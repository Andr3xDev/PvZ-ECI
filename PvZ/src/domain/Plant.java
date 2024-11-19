package domain;

public abstract class Plant implements Unit {
    private String name;
    protected int life;
    protected int damage;
    protected int cost;
    protected int positionX;
    protected int positionY;
    protected Board board;


    public Plant(String name ) {
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
    public String getName() {
        return name;
    }
}
