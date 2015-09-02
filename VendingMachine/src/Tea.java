
public class Tea extends HotDrink{

		@Override
		public void boilWater() {
			System.out.println("Keedan vett");
			costAmount += 0.03;
		}
		public void soakTeaBag() {
			System.out.println("Pistan teepaki kuuma vette");
			costAmount += 0.05;
		}
		@Override
		public void prepare() {
			System.out.println("Valan teevee tassi");
		}
		public void addLemon() {
			System.out.println("Lisan sidrunit ja mett");
			costAmount += 0.10;
			costAmount += 0.22;
		}
		
		@Override
		public void addIngredient(){
			soakTeaBag();
		}
		@Override
		public void addSomething(){
			addLemon();
		}
}
