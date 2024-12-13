package domain.players;

import domain.Game;

public class PlantsIntelligent extends Machine {

    private String[] plantTypes = {"peashooter", "wallnut", "sunflower", "eciplant", "potatomine"};

    public PlantsIntelligent(Game game) {
        super(game);
    }

    @Override
    public void makeMove(Game game) {

    }

}