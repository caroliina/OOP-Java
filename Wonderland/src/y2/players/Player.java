package y2.players;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import y2.cards.Card;
import y2.exceptions.CardNameException;
import y2.exceptions.CardsLimitException;
import y2.exceptions.DispelCardNotFoundException;
import y2.exceptions.GameException;
import y2.exceptions.NoPossibleMovesLeftException;
import y2.exceptions.TooTiredException;
import y2.game.Tile;


public class Player {

	private Tile location;
	private String name;
	
	/*
	 * Uus MoveBehaviour klass
	 */
	private MoveBehaviour moveBehaviour = new NormalMoves();
	
	public MoveBehaviour getMoveBehaviour() {
		return moveBehaviour;
	}

	public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
	}

	public Tile getLocation() {
		return location;
	}

	public void setLocation(Tile location) {
		this.location = location;
	}

	

	/*
	 * GetName() tagastab mängija nime.
	 */
	public String getName() {
		return name;
	}

	/*
	 * HashMap cardsHash hoiab endas mängija käes olevaid kaarte (koos nimetustega).
	 */
	private HashMap<Card, String> cardsHash = new HashMap<Card, String>();
	
	/*
	 * ArrayList cardsList hoiab endas mängija käes olevaid kaarte (ilma nimetustega).
	 */
	private ArrayList<Card> cardsList = new ArrayList<Card>();
	
	private int vitality = 3;
	private int maxVitality = vitality;
	
	/*
	 * SetVitaliy() abil saab muuta mängija jaksu.
	 */
	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	/*
	 * SetMaxVitaliy() abil saab muuta mängija maksimaalset jaksu.
	 */
	public void setMaxVitality(int maxVitality) {
		this.maxVitality = maxVitality;
	}

	/*
	 * Player konstruktor.
	 */
	public Player(String name, Tile location) {
        this.name = name;
        this.location = location;
    }
	
	/*
	 * Move() liigub ette antud mänguväljale.
	 */
	public void move(Tile tile) throws GameException{
		moveBehaviour.move(tile, this);
	}
	
	/*
	 * Forward() liigub ühe mänguvälja võrra edasi.
	 */
	public void forward() throws GameException{
		moveBehaviour.forward(this);
	}

	/*
	 * Backward() liigub ühe mänguvälja võrra tagasi.
	 */
	public void backward() throws GameException{
		moveBehaviour.backward(this);
	}
	
	/*
	 * PickUp() abil võtab mängija ette antud kaardi enda kätte. Lisatakse cards massiivi.
	 */
	public void pickUp(Card card) throws GameException{
		if (cardsHash.size() < 5 && cardsList.size() < 5) {
			if(card.getType() != "action"){
	            cardsHash.put(card, card.getName());
	            cardsList.add(card);
            }
            card.onPickedUp(this);
        } else {
        	throw new CardsLimitException();
        }
	}

	/*
	 * GetCards() tagastab mängija käes olevad kaardid.
	 */
	public List<Card> getCards() {
		return cardsList;
	}

	/*
	 * GetCardByName() tagastab kaardi, mis vastab ette antud nimele.
	 */
	public Card getCardByName(String cardName) throws GameException{
		
		for (Map.Entry<Card, String> entry : cardsHash.entrySet()) {
		    Card key = entry.getKey();
		    String value = entry.getValue();
		    
		    if(value==cardName){
		    	return key;
		    } else {
		    	throw new CardNameException();
		    }
		}
		return null;
	}
	
	/*
	 * Tagastab punktide arvu vastavalt kui palju PrizeCard'e on tal käes.
	 */
	public int getPoints() {
		int score = 0;
		for (Map.Entry<Card, String> entry : cardsHash.entrySet()) {
		    Card key = entry.getKey();
		    String value = entry.getValue();
		    if(value=="prize"){
		    	score++;
		    }
		}
		return score;
	}

	/*
	 * Drop() viskab mängija käest vastava kaardi mänguväljale.
	 */
	public void drop(Card card) throws GameException{
		try {
			card.onDropped(this);
			cardsHash.remove(card);
			cardsList.remove(card);
			location.getCards().add(card);
			System.out.println(this.getName() + " dropped " + card.getName() + " card");
		} catch (GameException e) {
			throw new GameException(e.getMessage());
		}
	}

	/*
	 * GetVitality() tagastab mängija hetke jaksu.
	 */
	public int getVitality() {
		return vitality;
	}

	/*
	 * Sleep() paneb mängija magama ning samal ajal mängija taastab oma jõuvarud.
	 */
	public void sleep() {
		vitality = maxVitality;
	}

	/*
	 * GetMaxVitality() tagastab mängija maksimaalse jaksu.
	 */
	public int getMaxVitality() {
		return maxVitality;
	}

	public HashMap<Card, String> getCardsHash() {
		return cardsHash;
	}

	public void setCardsHash(HashMap<Card, String> cardsHash) {
		this.cardsHash = cardsHash;
	}
	
	
}

