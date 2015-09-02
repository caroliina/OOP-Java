package y2.cards;

import y2.exceptions.GameException;
import y2.players.Player;

public class ActionCard extends Card{

	@Override
	public String getType() {
		return "action";
	}

	@Override
	public void onPickedUp(Player player) throws GameException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDropped(Player player) throws GameException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
