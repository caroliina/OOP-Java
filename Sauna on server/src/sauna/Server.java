package sauna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private static final int PORT = 8888;

	public static void main(String[] args) throws IOException {

		ActiveSessions activeSessions;
		OutboundMessages outQueue = new OutboundMessages();
		Device light = new Light();
		Device heater = new Heater();

		ServerSocket serv = new ServerSocket(PORT);
		System.out.println("Server startis...");

		String[] temp;
		String txt = "";
		int limit = 0;
		BufferedReader reader = new BufferedReader(new FileReader(
				"src/conf.txt"));
		String line = null;

		while ((line = reader.readLine()) != null) {
			txt += line + " ";
		}

		temp = txt.split(" ");
		String[] allowedUsers = new String[temp.length];

		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equalsIgnoreCase("States:")) {
				heater.setMinTemp(Integer.parseInt(temp[i + 1]));
				heater.setMaxTemp(Integer.parseInt(temp[i + 2]));
			} else if (temp[i].equalsIgnoreCase("Users:")) {
				for (int j = (i + 1); j < temp.length; j++) {
					if (!temp[j].equals("Aeg:")) {
						allowedUsers[j - (i + 1)] = temp[j];
					} else {
						limit = Integer.parseInt(temp[j + 1]);
						heater.setLimit(limit);
						break;
					}
				}
			}
		}
		List<String> allowedUsersList = new ArrayList<String>();
		// System.out.println(txt);
		System.out.println();
		System.out.println("Lubatud kasutajad on: ");
		for (int i = 0; i < allowedUsers.length; i++) {
			if (allowedUsers[i] != null) {
				System.out.println(allowedUsers[i]);
				allowedUsersList.add(allowedUsers[i]);
			}
		}
		System.out.println("Ajalimiit on: " + limit);
		activeSessions = new ActiveSessions(allowedUsersList);

		new Broadcaster(activeSessions, outQueue);

		try {
			while (true) { // serveri töötsükkel
				Socket sock = serv.accept(); // blocked!
				try {
					new ClientSession(sock, outQueue, activeSessions, light,
							heater); // sh. ClientSession.start()
				} catch (IOException e) {
					System.out.println("Socketi loomise avarii :(");
					sock.close();
				}
			}

		} finally {
			System.out.println("Server lõpetas");
			serv.close();
		}
	}
}