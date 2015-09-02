package y2.game;

import java.util.ArrayList;

public class Board {

	/*
	 * ArrayList, mis hoiab endas mänguväljasid
	 */
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	/*
	 * Append(), mille abil lisatakse mänguväli
	 * ArrayList'i tiles kui seda mänguvälja seal juba ei leidu.
	 * Kui ArrayList tiles ei ole tühi, siis tiles'i viimane element 
	 * pannakse lisatava mänguvälja eelmiseks elemendiks ning uus 
	 * mänguväli pannakse viimase järgmiseks elemendiks.
	 * Lõpuks lisatakse mänguväli ArrayList'i.
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
	 * GetStart() tagastab kõige esimese mänguvälja.
	 */
	public Tile getStart() {
        return tiles.get(0);
    }
	
	
	
}
