package cinema;
import ticket.Ticket;
import ticketmachine.TicketMachine;

public class Cinema {
	
	static int ticketNr = 0;
	
	public static int getTicketNr() {
		return ticketNr;
	}

	public static void main(String[] args) {
		
		TicketMachine m = new TicketMachine();
		TicketMachine m2 = new TicketMachine();
		
		Ticket t1 = m.buyTicket("Sherlock Holmes");
		Ticket t2 = m2.buyTicket("Mission Impossible");
		Ticket t3 = m.buyTicket("Sherlock Holmes");
		Ticket t4 = m.buyTicket("Mission Impossible");
		
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println(t3.toString());
		System.out.println(t4.toString());
		
		System.out.println(m.isValid(t1)); // -> true
		System.out.println(m.isValid(t2)); // -> true

		m.useTicket(t1); // -> teade: "HŠid filmielamusi"

		System.out.println(m.isValid(t1)); // -> false

		m2.useTicket(t1); // -> teade: "Pilet ei ole kehtiv!"

		Ticket fake = new Ticket("Sherlock", 7);
		System.out.println(m.isValid(fake)); // -> false

		m.useTicket(fake); // -> teade: "Pilet ei ole kehtiv!"

	}

}
