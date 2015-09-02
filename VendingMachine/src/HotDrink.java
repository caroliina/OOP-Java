
public abstract class HotDrink {
	double costAmount;
	double profitAmount;
	double salesAmount;
	
	public abstract void boilWater();
	public abstract void prepare();
	public abstract void addIngredient();
	public abstract void addSomething();
	
	public void drink (){
		System.out.println("küll on hea :)");
	}
	
	public double getCostAmount(){
		return costAmount;
	}
	public double getProfitAmount(){
		VendingMachine vm = new VendingMachine();
		profitAmount = costAmount * vm.getPercent();
		return profitAmount;
	}
	public double getSalesAmount(){
		salesAmount = costAmount + getProfitAmount();
		return salesAmount;
	}
	
	
}
