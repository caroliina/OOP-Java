package y2.cards;

import y2.players.Player;

public class ZippyCard extends ModifierCard{

	@Override
	public String getName(){
		return "zippy";
	}
	
	@Override
	public void onPickedUp(Player owner){
		owner.setVitality(owner.getVitality()+1);
	}
	
	@Override
	public void onDropped(Player owner){
		owner.setVitality(owner.getVitality()-1);
	}

	@Override
	public String toString(){
		return getName();
	}
}
