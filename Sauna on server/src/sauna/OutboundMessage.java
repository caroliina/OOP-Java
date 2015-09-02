package sauna;

import java.util.LinkedList;

class OutboundMessages {
	private LinkedList<String> messages = new LinkedList<String>(); // Saatmata
																	// sõnumite
																	// FIFO

	public synchronized void addMessage(String s) { // this lukku!
		messages.add(s);
		System.out.println(s);
		this.notifyAll(); // Broadcaster.notify()
	}

	public synchronized String getMessage() {
		try {
			while (messages.isEmpty())
				this.wait();
		} catch (InterruptedException e) {
		}

		String s = messages.getFirst();
		messages.removeFirst();
		return s;
	}
}