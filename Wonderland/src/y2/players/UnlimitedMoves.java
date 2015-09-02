package y2.players;

import y2.exceptions.DispelCardNotFoundException;
import y2.exceptions.GameException;
import y2.exceptions.NoPossibleMovesLeftException;
import y2.game.Tile;

public class UnlimitedMoves extends MoveBehaviour{

	@Override
	public void move(Tile tile, Player player) throws GameException{
		if(tile!=null){
			if(tile.getTileName() != ("Exit Wonderland")){
				player.getLocation().removePlayer(player);
				player.setLocation(tile);
				player.getLocation().addPlayer(player);
				
			} else if(tile.getTileName() == ("Exit Wonderland")){
				if(tile.canEnter(player)){
					player.getLocation().removePlayer(player);
					player.setLocation(tile);
					System.out.println("Moved to: " + player.getLocation().toString());
					System.out.println(player.getName() + " left Wonderland.");
					System.out.println(player.getName() + "'s score is " + player.getPoints());
				} else {
					throw new DispelCardNotFoundException();
				}
			}
		} else {
			throw new NoPossibleMovesLeftException();
		}
		/*
		 * Siin vitality arvestust ei pea.
		 */
		System.out.println("Moved to: " + player.getLocation().toString());
	}


}
