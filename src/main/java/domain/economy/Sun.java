package domain.economy;

public class Sun implements Coin{
    private int value;

    public Sun(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}