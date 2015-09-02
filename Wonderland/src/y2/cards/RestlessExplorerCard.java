package y2.cards;

import y2.players.NormalMoves;
import y2.players.Player;
import y2.players.UnlimitedMoves;

public class RestlessExplorerCard extends ModifierCard{
	
	@Override
	public String getName(){
		return "restless";
	}
	
	@Override
	public void onPickedUp(Player owner){
		/*
		 * Kui k�tte v�etakse, siis saab teha l�pmatult k�ike.
		 */
		owner.setMoveBehaviour(new UnlimitedMoves());
	}
	
	@Override
	public void onDropped(Player owner){
		/*
		 * Kui maha pannakse, siis kaob kaardi m�ju. UnlimitedMoves() asendub NormalMoves()'iga.
		 */
		owner.setMoveBehaviour(new NormalMoves());
	}

	@Override
	public String toString(){
		return getName();
	}

}
