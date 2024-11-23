package domain;

public class LawnMower {
    private Game game;
    private int positionY;
    public LawnMower(Game game, int positionY) {
        this.game = game;
        this.positionY = positionY;
    }
    public void kill() {
        for (int i = 0;i < 5;i++){
            this.game.deleteZombie(i,this.positionY);
        }
    }
    public int getPositionY() {
        return positionY;
    }
}
