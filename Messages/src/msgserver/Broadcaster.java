package msgserver;

import java.util.Iterator;

class Broadcaster extends Thread {
	private ActiveSessions activeSessions;
	private OutboundMessages outQueue;

	Broadcaster(ActiveSessions aa, OutboundMessages o) {
		activeSessions = aa;
		outQueue = o;
		start();
	}

	public void run() {
		while (true) { // Levitaja on igavene...

			String s = outQueue.getMessage(); // blocked
			String to = outQueue.getTo();

			synchronized (activeSessions) { // ActiveSessions lukku!
				Iterator<ClientSession> active = activeSessions.iterator();

				while (active.hasNext()) {
					ClientSession cli = active.next();

					if (!cli.isAlive()) {
						active.remove(); // ;-)
					} else if (to == null) {
						cli.sendMessage(s);
					} else {
						if (cli.getName().equalsIgnoreCase(to)) {
							cli.sendMessage(s);
						}
					}

				}
			}

		}

	}

}
