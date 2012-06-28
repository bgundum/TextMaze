
package textMaze_Server;

public class Cell {
	
	private boolean passable;
	private boolean empty;
	private String item;
	
	Cell(){
		this.passable=true;
		this.empty=true;
	}
	
	Cell(boolean passable){
		this.passable=passable;
	}
	
	public boolean isPassable(){
		return this.passable;
	}
	
	public void setPassable(boolean bool){
		this.passable=bool;
	}

	public boolean isEmpty() {
		return this.empty;
	}

	public void setItem(String item) {
		this.item = item;
		this.empty=false;
	}
	
	public synchronized String getItem(){
		this.empty=true;
		return this.item;
	}
	
	
	
	/*
	 * Possible add-ons:
	 * 
	 * -Treasures  * done
	 * -Traps
	 * -Enemies
	 * -Other Players
	 * 
	 */

}
