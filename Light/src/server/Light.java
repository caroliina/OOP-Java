package server;

public class Light {
	
	public Light(){
		
	}
	
	private int brightness = 0;
	private static int MAX_BRIGHTNESS = 3;
	
	public void darker() {
		if (brightness > 0) {
			brightness--;
			System.out.println(this);
		}
	}
	
	public void brighter() {
		if (brightness < MAX_BRIGHTNESS) {
			brightness++;
			System.out.println(this);
		}
	}
	
	public String toString() {
		return ( brightness == 0 
				? "ei p›le" 
				: "p›leb heledusega " + brightness);
	}
	
	public int getBrightness() {
		return brightness;
	}
}
