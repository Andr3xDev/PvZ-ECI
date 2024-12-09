package domain.economy;

import domain.PvZExceptions;

/**
 * Brain is a class that represents a brain coin.
 * It implements the Coin interface.
 * It has a value attribute that represents the amount of brain coins.
 */
public class Brain implements Coin {
    private final int value;

    /**
     * Constructor for the Brain class.
     * @param value The value of the brain coin.
     * @throws PvZExceptions If the value is negative.
     */
    public Brain(int value) throws PvZExceptions {
        if (value < 0) {
            throw new PvZExceptions(PvZExceptions.NEGATIVE_COIN_EXCEPTION);
        }
        this.value = value;
    }

    /**
     * Getter for the value of the brain coin.
     * @return The value of the brain coin.
     */
    public int getValue() {
        return value;
    }
}