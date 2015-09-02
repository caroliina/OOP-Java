package y2.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import y2.cards.Card;
import y2.exceptions.GameException;
import y2.players.Player;

public class Tile {

    private Tile nextTile;
    private Tile previousTile;
    private String tileName;
    private Board board;
    
    public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getTileName() {
		return tileName;
	}

	/*
     * HashMap, mis hoiab endas sellel m�nguv�ljal olevaid m�ngijaid.
     */
    private HashMap<String, Player> players = new HashMap<String, Player>();
    
    /*
     * ArrayList, mis hoiab endas sellel m�nguv�ljal olevaid kaarte.
     */
	private ArrayList<Card> cards = new ArrayList<Card>();	

	/*
	 * Annab m�nguv�ljale nimetuse, konstruktor.
	 */
	public Tile(String tileName) {
        this.tileName = tileName;
    }
    
    @Override
	public String toString() {
		return tileName;
	}

    /*
     * GetNextTile() tagastab j�rgmise m�nguv�lja.
     */
	public Tile getNextTile() {
		return nextTile;
	}

	/*
	 * SetNextTile() paneb muutujasse nextTile praegusele m�nguv�ljale j�rgneva m�nguv�lja.
	 */
	public void setNextTile(Tile nextTile) {
		this.nextTile = nextTile;
	}
	
	/*
     * GetPreviousTile() tagastab eelmise m�nguv�lja.
     */
	public Tile getPreviousTile() {
		return previousTile;
	}

	/*
	 * SetPreviousTile() paneb muutujasse previousTile praegusele m�nguv�ljale eelneva m�nguv�lja.
	 */
	public void setPreviousTile(Tile previousTile) {
		this.previousTile = previousTile;
	}
	
	/*
	 * AddCard() lisab selle m�nguv�lja ArrayList'i cards uue kaardi.
	 */
	public void addCard(Card card){
		cards.add(card);
	}
	
	/*
	 * GetCards() tagastab sellel m�nguv�ljal olevad kaardid.
	 */
	public List<Card> getCards() {
        return cards;
    }
	
	/*
	 * GetFirstCard() v�tab �ra m�nguv�lja ArrayList'ist cards k�ige esimese kaardi.
	 */
	public Card getFirstCard(){
		Card card = cards.get(0);
		cards.remove(0);
		System.out.println("Picked up card");
		return card;
	}
	
	/*
	 * AddPlayer() lisab players HashMap'i uue m�ngija. M�ngija asub sellel m�nguv�ljal.
	 */
	public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }
	
	/*
	 * RemovePlayer() eemdaldab players HashMap'ist m�ngija. M�ngija lahkub sellelt m�nguv�ljalt.
	 */
	public void removePlayer(Player player) {
        players.remove(player);
    }
	
	/*
	 * CanEnter() kontrollib, et kas m�ngijal on k�es DispelCard v�i mitte.
	 */
	public boolean canEnter(Player player){
		try {
			if(player.getCardByName("dispel") != null){
				return true;
			} else return false;
		} catch (GameException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}
