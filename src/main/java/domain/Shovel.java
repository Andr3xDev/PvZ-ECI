package domain;

public class Shovel {
    private Game game;
    private int positionX;
    private int positionY;
    public Shovel(Game game, int positionX, int positionY) throws PvZExceptions {
        this.game = game;
        this.positionX = positionX;
        this.positionY = positionY;
        this.game.deleteUnit(positionX, positionY);
    }

}
