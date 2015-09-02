package y1;

import y1.pos.Order;
import y1.wms.Item;
import y1.wms.Stock;
import y1.pos.Cart;
import y1.wms.Transaction;

public class Program {
	
	//Tellimuse id
	static int orderId = 0;

	public static int getOrderId() {
		return orderId;
	}
	

	public static void setOrderId(int orderId) {
		Program.orderId = orderId;
	}


	public static void main(String[] args) {
		
		//Lisan tooteid
		Item i1 = new Item("Leib", 0.95); 
		Item i2 = new Item("Piim", 1.00);
		Item i3 = new Item("Shokolaad", 0.60);
		System.out.println(i1.toString());
		System.out.println(i2.toString());
		System.out.println(i3.toString());
		
		//Uus tellimus
		Order receipt = new Order();
		receipt.add(i1, 2); 
		receipt.add(i2, 5); 
		receipt.add(i3, 7); 
		
		System.out.println(receipt.toString());
		
		//Ladu
		Stock s = new Stock();
		s.receive(receipt);
		System.out.println(s.toString(receipt));
		s.receive(receipt);
		System.out.println(s.toString(receipt));
		
		//Kui palju seda toodet on
		int kogus = s.getAvailable(i1); //mitu leiba on
		System.out.println(kogus + " tükki olemas.\n");
		
		//Shipment ja palju seejärel lattu jäi
		Order shipment = new Order();
		shipment.add(i3, 4); 
		s.dispatch(shipment); // lattu jäi alles 10 shokolaadi 
		
		Cart c = new Cart(); 
		c.add(i1, 1); // võtame siit ühe leiva... 
		c.add(i2, 2); // ja kaks piima 
		c.change(i1, 3); // tahan ikka 3 leiba
		c.remove(i2); // ei, piima ma vist ei taha 
		c.add(i2, 1); // siiski, võtaks ühe piima 
		c.add(i2, 1); // võtame ühe piima veel 
		System.out.println(c.toString());
		c.clear();
		System.out.println("Ostukorv on tühjendatud!" + c.toString());
		c.add(i2, 2); // lisame kaks piima 
		c.add(i3, 2);
		c.add(i1, 1); // võtame siit ühe leiva...
		c.change(i1, 2); // tahan ikka 2 leiba
		System.out.println(c.toString()); //ostukorvi sisu hetkel
		System.out.println("Ostukorvi kogumaksumus on: " + c.getTotal() + "€\n");
		
		Order shipment2 = c.checkOut(); //ostukorvi tellimus
		s.dispatch(shipment2); //saada välja
		
		System.out.println("\n");
		
		for(Transaction transaction : s.getItemTransactions(i1)) { // tagastab leiva laoliikumised 
            System.out.println("Tellimus:" + transaction.getOrderNr() + " Toode:" + transaction.getToode() + "€ Kogus:" + transaction.getQuantity());
        }
		System.out.println(s.getAvailable(i1));
		System.out.println(s.getAvailable(i2));



	}

}
