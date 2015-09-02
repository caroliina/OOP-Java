package y2;

import java.util.List;

import y2.cards.Card;
import y2.cards.PrizeCard;
import y2.cards.RestlessExplorerCard;
import y2.cards.VitalityCard;
import y2.game.Board;
import y2.game.ExitWonderlandTile;
import y2.game.Tile;
import y2.players.FlyingPlayer;
import y2.players.Player;

public class Program {

	public static void main(String[] args) throws Exception{
		
		Board b = new Board(); 
		Tile t1 = new Tile("Narrow Path"); 
		Tile t2 = new Tile("Old Tree"); 
		Tile t3 = new Tile("Rabbit Hole"); 
		Tile t4 = new Tile("Bottom of Rabbit Hole"); 
		Tile t5 = new ExitWonderlandTile("Exit Wonderland");
		b.append(t1); b.append(t2); b.append(t3); b.append(t4); b.append(t5);

		Player alice = new Player("Alice", b.getStart());
		
		t1.addCard(new PrizeCard()); 
		t2.addCard(new PrizeCard()); 

		alice.pickUp(t1.getFirstCard()); 
		alice.move(t2); 
		alice.pickUp(t2.getFirstCard());
		System.out.println(alice.getLocation());
		//t3.addCard(new DistractCard());
		//alice.pickUp(t3.getFirstCard());
		
		List<Card> cards = alice.getCards(); // alicel peaks olema 2 PrizeCard'i 
		System.out.println(cards);
		
		Card c = alice.getCardByName("prize"); // tagastab PrizeCard'i 
		System.out.println(c);
		
		alice.drop(c);
		
		Player bob = new Player("Bob", b.getStart()); 
		System.out.println(bob.getVitality()); // tagastab 3 
		
		
		//t3.addCard(new DispelCard()); 
		
		bob.forward(); bob.backward(); 
		//bob.pickUp(t3.getFirstCard()); 
		//bob.move(t5); // väsitame mängija ära 
		
		bob.forward();
		//bob.backward(); // peaks heitma erindi 
		
		System.out.println(bob.getVitality());
		bob.sleep(); // getVitality() peaks nüüd tagastama 3 
		System.out.println(bob.getVitality());
		
		
		
		//t3.addCard(new StickyCard()); 
		t3.addCard(new VitalityCard()); 
		//t3.addCard(new DistractCard()); 
		
		
		bob.move(t3); 
		System.out.println(bob.getVitality()); // tagastab 2 
		System.out.println(bob.getMaxVitality()); // maksimaalne vitaalsus on 3 
		
		bob.pickUp(t3.getFirstCard()); // Bobi vitaalsus on nüüd 3 ja maksimum 4 
		//bob.pickUp(t3.getFirstCard());
		
		/*List<Card> cardsb = bob.getCards();
		System.out.println(cardsb);*/
		
		System.out.println(bob.getVitality()); // tagastab 3 
		System.out.println(bob.getMaxVitality()); // maksimaalne vitaalsus on 4 

		//bob.drop(bob.getCardByName("sticky"));
		bob.drop(bob.getCardByName("vitality")); // vitaalsus on nüüd 2 maksimaalsest 3-st 
		System.out.println(bob.getVitality()); // tagastab 2
		

		Player tester = new FlyingPlayer("Tester", b.getStart());
		System.out.println("Tester vitality: " + tester.getVitality());
		System.out.println("Tester maxvitality: " + tester.getMaxVitality());

		//t4.addCard(new RestlessExplorerCard());
		tester.move(t4); 
		//tester.pickUp(t4.getFirstCard());
		tester.backward();tester.backward();tester.forward();
		
		System.out.println("Tester location: " + tester.getLocation().toString());
		System.out.println("Tester vitality: " + tester.getVitality());
		System.out.println("Tester maxvitality: " + tester.getMaxVitality());

	}

}
