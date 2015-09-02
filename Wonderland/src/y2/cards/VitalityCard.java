package y2.cards;

import y2.players.Player;

public class VitalityCard extends ModifierCard{
	
	@Override
	public String getName(){
		return "vitality";
	}
	
	@Override
	public void onPickedUp(Player owner){
		owner.setMaxVitality(owner.getMaxVitality()+1);
		owner.setVitality(owner.getVitality()+1);
	}
	
	@Override
	public void onDropped(Player owner){
		owner.setMaxVitality(owner.getMaxVitality()-1);
		owner.setVitality(owner.getVitality()-1);
	}

	@Override
	public String toString(){
		return getName();
	}
	
	

}
