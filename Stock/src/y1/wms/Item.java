package y1.wms;

import java.text.DecimalFormat;



public class Item {
	
	private String nimetus;
	private double hind;
	
	//Uus toode
	public Item(String nimetus, double hind){
		this.nimetus=nimetus;
		this.hind=hind;
	   }
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	//Toode ja hind stringiks
	public String toString(){
		String s = nimetus + " " + df.format(hind);
		return s;
	}

	public String getNimetus() {
		return nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public double getHind() {
		return hind;
	}

	public void setHind(double hind) {
		this.hind = hind;
	}

	
	
}
