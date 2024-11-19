package domain;

public class Sunflower extends Plant{
    private static final String name = "Sunflower";
    public Sunflower(int x, int y, Board board) {
        super(name);
        this.life = 300;
        this.cost = 50;
        this.board = board;
        this.positionY = y;
        this.positionX = x;

    }
}
