package domain;

public class Shovel {
    private Game game;
    public Shovel(Game game) {
        this.game = game;
    }

    public void deletePlant(int positionX, int positionY) throws PvZExceptions {
        if (this.game.getPlant(positionX, positionY) == null) {
            throw new PvZExceptions(PvZExceptions.NO_UNIT_EXCEPTION);
        }
        this.game.deleteUnit(positionX, positionY);
    }
}
