package ylesanne_1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Buss {
	
	private List <Reisija> reisijad = new LinkedList <Reisija>();
	
	public static void main(String []args){
	Buss buss = new Buss();
	Reisija uusReisija = new Reisija("Martin", 18, "Tartu", false);
	buss.minePeale(uusReisija);
	buss.minePeale(new Reisija("Linda", 70, "Tartu", true));
	buss.minePeale(new Reisija("Maria", 50, "Saku", false));
	buss.minePeale(new Reisija("Mihkel", 55, "Tartu", false));
	buss.minePeale(new Reisija("Mikel", 76, "Tallinn", false));
	buss.kontrolliPileteid();
	buss.mupo();
	}

	void minePeale(Reisija r){
		if (Reisija.count < 40){
		reisijad.add(r);
		}
	}
	
	void kontrolliPileteid(){
		for (Reisija r : reisijad){
			System.out.println(r.toString());
		}
	}
	
	void mupo(){
		System.out.println("Peale kontrolli: ");
			
			Iterator<Reisija> it = reisijad.iterator();
			while (it.hasNext()) {
			   Reisija reisija = it.next();
			   if(reisija.s›idu›igus == false){
			    it.remove();
			    Reisija.count--;
			   }
			}
		kontrolliPileteid();
		
	}

}
