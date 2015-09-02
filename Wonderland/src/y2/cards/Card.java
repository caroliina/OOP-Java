package y2.cards;

import y2.exceptions.GameException;
import y2.players.Player;

public abstract class Card {

	/*
	 * GetName() tagastab kaardi nime.
	 */
	public abstract String getName();
	
	/*
	 * OnPickedUp() realiseerib vastavad muutused, mis kindla kaardi Ÿleskorjamisega kaasnevad.
	 */
	public abstract void onPickedUp(Player player) throws GameException;
	
	/*
	 * OnDropped() realiseerib vastavad muutused, mis kindla kaardi mahaviskamisega kaasnevad.
	 */
	public abstract void onDropped(Player player) throws GameException;

	/*
	 * Tagastab tŸŸbi: kas Modifier v›i Action
	 */
	public abstract String getType();

	
}
