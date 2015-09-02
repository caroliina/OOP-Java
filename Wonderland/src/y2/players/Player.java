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
	 * GetName() tagastab m�ngija nime.
	 */
	public String getName() {
		return name;
	}

	/*
	 * HashMap cardsHash hoiab endas m�ngija k�es olevaid kaarte (koos nimetustega).
	 */
	private HashMap<Card, String> cardsHash = new HashMap<Card, String>();
	
	/*
	 * ArrayList cardsList hoiab endas m�ngija k�es olevaid kaarte (ilma nimetustega).
	 */
	private ArrayList<Card> cardsList = new ArrayList<Card>();
	
	private int vitality = 3;
	private int maxVitality = vitality;
	
	/*
	 * SetVitaliy() abil saab muuta m�ngija jaksu.
	 */
	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	/*
	 * SetMaxVitaliy() abil saab muuta m�ngija maksimaalset jaksu.
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
	 * Move() liigub ette antud m�nguv�ljale.
	 */
	public void move(Tile tile) throws GameException{
		moveBehaviour.move(tile, this);
	}
	
	/*
	 * Forward() liigub �he m�nguv�lja v�rra edasi.
	 */
	public void forward() throws GameException{
		moveBehaviour.forward(this);
	}

	/*
	 * Backward() liigub �he m�nguv�lja v�rra tagasi.
	 */
	public void backward() throws GameException{
		moveBehaviour.backward(this);
	}
	
	/*
	 * PickUp() abil v�tab m�ngija ette antud kaardi enda k�tte. Lisatakse cards massiivi.
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
	 * GetCards() tagastab m�ngija k�es olevad kaardid.
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
	 * Tagastab punktide arvu vastavalt kui palju PrizeCard'e on tal k�es.
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
	 * Drop() viskab m�ngija k�est vastava kaardi m�nguv�ljale.
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
	 * GetVitality() tagastab m�ngija hetke jaksu.
	 */
	public int getVitality() {
		return vitality;
	}

	/*
	 * Sleep() paneb m�ngija magama ning samal ajal m�ngija taastab oma j�uvarud.
	 */
	public void sleep() {
		vitality = maxVitality;
	}

	/*
	 * GetMaxVitality() tagastab m�ngija maksimaalse jaksu.
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

