package domain;

/**
 * Interface for all units in the game.
 */
public interface Unit {

    int getLife();
    int getDamage();
    int getCost();
    void takeDamage(int dmg);
    void die();
}