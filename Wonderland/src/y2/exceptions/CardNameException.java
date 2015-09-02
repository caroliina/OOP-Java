package y2.exceptions;

public class CardNameException extends GameException {
	
    public CardNameException() {
        super("No such card found.");
    }

}