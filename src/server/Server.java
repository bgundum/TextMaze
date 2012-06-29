
package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		int port = 5001;
		
		ServerSocket ss = new ServerSocket(port);

		System.out.println("Server started on port "+port+", listening...");
		

		while(true) { 
		
			Socket cs = ss.accept();
				
			System.out.println("Got connection from:"+cs.getInetAddress().toString());
		
			GameServer server = new GameServer (cs);
			//server.run();
			
			Thread th = new Thread(server);
			th.start();
		
			//System.out.println("Client closed");
		}
		
	}

}
