package domain;

public class Board {
    private Unit[][] unit;
    public Board() {
        unit = new Unit[8][5];
    }
    public Unit[][] getUnit() {
        return unit;
    }
}
