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
     * HashMap, mis hoiab endas sellel mänguväljal olevaid mängijaid.
     */
    private HashMap<String, Player> players = new HashMap<String, Player>();
    
    /*
     * ArrayList, mis hoiab endas sellel mänguväljal olevaid kaarte.
     */
	private ArrayList<Card> cards = new ArrayList<Card>();	

	/*
	 * Annab mänguväljale nimetuse, konstruktor.
	 */
	public Tile(String tileName) {
        this.tileName = tileName;
    }
    
    @Override
	public String toString() {
		return tileName;
	}

    /*
     * GetNextTile() tagastab järgmise mänguvälja.
     */
	public Tile getNextTile() {
		return nextTile;
	}

	/*
	 * SetNextTile() paneb muutujasse nextTile praegusele mänguväljale järgneva mänguvälja.
	 */
	public void setNextTile(Tile nextTile) {
		this.nextTile = nextTile;
	}
	
	/*
     * GetPreviousTile() tagastab eelmise mänguvälja.
     */
	public Tile getPreviousTile() {
		return previousTile;
	}

	/*
	 * SetPreviousTile() paneb muutujasse previousTile praegusele mänguväljale eelneva mänguvälja.
	 */
	public void setPreviousTile(Tile previousTile) {
		this.previousTile = previousTile;
	}
	
	/*
	 * AddCard() lisab selle mänguvälja ArrayList'i cards uue kaardi.
	 */
	public void addCard(Card card){
		cards.add(card);
	}
	
	/*
	 * GetCards() tagastab sellel mänguväljal olevad kaardid.
	 */
	public List<Card> getCards() {
        return cards;
    }
	
	/*
	 * GetFirstCard() võtab ära mänguvälja ArrayList'ist cards kõige esimese kaardi.
	 */
	public Card getFirstCard(){
		Card card = cards.get(0);
		cards.remove(0);
		System.out.println("Picked up card");
		return card;
	}
	
	/*
	 * AddPlayer() lisab players HashMap'i uue mängija. Mängija asub sellel mänguväljal.
	 */
	public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }
	
	/*
	 * RemovePlayer() eemdaldab players HashMap'ist mängija. Mängija lahkub sellelt mänguväljalt.
	 */
	public void removePlayer(Player player) {
        players.remove(player);
    }
	
	/*
	 * CanEnter() kontrollib, et kas mängijal on käes DispelCard või mitte.
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
