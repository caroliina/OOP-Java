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
		 * Kui kätte võetakse, siis saab teha lõpmatult käike.
		 */
		owner.setMoveBehaviour(new UnlimitedMoves());
	}
	
	@Override
	public void onDropped(Player owner){
		/*
		 * Kui maha pannakse, siis kaob kaardi mõju. UnlimitedMoves() asendub NormalMoves()'iga.
		 */
		owner.setMoveBehaviour(new NormalMoves());
	}

	@Override
	public String toString(){
		return getName();
	}

}
