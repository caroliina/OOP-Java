package y1.wms;


public class Transaction { 
	
	private int quantity;
	private Item toode;
	private int orderNr;
	
	public int getQuantity() { //toote kogus
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Item getToode() { //toode
		return toode;
	}
	public void setToode(Item toode) {
		this.toode = toode;
	}
	public int getOrderNr() { //tellimuse number
		return orderNr;
	}
	public void setOrderNr(int orderNr) {
		this.orderNr = orderNr;
	}
	
	

	
	

}
