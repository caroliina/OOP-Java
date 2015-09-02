
public class Program {

	public static void main(String[] args) {
		
		VendingMachine v = new VendingMachine();
		HotDrink t = v.orderDrink(DrinkType.TEA);
		t.drink(); // küll on hea :)
		
		System.out.println(t.getCostAmount()); // tee sidruni ja meega:0.03+0.05+0.10+0.22 = 0.40€
		System.out.println(t.getSalesAmount()); // klient peab maksma: 0.60€
		HotDrink c = v.orderDrink(DrinkType.COFFEE);
		System.out.println(c.getCostAmount()); //kohv suhkru ja koorega:0.03+0.25+0.07+0.15 = 0.50€
		System.out.println(c.getSalesAmount()); // klient peab maksma: 0.75€
		/*Automaat peaks ka arvet pidama, kui palju on selle abil kaupmees kasu saanud:*/
		double totalprofit = v.getTotalProfit();
		System.out.println(totalprofit); // kahe tellitud joogi peale kokku: 0.45€

	}

}
