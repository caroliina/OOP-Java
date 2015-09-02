package y1.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import y1.pos.Order;

public class Stock {
	List <Transaction> tehingud = new LinkedList <Transaction>();
	
	//Uus map laoseisu hindamiseks
	Map<Item, Integer> stock = new HashMap<Item, Integer>();
	int kogus = 0;
	
	public void receive(Order o){
		System.out.println("Laos on nüüd: ");
		
	}
	
	//Ärasaatmine
	public void dispatch(Order o){
		
		//Võtan vanad tellimused, esialgsed
		Map<Item, Integer> tellimus2 = o.getTellimus();
		Set s1 = tellimus2.entrySet();
	    Iterator it1 = s1.iterator();
	    
	    while(it1.hasNext())
	    {
	        Map.Entry m1 =(Map.Entry)it1.next();
	        Item key1 = (Item)m1.getKey();
	        //Kogused
	        Integer value1 = (Integer)m1.getValue();
	        
	        //Kui laos on sama toode olemas, siis võta tema kogus 
	        //ja kui sisestatud arv on temast väiksem ning positiivne,
	        //siis saada nii palju tooteid laost ära
	        if(stock.containsKey(key1)){
	        	kogus = stock.get(key1);
	        	if(kogus >= value1 && value1 > 0){
	        	kogus -= value1;
	        	stock.put(key1, kogus);
		        
		        Transaction t = new Transaction();
				t.setOrderNr(o.getOrderNr());
				t.setToode(key1);
				t.setQuantity(-value1);
				tehingud.add(t);
	        	
	        	} else { //kui sisestatud kogus ei vasta tingimustele, siis kuva error
	        		throw new IllegalArgumentException("Lubamatu kogus");
	        	}
	        }
	        
	        System.out.println("Lattu jäi alles " + kogus + "x " + key1 + "€");
	        
	    }
	}
	
	//Lattu lisamine
	public String toString(Order o){
	
		String str1 = "";
		String toode = "";
		
		Map<Item, Integer> tellimus1 = o.getTellimus();
		
		Set s = tellimus1.entrySet();
	    Iterator it = s.iterator();
		
		while(it.hasNext())
	    {
	        Map.Entry m =(Map.Entry)it.next();
	        Item key = (Item)m.getKey();
	        toode = "" + key;
	        Integer value = (Integer)m.getValue();
	        kogus = value;
	        
	        //Kui laos see toode olemas, siis suurenda kogust
	        if(stock.containsKey(key)){
	        	kogus+=kogus;
	        	stock.put(key, kogus);
	        	
	        	Transaction t = new Transaction();
				t.setOrderNr(o.getOrderNr());
				t.setToode(key);
				t.setQuantity(value);
				tehingud.add(t);
	        	
	        } else { //Kui laos seda toodet pole, siis lisa lattu uus toode
	        	stock.put(key, value);
	        	
	        	Transaction t = new Transaction();
				t.setOrderNr(o.getOrderNr());
				t.setToode(key);
				t.setQuantity(value);
				tehingud.add(t);
	        }
	        
	        str1 += kogus + "x " + "(" + toode + "€) ";
	        
	    }
		
		return str1 + "\n";
	}
	
	//Näitab palju seda toodet laos on
	public int getAvailable(Item toode){
		int kogus = 0;
		
		if(stock.containsKey(toode)){
        	kogus = stock.get(toode); //toote kogus
        }
		return kogus; //tagasta etteantud toote kogus
		
	}
	
	public List<Transaction> getItemTransactions(Item item){ //toote liikumised
		
		Iterator<Transaction> it = tehingud.iterator();
		List <Transaction> tehingud2 = new LinkedList <Transaction>();
		
		while (it.hasNext()) {
			   Transaction transaction = it.next();
			   if(transaction.getToode().equals(item)){
			    tehingud2.add(transaction);
			   }
			}
		
		return tehingud2; //tagasta liikumised
	}
	
}
