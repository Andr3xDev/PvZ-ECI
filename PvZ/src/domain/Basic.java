package domain;

public class Basic extends Zombie{
    private static final String name = "Basic";
    public Basic(int x, int y, Board board) {
        super(name);
        this.life = 100;
        this.damage = 100;
        this.cost = 50;
        this.board = board;
        this.positionY = y;
        this.positionX = x;
    }

}
