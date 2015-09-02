package ylesanne_1;

public class Reisija {
	
	String nimi;
	int vanus;
	String elukoht;
	Boolean sõiduõigus;
	String olek;
	String soiduoigus = "";
	static int count = 0;
	
	public Reisija(String nimi, int vanus,  String elukoht, Boolean sõiduõigus){
		this.nimi=nimi;
		this.vanus=vanus;
		this.elukoht=elukoht;
		this.sõiduõigus=sõiduõigus;
		
		if (count >= 40){
			System.out.println("Buss on täis!");
		} else {
			count++;
		}
		
	   }
	
	public String toString(){
		
		if (tasutaSoit()){
			if(sõiduõigus == false && elukoht != "Tallinn" && elukoht != "Saku"){
			soiduoigus = "puudub";
			sõiduõigus = false;}
			else {
				soiduoigus = "olemas";
				sõiduõigus = true;
			}
		} else {
			soiduoigus = "olemas";
			sõiduõigus = true;
		}
		String s = "Nimi: " + nimi + " vanus: " + vanus + " sõiduõigus: " + soiduoigus;
		return s;
	}
	
	public boolean tasutaSoit(){
		if (vanus<5 || vanus>65){
			
			return false;
		} else {
			return true;
		}
		

	}

}
