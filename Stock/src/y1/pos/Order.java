package y1.pos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import y1.Program;
import y1.wms.Item;


public class Order {
	
	//Tellimused
	private Map <Item, Integer> tellimus = new HashMap <Item, Integer>();
	
	//Tellimuse id, tellimusele unikaalne id
	private static int orderNr = Program.getOrderId();
	
	public Order(){
		++orderNr;
		Program.setOrderId(orderNr);
		//System.out.println(orderId);
	}
	
	//Lisan uue toote koos kogusega
	public void add(Item toode, int kogus){
		tellimus.put(toode, kogus);
	}
	
	//Vaatame tooted(kogused) lŠbi, vŠljastame tooted kui k›ike on lubatud kogustes
	public String toString(){
		
		String str = "";
		String toode;
		String kogus;
		
		Set s = tellimus.entrySet();
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


	public Map<Item, Integer> getTellimus() {
		return tellimus;
	}

	public void setTellimus(Map<Item, Integer> tellimus) {
		this.tellimus = tellimus;
	}
	
	public static int getOrderNr() {
		return orderNr;
	}
	
}
