package msgserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ActiveSessions {
	private List<ClientSession> sessionList = new ArrayList<ClientSession>(); // Jutustajate
																				// kollektsioon
	private List<ClientSession> sessionListA = new ArrayList<ClientSession>();

	public synchronized void addSession(ClientSession s) { // this lukku!
		sessionList.add(s);
		sessionListA.add(s);
		System.out.println("Saabus uus klient: " + s);

	}

	public Iterator<ClientSession> iterator() { // kus sünkronriseeritakse?
		return sessionList.iterator();
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < sessionList.size(); i++) {
			str += " " + sessionList.get(i).getName();
		}
		return "Serveris on:" + str;
	}

	public String top() {
		String str = "";
		for (int i = 0; i < sessionListA.size(); i++) {
			str += " " + sessionListA.get(i).getName() + " - "
					+ sessionListA.get(i).getCount();
		}
		return "Jutupaunikud:" + str;
	}

}
