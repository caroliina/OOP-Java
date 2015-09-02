package sauna;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ActiveSessions {
	List<String> allowed;
	public ActiveSessions (List allowed) {
		this.allowed=allowed;
	}
	private List<ClientSession> sessionList = new ArrayList<ClientSession>(); // Jutustajate
																				// kollektsioon

	public synchronized void addSession(ClientSession s) { // this lukku!
		if(allowed.contains(s.getName())){
			sessionList.add(s);
			System.out.println("Saabus uus klient: " + s);
		}
		
	}

	public List<String> getAllowed() {
		return allowed;
	}

	public void setAllowed(List<String> allowed) {
		this.allowed = allowed;
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
}