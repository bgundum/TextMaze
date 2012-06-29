package server;

import java.io.*;
import java.net.*;

public class GameServer implements Runnable{
	
	public GameServer(Socket ssock) {
		this.ssock = ssock;
		gsp = new GameServerProtocol();
	}

	Socket ssock;
	GameServerProtocol gsp;
	
	public void run() {
		
		try{
		PrintWriter pout = new PrintWriter(ssock.getOutputStream());
		BufferedReader bin = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
	
		String mess;
	
		while((mess=bin.readLine()) != null) { // application loop
		
			System.out.println("Read="+mess);
			
			String rmess = gsp.processMessage(mess);
			pout.println(rmess);
			pout.flush();
		
			System.out.println("Sent="+rmess);
		}
	
		pout.close();
		bin.close();
		ssock.close();
		}
		catch(Exception e){
			System.err.println("Network Communication Error");
			e.printStackTrace();
			System.err.println(e.getMessage());

		}
	}


}
