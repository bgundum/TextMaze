
package textMaze_Server;

public class Player {
	
	private String name;
	private int[] position;
	int itemCount;

	public int getItemCount() {
		return itemCount;
	}

	public void addItem() {
		this.itemCount++;
	}

	public Player(String name, int[] position){
		this.name=name;
		this.position=position;
		this.itemCount=0;
	}
	
	public String getUsername(){
		return this.name;
	}
	
	public void setUsername(String name){
		this.name=name;
	}
	
	public void setPosition(int[] position){
		this.position=position;
	}
	
	public int[] getPosition(){
		return this.position.clone();
	}
	/*
	 * Possible add-ons:
	 * 
	 * -Inventory
	 *   *items
	 *   *equipment
	 *   *weapons
	 * -Status
	 *   *ATK
	 *   *DEF
	 *   *AGL
	 *   *AILMENTS
	 *   
	 */
}
