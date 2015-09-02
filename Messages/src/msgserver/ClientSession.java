package msgserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ClientSession extends Thread {
	private Socket socket;
	private OutboundMessages outQueue;
	private ActiveSessions activeSessions;
	private BufferedReader netIn;
	private PrintWriter netOut;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ClientSession(Socket s, OutboundMessages out, ActiveSessions as)
			throws IOException {
		socket = s;
		outQueue = out;
		activeSessions = as;
		netIn = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		netOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
		System.out.println("ClientSession " + this + " stardib...");
		start();
	}

	public void run() {
		try {
			netOut.println("Teretulemast jutustama!");
			netOut.println("Palun ytle oma nimi:");
			String name = netIn.readLine(); // blocked - ootab kliendi nime
			super.setName(name); // anname endale nime

			activeSessions.addSession(this); // registreerime end aktiivsete
												// seansside loendis

			String str = name + " tuli sisse...";
			outQueue.addMessage(null, str); // teatame sellest kõigile

			while (true) { // Kliendisessiooni elutsükli põhiosa ***
				str = netIn.readLine(); // blocked...

				String outMsg = getName() + " lausub: " + str;

				String[] temp;

				String delimiter;

				delimiter = " ";

				temp = str.split(delimiter);

				for (int i = 0; i < temp.length; i++) {
					System.out.println(temp[i]);
				}

				if (str == null) {
					continue; // tuli EOF
				}
				if (temp[0].equalsIgnoreCase("END")) {
					break;
				} else if (temp[0].equalsIgnoreCase("TALK")) {
					String to = temp[1];
					String content = "";
					for (int i = 2; i < temp.length; i++) {
						content += temp[i] + " ";
					}

					Message msg = new Message(to, content);
					outQueue.addMessage(to, name + " ütleb sulle: " + content);
					count++;
				} else if (temp[0].equalsIgnoreCase("WHO")) {
					netOut.println(activeSessions.toString());
				} else if (temp[0].equalsIgnoreCase("TOP")) {
					netOut.println(activeSessions.top());
				} else {
					outQueue.addMessage(null, outMsg);
					count++;
				}

			} // **************************************

			outQueue.addMessage(null, getName() + " lahkus...");

		} catch (IOException e) {
			outQueue.addMessage(null, getName() + " - avarii...");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	public void sendMessage(String msg) {
		try {
			if (!socket.isClosed()) {
				netOut.println(msg);
			} else {
				throw new IOException(); // tegelikult: CALL catch()
			}
		} catch (IOException eee) {
			outQueue.addMessage(null, getName() + " - avarii...");
			try {
				socket.close();
			} catch (IOException ee) {
			}
		}
	}

}