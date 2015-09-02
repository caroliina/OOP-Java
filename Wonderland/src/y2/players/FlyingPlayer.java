package y2.players;

import y2.cards.Card;
import y2.exceptions.CardsLimitException;
import y2.exceptions.GameException;
import y2.game.Tile;

public class FlyingPlayer extends Player{
	
	/*
	 * Player'i alamklass, mille puhul on v�imalik teha l�pmatult k�ike.
	 */
	public FlyingPlayer(String name, Tile location) {
		super(name, location);
		/*
		 * V�imalus teha l�pmatult k�ike.
		 */
		super.setMoveBehaviour(new UnlimitedMoves());
	}
	
	@Override
	public void pickUp(Card card) throws GameException{
		/*
		 * Selle m�ngija korral v�ib korraga k�es olla ainult 2 kaarti.
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
