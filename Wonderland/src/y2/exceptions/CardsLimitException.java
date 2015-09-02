package y2.exceptions;

public class CardsLimitException extends GameException {
	
    public CardsLimitException() {
        super("Cards limit reached.");
    }


}

