package server;

import java.util.*;

import common.GameProtocol;

public class GameServerProtocol implements GameProtocol{
	
	TextMaze game = TextMaze.getInstance();
	
	public String processMessage(String message) {

		StringTokenizer tokenizer = new StringTokenizer(message, ":");
		String token = tokenizer.nextToken();

		if (token.equals("LOGIN")) {
			String userName = tokenizer.nextToken();
			if (game.login(userName)) {
				return "OK: User Logged In";
			}
			else {
				return "ERR: User Already Logged In, Try a different user name";
			}
		} 
		else if (token.equals("MOVE")) {
			String direction = tokenizer.nextToken();
			String userName = tokenizer.nextToken();

			return game.move(direction, userName);
		} 
		else if (token.equals("QUITGAME")) {
			String username = tokenizer.nextToken();
			if(game.quitGame(username)){
				return "OK: Game Quitted";
			}
			else{
				return "ERR: Could not quit";
			}
		} 
		else if (token.equals("LOGOFF")) {
			String username = tokenizer.nextToken();
			if(game.logoff(username)){
				return "OK: User logged off";
			}
			else{
				return "ERR: Could not log off";
			}
		} 
		else if (token.equals("NEWGAME")) {
			String userName= tokenizer.nextToken();
			if(game.newGame(userName)){
				return "OK: New game created";
			}
			else {
				return "ERR: Game not created";
			}
		}
		else if (token.equals("AUDIT")){
			game.auditLoggedIn();
		}

		return "ERR: Bad message";

	}

}
