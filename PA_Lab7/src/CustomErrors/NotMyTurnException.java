package CustomErrors;

public class NotMyTurnException extends Exception{
    public NotMyTurnException(){
        super("It's not my turn! I will let another thread take my place!...");
    }
}
