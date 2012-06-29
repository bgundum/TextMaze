package client;

import java.io.*;
import java.net.Socket;

public class GameClient {
	
	public static String networkCom(BufferedReader sin, PrintWriter sout, String input) throws IOException{
		
		sout.println(input);
		sout.flush();
		//System.out.println("Sent:"+input);
		
		String rmess = sin.readLine();
		System.out.println("Read:"+rmess);
		
		return rmess;
	}

	public static void main(String[] args) {

		String hostname = "localhost";
		int port = 5001;
		
		String rmess;
		
		try {
			
			Socket s = new Socket(hostname,port);
			
			BufferedReader sin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter sout = new PrintWriter(s.getOutputStream());
			BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
			
			String input=new String();
			String userName=new String();
			
			boolean loggedIn=false;
			
			
			System.out.println("Welcome to the Dungeon Crawler Prototype");
			System.out.println("Please enter your username");
			
			userName=cin.readLine();
			input="LOGIN:"+userName;
			rmess=networkCom(sin, sout, input);

			loggedIn=true;
			
			if(rmess.subSequence(0, 3).equals("ERR"))
				loggedIn=false;

			if(loggedIn){	
				
				System.out.println("Type new game or logoff");
				
				while(true){
					
					input=cin.readLine();
					if(input.equalsIgnoreCase("Logoff")){
							
						input="LOGOFF:"+userName;
						rmess=networkCom(sin, sout, input);
							
						if(rmess.substring(0, 4).equals("ERR:")){
							System.out.println("Could not log off. Try again\n" +
												"Type new game or logoff.");
							continue;
						}
						break;
					}
					else if(input.equalsIgnoreCase("New Game")){
						
						input="NEWGAME:"+userName;
						rmess=networkCom(sin, sout, input);				
				
						System.out.println("\nYou can enter move or quit");
						System.out.println("To move type, 'move' and a direction ");
						System.out.println("ie. move north");
						input=cin.readLine();
				
						if(input.equalsIgnoreCase("quit")){
							String msg="QUITGAME:" +userName;
							rmess=networkCom(sin, sout, msg);
							
							System.out.println("Enter new game or logoff.");
							break;
						}
						if(input.substring(0, 4).equalsIgnoreCase("move")){
							
							while(true){
								
						
								if(input.equalsIgnoreCase("quit")){
									String msg="QUITGAME:" +userName;
									rmess=networkCom(sin, sout, msg);
									System.out.println("Enter new game or logoff");
									break;
								}
								
								String msg="MOVE:"+input.substring(5)+":"+userName;
								rmess=networkCom(sin, sout, msg);
								
								if(rmess.substring(0, 3).equals("WIN")){
									msg="QUITGAME:" +userName;
									rmess=networkCom(sin, sout, msg);
									System.out.println("Enter new game or logoff");
									break;
								}
								
								System.out.println("\nYou can enter move or quit");
								input=cin.readLine();
							}
						}
						
					}
				}
			}
			sin.close();
			sout.close();
			cin.close();
			s.close();
			
			
		} catch (Exception e) {
			System.out.println("Error:"+e.getMessage());
		}
	}



}
