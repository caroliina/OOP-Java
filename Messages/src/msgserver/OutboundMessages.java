package msgserver;

import java.util.LinkedList;

class OutboundMessages {
	private LinkedList<String> messages = new LinkedList<String>(); // Saatmata
																	// sõnumite
																	// FIFO
	String to;

	public synchronized void addMessage(String to, String content) { // this
																		// lukku!
		messages.add(content);
		System.out.println(content);
		this.to = to;
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

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}