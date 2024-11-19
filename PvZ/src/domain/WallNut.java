package domain;

public class WallNut extends Plant{
    private static final String name = "WallNut";
    public WallNut(int x, int y, Board board) {
        super(name);
        this.life = 4000;
        this.cost = 50;
        this.board = board;
        this.positionY = y;
        this.positionX = x;
    }
}
