package domain;

public class BucketHead extends Zombie{
    private static final String name = "BucketHead";
    public BucketHead(int x, int y, Game game) {
        super(name);
        this.life = 1100;
        this.damage = 100;
        this.cost = 200;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }
}
