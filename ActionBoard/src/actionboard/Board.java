package actionboard;

import java.util.ArrayList;

public class Board {

	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	Tile start = new Tile("start", 4);
	Tile B2 = new Tile("B2", 2);
	Tile A2 = new Tile("A2", 4);
	Tile A1 = new Tile("A1", 2);
	Tile B1 = new Tile("B1", 4);

	public Board() {
		tiles.add(start);
		start.setWest(B2);

		tiles.add(B2);
		B2.setNorth(B1);
		B2.setWest(A2);

		tiles.add(A2);
		A2.setNorth(A1);
		A2.setEast(B2);

		tiles.add(A1);
		A1.setSouth(A2);
		A2.setEast(B1);

		tiles.add(B1);
		B1.setSouth(B2);
		B2.setWest(A1);
	}

	public Tile getStart() {
		return start;
	}

}
