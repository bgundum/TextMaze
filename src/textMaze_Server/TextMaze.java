
package textMaze_Server;

import java.util.*;


public class TextMaze {
	
	private static TextMaze instance=null;
	private Map map;
	private Set<String> playersLoggedIn;

	
	public static synchronized TextMaze getInstance(){
		if(instance==null){
			instance = new TextMaze();
		}
		return instance;
	}
	
	private TextMaze(){
		playersLoggedIn= Collections.synchronizedSet( new HashSet<String>() );
		map=new Map();
	}
	
	
	public synchronized String move(String direction, String username){
		
		Iterator<Player> it = map.getPlayerList().iterator();
			
		while(it.hasNext()){
			Player p=it.next();
			if(p.getUsername().equals(username)){
					
				if(direction.equals("north")){
					int[] pos=p.getPosition();
					
					if((pos[1]+1)!=map.getHeight()){ //border detection
						
						Cell curCell = map.getGrid()[pos[0]][(pos[1]+1)]; 
						
						if(curCell.isPassable()){
							pos[1]++; 
							p.setPosition(pos);
							String collected="";
							
							if(!curCell.isEmpty()){
								curCell.getItem();
								p.addItem();
								collected = " - Found Key";
							}
							
							if(Arrays.equals(pos,map.end())){
								return "WIN";
							}
							return "OK: completed" + collected;
						}
						else
							return"ERR: failed";
					}
					else
						return "ERR: failed";
				}
				else if(direction.equals("south")){
					int[] pos=p.getPosition();
					
					if((pos[1]-1)!= 0){ //border detection
						Cell curCell = map.getGrid()[pos[0]][(pos[1]-1)]; 
						
						if(curCell.isPassable()){
							pos[1]--; 
							p.setPosition(pos);
							String collected="";
							
							if(!curCell.isEmpty()){
								curCell.getItem();
								p.addItem();
								collected = " - Found Key";
							}
							
							if(Arrays.equals(pos,map.end())){
								return "WIN";
							}
							return "OK: completed" + collected;
						}
						else
							return "ERR: failed";
					}
					else
						return "ERR: failed";
				}
				else if(direction.equals("east")){
					int[] pos=p.getPosition();
					
					if((pos[0]+1)!=map.getWidth()){
						Cell curCell = map.getGrid()[pos[0]+1][(pos[1])]; 
						
						if(curCell.isPassable()){
							pos[0]++; 
							p.setPosition(pos);
							String collected="";
							
							if(!curCell.isEmpty()){
								curCell.getItem();
								p.addItem();
								collected = " - Found Key";
							}
							
							if(Arrays.equals(pos,map.end())){
								return "WIN";
							}
							return "OK: completed" + collected;
						}
						else
							return "ERR: failed";
					}
					else
						return "ERR: failed";
				}
				else if(direction.equals("west")){
					int[] pos=p.getPosition();
					
					if((pos[0]-1)!= 0){
						Cell curCell = map.getGrid()[pos[0]-1][(pos[1])]; 
						
						if(curCell.isPassable()){
							pos[0]--; 
							p.setPosition(pos);
							String collected="";
							
							if(!curCell.isEmpty()){
								curCell.getItem();
								p.addItem();
								collected = " - Found Key";
							}
							
							if(Arrays.equals(pos,map.end())){
								return "WIN";
							}
							return "OK: completed" + collected;
						}
						else
							return "ERR: failed";
					}
				}
					else
						return "ERR: failed";
			}
		}
	return "ERR: failed";
	}
	
	public boolean logoff(String username){
		if(!playersLoggedIn.remove(username)){
			return false;
		}
		return true;
	}
	
	public boolean login(String username){
		return playersLoggedIn.add(username);
	}
	
	public boolean newGame(String username){
		Player newPlayer= new Player(username, map.start());
		
		if(map.addPlayer(newPlayer)){
			return true;
		}
		return false;
	}
	
	public synchronized boolean quitGame(String username){
		
		Iterator<Player> it = map.getPlayerList().iterator();
		
		while(it.hasNext()){
			Player p= it.next();
			if(p.getUsername().equals(username)){
				if(map.removePlayer(p));
					return true;
			}
		}
		return false;
	}
	
	public String auditLoggedIn(){
		
		//For debugging only
		
		Iterator<String> it=playersLoggedIn.iterator();
		String p=it.next();
		
		while(it.hasNext()){
			System.out.println(p);
			p=it.next();
		}
		return "OK: Audit complete";
	}
	
}
