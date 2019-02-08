
public class Enemy {
	/*
	 * ENEMY CLASS
	 * - The Enemy class stores the Enemie's x and y postion...
	 * Also the getters and setter use some really edgy notation
	 * cause my boi olie told me to it like this
	 */
	//-------- ATRIBUTES -------
	
	//Int for the enemy x and y
	private int x;
	private int y;
	public Enemy(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	//Getters
	public int getX() {return(x);}
	public int getY() {return(y);}
	//Setters
	public void setX(int _x) {x = _x;}
	public void setY(int _y) {y = _y;}
}
