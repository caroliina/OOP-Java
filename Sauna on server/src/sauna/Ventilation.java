package sauna;

public class Ventilation {
	String state = "OFF";
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Ventilation(String state) {
		this.state=state;
	}
}
