package domain.economy;

import domain.PvZExceptions;

/**
 * Sun class that represents the currency of the game.
 * It implements the Coin interface.
 * It has a value attribute that represents the amount of suns.
 */
public class Sun implements Coin{
    private final int value;

    /**
     * Constructor of the Sun class.
     * @param value The amount of suns.
     * @throws PvZExceptions If the value is negative.
     */
    public Sun(int value) throws PvZExceptions {
        if (value < 0) {
            throw new PvZExceptions(PvZExceptions.NEGATIVE_COIN_EXCEPTION);
        }
        this.value = value;
    }

    /**
     * Getter for the value of the suns.
     * @return The value of the suns.
     */
    public int getValue() {
        return value;
    }
}