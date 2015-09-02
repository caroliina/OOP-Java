package y2.players;

import y2.exceptions.GameException;
import y2.game.Tile;

public abstract class MoveBehaviour {

	/*
	 * Liigub ette antud mänguväljale.
	 */
	public abstract void move(Tile tile, Player player) throws GameException;
	
	public void forward(Player player) throws GameException {
		move(player.getLocation().getNextTile(), player);	
	}
	
	public void backward(Player player) throws GameException {
		move(player.getLocation().getPreviousTile(), player);
	}
}
