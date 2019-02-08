import java.util.ArrayList;
import java.util.Random;

public class Map {
	/*
	 * THE MAP CLASS
	 * is in charge of creating both the main map and the temp map:
	 * EX:
	 * MAIN      Temp
	 * xxxxxx     xx
	 * Axxx**     Ax
	 * xxxx**
	 * The temp map simply takes segments from the main map and makes it into its own
	 * Its also in charge of storing the enemies as well as randomizaing the map
	 */
	
	// ATRIBUTES
	
	//Creates both the main and temp aka player MAPS
	private int map[][];
	private int playermap[][];
	
	//Creates a array list of enimes that can be added onto
	ArrayList<Enemy> enemies = new ArrayList<>();
	
	// CONSTRUCTER
	public Map(int[][] map, int[][] playermap) {
		// Stores both the map and the temp map locally
		this.map = map;
		this.playermap = playermap;
		
		// Calls the randomize functions
		randomize(map);
	}
	
	// METHODS
	/*
	 * This method changes the local map to be in relation to player position, shifting it b
	 */
	public void gen_currmap(int[][] map, int playerx, int playery) {
		this.setPlayermap(currmap(map, playerx, playery));
	}
	
	
	/*
	 * This method randomizes the map by making a path that always leads to the end of the map
	 * While also generatering "fauna" in the forum of cirlces and enemys to spice up the land scape
	 */
	public void randomize(int[][] map) {
		Random r = new Random();
		
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				//Sets everyblock to be water so the grass can cover it up and the remainder becomes lakes
				map[x][y] = 3;
			
			}
		}
		//Creates an x and y for current position
		int x = 0,y = 0;
		map[x][y] = 2;
		while (x != map.length && y != map.length) {
			if (r.nextInt(2) < 1) x++;
			else y++;
			//by only moving down or to the right, we are guaranteed that the code will create a path
			for (int xi = 0; xi < 3+ r.nextInt(4); xi++) {
				for (int yi = 0; yi < 3+ r.nextInt(4); yi++) {
					//Makes a randomly sized "box" which means the path can have diffrent widths
					setval(x+xi, y+yi, 2);
				}
			}
		}
		//Makes 50 diffrent enemys
		makeEnemies(50);
		//Makes 35 "Lakes" aka Fauna with a radius of 7
		makelakes(7,35);
	}
	public void makelakes(int r, int n) {
		//for each lake
		for (int lakes = 0; lakes < n; lakes++) {
			Random ran = new Random();
			//picks a random starting val using the system of {x,y}
			int[] Startingval = {ran.nextInt(map.length),ran.nextInt(map[0].length)};
			for (int y = -r; y < r; y++) {
				for (int x = -(r - Math.abs(y)); x < r - Math.abs(y) ; x++) {
					/*
					 * Function I made to make a circular pattern, kinda did it at 3am
					 * much like this commenting so its kinda blur but it works like:
					 * z*z
					 * ***
					 * z*z z is nothing
					 */
					
					//calls the setval function which has a built in out of bounds
					setval(x + Startingval[1],Startingval[0]+y,2);
				}
			}
		}
	}
	//checks to see if something is in the map
	public boolean insidemap(int x, int y) {
		//returns if x and y is <max and >0
		return(x < map.length && x>= 0) && (y < map[0].length && y >= 0);
	}
	//sets a value in a map to be annother value and checks to see if the index is valid
	public void setval(int x, int y, int val) {
		//checks to see if the val is inside the map to avoid an index out of bounds
		if (insidemap(x,y)) {
			map[y][x] = val;
		}
		
	}

	// Creates a super cool temp list using lots of cool things I founc
	public int[][] currmap(int[][] map, int x, int y){
		int[][] temp = new int[40][40];
		for (int mapx = 0; mapx < 40; mapx++) {
			for (int mapy = 0; mapy < 40; mapy++) {
				// Much like the insidemap functions, by init the temp map to
				// 0 it means that things we dont itterate over (such as index -1,-2,-3)
				// can still be represented without a error
				temp[mapx][mapy] = 0;
			}
		}
		
		for (int mapx = x - 20; mapx < x+20; mapx++) {
			for (int mapy = y-20; mapy < y+20; mapy++) {
				//makes a kind of box and then checks if each value is actually in the map
				if (mapx >= 0 && mapx < map.length && mapy >= 0 && mapy < map[0].length) {
					//sets the temp map location to be the actually map location
					temp[mapx-x+20][mapy-y+20] = map[mapx][mapy];
					
				}
			}
		}
		//returns the 2d list
		return(temp);
		
		
	}
	//Creates a bunch of instances of the Enemy class and adds it to the arry list
	public void makeEnemies(int count){
	    Random rand = new Random();
	    // for each in the count paramiter 
	    for(int i = 0; i < count; i++){
	    		//picks a random point on the map
	    		int x = rand.nextInt(map.length), y = rand.nextInt(map[0].length);
	    		//makes sure that point is land
	    		if (map[y][x] == 2) {
	    			// adds a new enemy to the array list
	    			enemies.add(new Enemy(x, y));
	    			//sets the val on the map so that it can display the enemy
	    			setval(enemies.get(i).getX(), enemies.get(i).getY(), 4);
	    		}else i--;
	    		//^^ resets and runs again so the enemy can respawn on the map
      }
	}
	//Unused for anying really, practicly finds the index and checks to see if the player is ontop, replacing the
	//enemy with a grass block
	public void battle(int y, int x) {
		int enemyIndex = 0;
		for(int i = 0; i < enemies.size(); i++) {
			//checks x and ys of the player and enemy
			if(enemies.get(i).getX() == x && enemies.get(i).getY() == y) {
				enemyIndex = i;
			}
		}
		//resets the val
		setval(enemies.get(enemyIndex).getX(), enemies.get(enemyIndex).getY(), 2);
		
	}
	
	// GETTERS AND SETTERS
	
	public int[][] getPlayermap() {
		return playermap;
	}
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int playermap[][]) {
		this.map = playermap;
	}
	
	public void setPlayermap(int playermap[][]) {
		this.playermap = playermap;
	}
}
