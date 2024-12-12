package domain;

import java.io.Serializable;

/**
 * Represents a bullet in the game. It's an interface bullet class.
 * It has a position X and a position Y.
 */
public interface Bullet extends Serializable {

    // Methods

    /**
     * Makes the bullet move.
     */
    void move();
}