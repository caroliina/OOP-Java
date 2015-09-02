package y2.cards;

import y2.exceptions.CantDropStickyCardException;
import y2.players.Player;

public class StickyCard extends ModifierCard{

	@Override
	public String getName(){
		return "sticky";
	}

	@Override
	public void onPickedUp(Player owner){
	}

	@Override
    public void onDropped(Player owner) throws CantDropStickyCardException{
        throw new CantDropStickyCardException();
    }

}
