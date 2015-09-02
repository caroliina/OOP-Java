package ticketmachine;

import cinema.Cinema;
import ticket.Ticket;

public class TicketMachine {
	
	Ticket ticket;
	static int ticketNr = Cinema.getTicketNr();

	public Ticket buyTicket(String film){
		++ticketNr;
		ticket = new Ticket(film, ticketNr);
		return ticket;
	}
	
	public boolean isValid(Ticket ticket){
		if (ticketNr != 000){
			return true;
		} else return false;
	}
	
	public void useTicket(Ticket ticket){
		if (ticketNr != 000){
			ticketNr = 000;
			System.out.println("Kasutatud: " + ticket);
			System.out.println("HŠid filmielamusi!");
		} else System.out.println("Pilet ei ole kehtiv!");
	}

}
