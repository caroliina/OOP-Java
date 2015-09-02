
import sauna.FemaleBather;
import sauna.MaleBather;
import sauna.Sauna;
import util.ThreadViz;

public class Program {
	public static void main(String[] args) {
		
		Sauna s = new Sauna();
		
		MaleBather m1 = new MaleBather("Peeter", s);
		MaleBather m2 = new MaleBather("Kalle", s);
		
		FemaleBather f1 = new FemaleBather("Pille", s);
		FemaleBather f2 = new FemaleBather("Mari", s);
		
		
		ThreadViz.showUI();
	}
}
