package ticket;

public class Ticket {

	private String film;
	private int ticketNr;
	
	public Ticket(String film, int ticketNr){
		this.film = film;
		this.ticketNr = ticketNr;
	   }
	
	public String toString(){
		String s = film + " " + ticketNr;
		return s;
	}
	
}
