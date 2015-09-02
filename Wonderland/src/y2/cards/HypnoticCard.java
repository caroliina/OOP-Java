package y2.cards;

import y2.players.Player;

public class HypnoticCard extends ActionCard{

	@Override
	public String getName(){
		return "hypnotic";
	}
	
	@Override
	public void onPickedUp(Player owner){
		owner.setVitality(0);
	}
	
	@Override
	public void onDropped(Player owner){
	}

	@Override
	public String toString(){
		return getName();
	}
	
}
