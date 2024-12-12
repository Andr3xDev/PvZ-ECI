package domain;

import java.io.Serializable;

/**
 * This class is used to delete a plant from the game by the player.
 */
public class Shovel implements Serializable {

    // Attributes
    private final Game game;


    // Constructor

    /**
     * This constructor creates a new shovel object.
     * @param game The game where the shovel will be used.
     */
    public Shovel(Game game) {
        this.game = game;
    }

    /**
     * This method deletes a plant from the game.
     * @param positionX The x position of the plant to be deleted.
     * @param positionY The y position of the plant to be deleted.
     * @throws PvZExceptions If there is no plant in the given position.
     */
    public void deletePlant(int positionX, int positionY) throws PvZExceptions {
        if (this.game.getPlant(positionX, positionY) == null) {
            throw new PvZExceptions(PvZExceptions.NO_UNIT_EXCEPTION);
        }
        this.game.deleteUnit(positionX, positionY);
    }
}