package domain.plants;

import domain.Game;

public class Peashooter extends Plant {
    private static final String name = "Peashooter";
    public Peashooter(int x, int y, Game game) {
        super(name);
        this.life = 300;
        this.damage = 20;
        this.cost = 100;
        this.game = game;
        this.positionY = y;
        this.positionX = x;
    }
    private void shoot(){
        for (int i = positionX;i < 8;i++){
            if (game.getUnit()[i][this.positionY] != null){

            }
        }
    }
}
