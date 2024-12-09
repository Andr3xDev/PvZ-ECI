package domain;

public class LawnMower {
    private Game game;
    private int positionY;
    public LawnMower(Game game, int positionY) {
        this.game = game;
        this.positionY = positionY;
    }
    public void kill() throws PvZExceptions {
        for (int i = 0;i < 11;i++){
            if (this.game.getZombie(i,this.positionY) != null){
                this.game.deleteUnit(i,this.positionY);
            }
        }
    }
    public int getPositionY() {
        return positionY;
    }
}
