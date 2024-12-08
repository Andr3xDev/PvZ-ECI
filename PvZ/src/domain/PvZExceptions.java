package domain;

public class PvZExceptions extends Exception {
    public static final String SAVE_EXCEPTION = "Cannot save the PvZ map";
    public static final String OPEN_EXCEPTION = "Cannot open the PvZ map";
    public PvZExceptions(String message){
        super(message);
    }
}
