package domain.economy;

import domain.PvZExceptions;

public class Sun implements Coin{
    private int value;

    public Sun(int value) throws PvZExceptions {
        if (value < 0) {
            throw new PvZExceptions(PvZExceptions.NEGATIVE_COIN_EXCEPTION);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}