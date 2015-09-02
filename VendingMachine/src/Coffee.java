
public class Coffee extends HotDrink{
	
		@Override
		public void boilWater() {
			System.out.println("Kuumutan vett 95C-ni");
			costAmount += 0.03;
		}
		
		public void addCoffeePowder() {
			System.out.println("Panen kohvipulbri filtrisse");
			costAmount += 0.25;
		}
		@Override
		public void prepare() {
			System.out.println("Valan vee läbi filtri");
		}
		
		public void addSugar() {
			System.out.println("Lisan suhkru ja koore");
			costAmount += 0.07;
			costAmount += 0.15;
		}
		
		@Override
		public void addIngredient(){
			addCoffeePowder();
		}
		@Override
		public void addSomething(){
			addSugar();
		}
}
