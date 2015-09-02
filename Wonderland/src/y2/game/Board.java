package y2.game;

import java.util.ArrayList;

public class Board {

	/*
	 * ArrayList, mis hoiab endas m�nguv�ljasid
	 */
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	/*
	 * Append(), mille abil lisatakse m�nguv�li
	 * ArrayList'i tiles kui seda m�nguv�lja seal juba ei leidu.
	 * Kui ArrayList tiles ei ole t�hi, siis tiles'i viimane element 
	 * pannakse lisatava m�nguv�lja eelmiseks elemendiks ning uus 
	 * m�nguv�li pannakse viimase j�rgmiseks elemendiks.
	 * L�puks lisatakse m�nguv�li ArrayList'i.
	 */
	public void append(Tile tile) {
    	if(!tiles.contains(tile)) {
    		if(tiles.size() != 0){
	    		Tile lastTile = tiles.get(tiles.size()-1);
	    		tile.setPreviousTile(lastTile);
	    	    lastTile.setNextTile(tile);
    		}
    		tiles.add(tile);
    	}
    	
    	tile.setBoard(this);
    }

	/*
	 * GetStart() tagastab k�ige esimese m�nguv�lja.
	 */
	public Tile getStart() {
        return tiles.get(0);
    }
	
	
	
}
