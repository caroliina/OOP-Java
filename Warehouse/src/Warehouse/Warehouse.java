package Warehouse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;



public class Warehouse {
	String t = "";
	public LinkedList<ProductLocation> wh = new LinkedList<ProductLocation>();
	
	public void receive(Product product, String location){
		
		ProductLocation pl = new ProductLocation(product, location);
		wh.add(pl);
	}
	
	public String dispatch(Product product){
		
		
		Iterator<ProductLocation> iterator = wh.iterator(); 
		ProductLocation tmp;
		
		while (iterator.hasNext()){
			tmp = iterator.next();
			
			if(product.getName().equals(tmp.getProduct().getName()) && product.getSize() == (tmp.getProduct().getSize())){
				t = "" + tmp.getLocation();
				iterator.remove();
				break;
			} else t=null;
		}
		
		return t;
	}
	
	public int getItemCount(Product product){
	    int counter = 0;
	    
	    Iterator<ProductLocation> iterator = wh.iterator(); 
		ProductLocation tmp;
		String lc = "";
		
		while (iterator.hasNext()){
			tmp = iterator.next();
			if(product.getName().equals(tmp.getProduct().getName()) && product.getSize() == (tmp.getProduct().getSize())){
				counter++;
			}
		}
		return counter;
	}
}
