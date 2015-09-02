package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable {
	Socket csocket;
	

	Server(Socket csocket) {
		this.csocket = csocket;
	}

	public static void main(String args[]) throws Exception {
		ServerSocket ssock = new ServerSocket(8088);
		System.out.println("Listening");
		
		
		while (true) {
			Socket sock = ssock.accept();
			System.out.println("Connected");
			new Thread(new Server(sock)).start();
			Light light = new Light();
			Map<String, Light> lights = new HashMap<String, Light>(); 
			lights.put("esik", new Light());
			lights.put("tuba", new Light());
			lights.put("koridor", new Light());
			
			BufferedReader input;
			input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter netOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
			netOut.println("Teretulemast valgusserverisse!");
			
			while(true){
			        String line = input.readLine();
			        line.trim();
			        
			        String action = line.substring(0, line.indexOf(" "));
			        String lamp = line.substring(line.indexOf(" ") + 1); 
			        
			        if(line != null){
			        	if(action.equals("BRI") && lights.containsKey(lamp)){
			        		//netOut.println(lamp);
			        		lights.get(lamp).brighter();
			        		netOut.println(lights.get(lamp).toString());
			        	} else if(action.equals("DARK") && lights.containsKey(lamp)){
			        		//netOut.println(lamp);
			        		lights.get(lamp).darker();
			        		netOut.println(lights.get(lamp).toString());
			        	} else if (action.equals("STATUS") && lights.containsKey(lamp)){
			        		//netOut.println(lamp);
			        		netOut.println(lights.get(lamp).toString());
			        	} else if(action.equals("STATUSES")){
			        		netOut.println(lights);
			        	}
			        }
			}
			
		}
	}

	public void run() {
		
	}
}