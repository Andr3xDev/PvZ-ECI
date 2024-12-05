package domain.economy;

public class Brain implements Coin {
    private int value;
    public Brain(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
