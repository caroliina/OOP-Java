package ylesanne_1;

public class Reisija {
	
	String nimi;
	int vanus;
	String elukoht;
	Boolean s�idu�igus;
	String olek;
	String soiduoigus = "";
	static int count = 0;
	
	public Reisija(String nimi, int vanus,  String elukoht, Boolean s�idu�igus){
		this.nimi=nimi;
		this.vanus=vanus;
		this.elukoht=elukoht;
		this.s�idu�igus=s�idu�igus;
		
		if (count >= 40){
			System.out.println("Buss on t�is!");
		} else {
			count++;
		}
		
	   }
	
	public String toString(){
		
		if (tasutaSoit()){
			if(s�idu�igus == false && elukoht != "Tallinn" && elukoht != "Saku"){
			soiduoigus = "puudub";
			s�idu�igus = false;}
			else {
				soiduoigus = "olemas";
				s�idu�igus = true;
			}
		} else {
			soiduoigus = "olemas";
			s�idu�igus = true;
		}
		String s = "Nimi: " + nimi + " vanus: " + vanus + " s�idu�igus: " + soiduoigus;
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
