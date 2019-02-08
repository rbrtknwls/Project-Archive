
public class Player {
	/*
	 * PLAYER CLASS
	 * - The player class stores the players x and y postion...
	 * yeah thats it, its a pretty simple class, it sets the players x and y
	 * and then creates getters and Setters
	 */
	
	//-------- ATRIBUTES -------
	
	//Int for the player x and y
	private int playerx;
	private int playery;
	
	//-------- CONSTRUCTOR -------
	public Player(int[] playerpos) {
		//converts {int x,int y} into int x and int y
		this.setPlayerx(playerpos[0]);
		this.setPlayerx(playerpos[1]);
	}
	
	//-------- METHODS -------
	
	//Generic getter for player x
	public int getPlayerx() {
		return playerx;
	}
	//Generic setter for playerx
	public void setPlayerx(int playerx) {
		this.playerx = playerx;
	}
	//Generic getter for player y
	public int getPlayery() {
		return playery;
	}
	//Generic setter for playery
	public void setPlayery(int playery) {
		this.playery = playery;
	}
}
