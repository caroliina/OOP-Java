package sauna;

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
	private Device light;
	private Device heater;
	private Heating heating;
	private Cooling cooling;

	public ClientSession(Socket s, OutboundMessages out, ActiveSessions as,
			Device light, Device heater) throws IOException {

		socket = s;
		outQueue = out;
		activeSessions = as;
		this.light = light;
		this.heater = heater;
		netIn = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		netOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
		System.out.println("ClientSession " + this + " stardib...");

		start();
	}

	public void run() {
		try {
			// netOut.println("Teretulemast jutustama!");
			netOut.println("Palun ytle oma nimi:");
			String name = netIn.readLine(); // blocked - ootab kliendi nime

			String str;
			if (activeSessions.getAllowed().contains(name)) {
				super.setName(name); // anname endale nime

				activeSessions.addSession(this); // registreerime end aktiivsete
													// seansside loendis

				str = name + " tuli sisse...";
				outQueue.addMessage(str); // teatame sellest kõigile
			} else {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}

			Ventilation vent = new Ventilation("OFF");

			while (true) { // Kliendisessiooni elutsükli põhiosa ***
				str = netIn.readLine(); // blocked...

				String[] temp;
				String delimiter;
				delimiter = " ";
				temp = str.split(delimiter);

				if (str == null) {
					continue; // tuli EOF
				}

				if (temp[0].equalsIgnoreCase("END")) {
					break;
				} else if (temp[0].equalsIgnoreCase("ON")) {
					if (temp[1].equalsIgnoreCase("LIGHT")) {
						light.setStatus("ON");
					} else if (temp[1].equalsIgnoreCase("HEATER")) {
						heater.setStatus("ON");
						if (cooling != null && cooling.isAlive()) {
							cooling.kill();
						}
						heating = new Heating(heater.getMinTemp(),
								heater.getMaxTemp(), heater.getTemp(), heater,
								vent, outQueue, cooling);
					} else if (temp[1].equalsIgnoreCase("VENTILATION")) {
						vent.setState("ON");
					}

				} else if (temp[0].equalsIgnoreCase("OFF")) {
					if (temp[1].equalsIgnoreCase("LIGHT")) {
						light.setStatus("OFF");
					} else if (temp[1].equalsIgnoreCase("HEATER")) {
						heater.setStatus("OFF");
						if (heating != null && heating.isAlive()) {
							heating.kill();
						}

						cooling = new Cooling(heater.getMinTemp(),
								heater.getMaxTemp(), heater.getTemp(), heater,
								vent);

					} else if (temp[1].equalsIgnoreCase("VENTILATION")) {
						vent.setState("OFF");
					}
				} else if (temp[0].equalsIgnoreCase("STATUS")) {
					if (temp[1].equalsIgnoreCase("LIGHT")) {
						netOut.println(light.getStatus());
					} else if (temp[1].equalsIgnoreCase("HEATER")) {
						netOut.println(heater.getStatus() + " "
								+ heater.getTemp());

					}
				} else if (temp[0].equalsIgnoreCase("SEMION")) {
					if (temp[1].equalsIgnoreCase("LIGHT")) {
						light.setStatus("SEMION");
					}
				} else if ((temp[0].equalsIgnoreCase("SETMAX"))
						&& (Integer.parseInt(temp[1]) > heater.getMaxTemp())) {
					heater.setMaxTemp(Integer.parseInt(temp[1]));
					if (cooling != null && cooling.isAlive()) {
						cooling.kill();
					}
					heating = new Heating(heater.getMinTemp(),
							heater.getMaxTemp(), heater.getTemp(), heater,
							vent, outQueue, cooling);
				} else if ((temp[0].equalsIgnoreCase("SETMAX"))
						&& (Integer.parseInt(temp[1]) < heater.getMaxTemp())) {
					heater.setMaxTemp(Integer.parseInt(temp[1]));
					if (heating != null && heating.isAlive()) {
						heating.kill();
					}
					cooling = new Cooling(heater.getMaxTemp(),
							heater.getMaxTemp(), heater.getTemp(), heater, vent);
					if (heater.getTemp() == heater.getMaxTemp()) {
						cooling.kill();
					}
				} else if (temp[0].equalsIgnoreCase("WHO")) {
					netOut.println(activeSessions.toString());
				} else if (temp[0].equalsIgnoreCase("FASTER")) {
					if ((heater.getPower() - 1000) > 0) {
						heater.setPower(heater.getPower() - 1000);
					}
				} else if (temp[0].equalsIgnoreCase("SLOWER")) {
					heater.setPower(heater.getPower() - 1000);
				}
			}

			while (heating.isStop()) {
				outQueue.addMessage("Keris peab välja lülituma!");
				break;
			}

			outQueue.addMessage(getName() + " lahkus...");

		} catch (IOException e) {
			outQueue.addMessage(getName() + " - avarii...");
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
			outQueue.addMessage(getName() + " - avarii...");
			try {
				socket.close();
			} catch (IOException ee) {
			}
		}
	}
}