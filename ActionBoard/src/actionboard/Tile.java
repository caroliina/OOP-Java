package actionboard;

import java.util.ArrayList;

public class Tile {

	String name;
	Tile north;
	Tile west;
	Tile south;
	Tile east;
	int count;
	Tile next;
	int monsters;
	int people;

	public boolean countPlayers() {
		monsters = 0;
		people = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getN() == "monster") {
				monsters++;
			} else {
				people++;
			}
		}
		if (monsters > people) {
			return true;
		} else
			return false;
	}
	
	String found = "no";
	public String contains(String string){
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getN()==string){
				found="yes";
			}
		}
		return found;
	}

	private ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Tile> neighbours = new ArrayList<Tile>();

	public ArrayList<Tile> getNeighbours() {
		return neighbours;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	Semaphore semaphore;

	public Tile(String name, int count) {
		this.name = name;
		this.count = count;
		semaphore = new Semaphore(count);
	}

	public void enter(Player player) throws InterruptedException {
		semaphore.acquire(player);
		players.add(player);
	}

	public void leave(Player player) {
		semaphore.release(player);
		players.remove(player);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tile getNorth() {
		return north;
	}

	public void setNorth(Tile north) {
		this.north = north;
		neighbours.add(north);
	}

	public Tile getWest() {
		return west;
	}

	public void setWest(Tile west) {
		this.west = west;
		neighbours.add(west);
	}

	public Tile getSouth() {
		return south;
	}

	public void setSouth(Tile south) {
		this.south = south;
		neighbours.add(south);
	}

	public Tile getEast() {
		return east;
	}

	public void setEast(Tile east) {
		this.east = east;
		neighbours.add(east);
	}

	public Tile getNext() {
		return next;
	}

	public void setNext(Tile next) {
		this.next = next;
	}

}
