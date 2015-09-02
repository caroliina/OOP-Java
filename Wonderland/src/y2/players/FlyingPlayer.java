package y2.players;

import y2.cards.Card;
import y2.exceptions.CardsLimitException;
import y2.exceptions.GameException;
import y2.game.Tile;

public class FlyingPlayer extends Player{
	
	/*
	 * Player'i alamklass, mille puhul on võimalik teha lõpmatult käike.
	 */
	public FlyingPlayer(String name, Tile location) {
		super(name, location);
		/*
		 * Võimalus teha lõpmatult käike.
		 */
		super.setMoveBehaviour(new UnlimitedMoves());
	}
	
	@Override
	public void pickUp(Card card) throws GameException{
		/*
		 * Selle mängija korral võib korraga käes olla ainult 2 kaarti.
		 */
		if (super.getCardsHash().size() < 2 && super.getCards().size() < 2) {
			if(card.getType() != "action"){
	            super.getCardsHash().put(card, card.getName());
	            super.getCards().add(card);
            }
            card.onPickedUp(this);
        } else {
        	throw new CardsLimitException();
        }
	}

}
