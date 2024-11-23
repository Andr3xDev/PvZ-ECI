package domain;

public class ECIZombie extends Zombie {
    private static final String name = "ECIZombie";
    public ECIZombie(int y, Game game) {
        super(name);
        this.life = 200;
        this.damage = 50;
        this.cost = 250;
        this.game = game;
        this.positionY = y;
        this.positionX = 7;
    }
}
