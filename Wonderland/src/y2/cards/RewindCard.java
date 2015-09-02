package y2.cards;

import y2.exceptions.GameException;
import y2.players.Player;

public class RewindCard extends ActionCard{
	
	@Override
	public String getName(){
		return "rewind";
	}

	@Override
	public void onPickedUp(Player owner) throws GameException{
		owner.move(owner.getLocation().getBoard().getStart());
	}
}
