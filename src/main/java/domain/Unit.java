package domain;

import java.io.Serializable;

/**
 * Interface for all units in the game.
 */
public interface Unit extends Serializable {

    int getLife();
    int getDamage();
    int getCost();
    void takeDamage(int dmg);
    void die();
}