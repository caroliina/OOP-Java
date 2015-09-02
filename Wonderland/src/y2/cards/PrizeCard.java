package y2.cards;

import y2.players.Player;

public class PrizeCard extends ModifierCard{
	
	@Override
	public String getName(){
		return "prize";
	}
	
	@Override
	public void onPickedUp(Player owner){
		
	}
	
	@Override
	public void onDropped(Player owner){
		
	}

	@Override
	public String toString() {
		return getName();
	}
	
	
	

}
