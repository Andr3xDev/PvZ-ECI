package domain;

/**
 * Class that represents the LawnMower object.
 * It is used to kill all the zombies in a row when they touch this.
 */
public class LawnMower {

    // Attributes
    private Game game;
    private int positionY;


    // Constructor

    /**
     * Constructor method of the LawnMower class.
     * @param game Game object where the LawnMower is.
     * @param positionY Integer with the position in the Y axis of the LawnMower.
     */
    public LawnMower(Game game, int positionY) {
        this.game = game;
        this.positionY = positionY;
    }


    // Methods

    /**
     * Method that kills all the zombies in the row where the LawnMower is.
     * @throws PvZExceptions Exception thrown when the unit is not found.
     */
    public void kill() throws PvZExceptions {
        for (int i = 0;i < 11;i++){
            if (this.game.getZombie(i,this.positionY) != null){
                this.game.deleteUnit(i,this.positionY);
            }
        }
    }

    /**
     * Method that returns the position in the Y axis of the LawnMower.
     * @return Integer with the position in the Y axis of the LawnMower.
     */
    public int getPositionY() {
        return positionY;
    }
}