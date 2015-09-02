package y2.exceptions;

public class CantDropStickyCardException extends GameException {
	
    public CantDropStickyCardException() {
        super("Can't drop a StickyCard.");
    }

}

