package y1.pos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import y1.wms.Item;

public class Cart {
	
	Map<Item, Integer> cart = new HashMap<Item, Integer>();
	int kokku = 0;

	public void add(Item item, int kogus){ //ostukorvi lisamine
		if(cart.containsKey(item)){
			kokku = cart.get(item);
        	kokku += kogus;
        	cart.put(item, kokku);
        } else {
        	cart.put(item, kogus);
        }
	}
	
	public void change(Item item, int kogus){ //muuda-asenda
		if(cart.containsKey(item)){
        	cart.put(item, kogus);
        }
	}
	
	public void clear(){ //tŸhjenda ostukorv
		Iterator it = cart.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry m = (Map.Entry)it.next();
	        it.remove();
	    }
	}
	
	public void remove(Item item){ //eemalda toode
		if(cart.containsKey(item)){
        	cart.remove(item);
        }
	}
	
	public String toString(){
		String str = "";
		String toode;
		String kogus;
		
		Set s = cart.entrySet();
	    Iterator it = s.iterator();
		
		while(it.hasNext()) //kui jŠrgmine eksisteerib
	    {
	        Map.Entry m =(Map.Entry)it.next();
	        
	        //Toode
	        Item key = (Item)m.getKey();
	        //Kogus
	        Integer value = (Integer)m.getValue();
	        
	        toode = "" + key;
	        kogus = "" + value;
	        
	        if(value > 0){
	        str += kogus + "x " + "(" + toode + "Û) ";
	        } else {
	        	throw new IllegalArgumentException("Lubamatu kogus"); //Kogus peab olema suurem 0-ist
	        }
	    }
		return "\n" + str + "\n";
	}
	
	public String getTotal(){ //ostukorvi summa
		double totalSum = 0;
		String item = "";
		
		Iterator it = cart.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry m = (Map.Entry)it.next();
	        Item key = (Item)m.getKey();
	        Integer value = (Integer)m.getValue();
	        item = "" + key;
	        item = item.substring(item.lastIndexOf(" ") + 1);
	            
	        totalSum += Double.valueOf((Double.parseDouble(item))) * value;
	        
	    }
	    DecimalFormat df = new DecimalFormat("0.00");  
	    return df.format(totalSum);
	}
	
	public Order checkOut(){ //uus tellimus
		Order newOrder = new Order();
		newOrder.setTellimus(cart);
		
		return newOrder;
	}
	
	
	
	
}
