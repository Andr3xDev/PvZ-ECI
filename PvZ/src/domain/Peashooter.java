package domain;

public class Peashooter extends Plant{
    private static final String name = "Peashooter";
    public Peashooter(int x, int y, Board board) {
        super(name);
        this.life = 300;
        this.damage = 20;
        this.cost = 100;
        this.board = board;
        this.positionY = y;
        this.positionX = x;
    }
    private void shoot(){
        for (int i = positionX;i < 8;i++){
            if (board.getUnit()[i][this.positionY] != null){

            }
        }
    }
}
