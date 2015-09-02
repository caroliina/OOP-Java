package y2.exceptions;

public class NoPossibleMovesLeftException extends GameException {
	
    public NoPossibleMovesLeftException() {
        super("No tiles there.");
    }

}
