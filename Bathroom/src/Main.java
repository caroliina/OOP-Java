
public class Main {

	public static void main(String[] args) {

				
				//Bathroom s = new Bathroom();
				Showers s = new Showers(3);
				
				Person m1 = new Person("Mati", s);
				Person m2 = new Person("Kalle", s);
				
				Person f1 = new Person("Pille", s);
				Person f2 = new Person("Kati", s);
				
				
				ThreadViz.showUI();
			}
		}

	


