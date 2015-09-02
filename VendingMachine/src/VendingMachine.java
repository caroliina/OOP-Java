
public class VendingMachine {
	
	double totalProfit;

	public void makeDrink(HotDrink hd){
		hd.boilWater();
		hd.addIngredient();
		hd.prepare();
		hd.addSomething();
		
		totalProfit += hd.getProfitAmount();
		}
	
	double percent = 0.5;
	
	public double getPercent() {
		return percent;
	}

	public HotDrink orderDrink(DrinkType t){
		HotDrink hd = null;
		if (t==DrinkType.TEA){
			hd = new Tea();
			makeDrink(hd);
		} else if (t==DrinkType.COFFEE){
			hd = new Coffee();
			makeDrink(hd);
		}
		return hd;	
	}
	
	public double getTotalProfit(){
		return totalProfit;
	}
}
