package y2.cards;

import java.util.List;

import y2.exceptions.GameException;
import y2.players.Player;

public class DistractCard extends ActionCard{

	@Override
	public String getName(){
		return "distract";
	}

	@Override
	public void onPickedUp(Player owner){
		List<Card> cards = owner.getCards();
		if(cards.get(0) != null){
			try {
				owner.drop(cards.get(0));
			} catch (GameException e){
				
			}
		}
	}

	@Override
	public void onDropped(Player owner){
	}

}
