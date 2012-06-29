
package server;

import java.util.*;

public class Map {
	
	private String name;
	private int width, height, numPlayers;
	private final int TOTAL_PLAYERS=5;
	private int[] start, end;
	private Cell[][] cell;
	private Set<Player> playerList;
	
	public Map(){
		numPlayers=0;
		start = new int[] {2, 1};
		end = new int[] {6, 9};
		width=10; height=10;
		playerList= Collections.synchronizedSet(new HashSet<Player>());
		cell=new Cell[width][height];
		
		// Set all to not passable
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				cell[i][j]=new Cell();
				cell[i][j].setPassable(false);
			}
		}
		
		//create maze path
		for(int i=0;i<width;i++){
			
			switch(i){
				case 0:
					cell[i][4].setPassable(true);
					break;
				case 1:
					cell[i][4].setPassable(true);
					break;
				case 2:
					for(int j=0; j<height; j++){
						if(j>0 && j<6)
							cell[i][j].setPassable(true);
					}
					break;
				case 3: 
					cell[i][2].setPassable(true);
					cell[i][4].setPassable(true);
					cell[i][5].setPassable(true);
					break;
				case 4:
					for(int j=0;j<width;j++){
						if(j>1 && j<width)
							cell[i][j].setPassable(true);
					}
				case 5:
					cell[i][1].setPassable(true);
					cell[i][2].setPassable(true);
					cell[i][5].setPassable(true);
					cell[i][9].setPassable(true);
					break;
				case 6:
					cell[i][2].setPassable(true);
					cell[i][5].setPassable(true);
					break;
				case 7:
					for(int j=0;j<height;j++)
						if(j>1 && j<8)
							cell[i][j].setPassable(true);
					break;
				default: 
					continue;  
			}
		}
		
		//set start point
		cell[start[0]][start[1]].setPassable(true);
		
		//set end point
		cell[end[0]][end[1]].setPassable(true);
		
		//set items
		cell[2][2].setItem("key");
		cell[4][4].setItem("key");
		cell[4][7].setItem("key");
		cell[7][4].setItem("key");
	}
	
	public int[] start(){
		return this.start;
	}
	
	public int[] end(){
		return this.end;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public Set<Player> getPlayerList(){
		return this.playerList;
	}
	
	public void setPlayerList(Set<Player> playerList){
		this.playerList=playerList;
	}
	
	public Cell[][] getGrid(){
		return this.cell;
	}
	
	public boolean addPlayer(Player newPlayer){
		if(numPlayers<TOTAL_PLAYERS && this.playerList.add(newPlayer)){
			numPlayers++;
			return true;
		}
		return false;
	}
	
	public boolean removePlayer(Player player){
		if(numPlayers>0){
			this.playerList.remove(player);
			numPlayers--;
			return true;
		}
		return false;
	}
	
}
