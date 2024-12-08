package domain;

public class PvZExceptions extends Exception {
    public static final String ZOMBIE_OUT_RANGE_EXCEPTION = "Given position is out of range for the zombie";
    public static final String PLANT_OUT_RANGE_EXCEPTION = "Given position is out of range for the plant";
    public static final String NO_BRAINS_EXCEPTION = "Not enough brains to buy the plant";
    public static final String NO_SUNS_EXCEPTION = "Not enough suns to buy the plant";
    public static final String EXISTENT_UNIT_EXCEPTION = "Cannot add the unit because there is already a unit in the cell";
    public static final String SAVE_EXCEPTION = "Cannot save the PvZ map";
    public static final String OPEN_EXCEPTION = "Cannot open the PvZ map";
    public PvZExceptions(String message){
        super(message);
    }
}