package domain.players;

import domain.Game;

public class PlantsStrategic extends Machine {

    private String[] plantTypes = {"peashooter", "wallnut", "sunflower", "eciplant", "potatomine"};


    public PlantsStrategic(Game game) {
        super(game);
    }

    @Override
    public void makeMove(Game game) {

    }
}