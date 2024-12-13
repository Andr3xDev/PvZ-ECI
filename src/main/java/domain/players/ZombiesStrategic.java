package domain.players;

import domain.Game;

public class ZombiesStrategic extends Machine {

    String[] zombieTypes = {"basic", "conehead", "buckethead", "brainstein", "ecizombie"};

    public ZombiesStrategic(Game game) {
        super(game);
    }

    @Override
    public void makeMove(Game game) {

    }
}
