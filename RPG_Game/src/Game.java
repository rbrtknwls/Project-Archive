import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Game {
	
	/*------------------ GAME RUNNER AND UPDATER ------------------
	TLDR; this class is event driven, taking user input and implementing 
	the three other classes and displays them on the game panel
	
	This code runs when lower down the main method creates and instance of this class
	This class is also encasplated meaning that no other class can call its fields
	Most of the processing happens within the map class but the Game runner itself
	is incharge of providing the map with user input when someone presses a button because this code
	is event driven.
	
	
	
	------------------------------ FIELDS ------------------------------*/
	//All three of these are the parmaters used when instanating Map and Player
	private int[][] mapSize = new int[100][100];
	private int[][] tempSize = new int[40][40];
	private int[] playerPosition = {1,1};
	
	// Creates the instances of Map (which stores both the "temp map" and "real map" (more on that later)) and player + Jframe
	private Map Map = new Map(mapSize,tempSize);
	private Player Player = new Player(playerPosition);
	private JFrame Frame = new JFrame();
	
	// Creates the booleans used in our toggle buttons
	private boolean fog = false;
	private boolean score = false;
	
	// Stores the score as an int
	private int scoreCounter = 0;
	
	/* Buttons used for going 
	 * L (Left)
	 * R (Right)
	 * U (Up)
	 * D (Down)
	 */
	private JButton b1 = new JButton("L");
	private JButton b2 = new JButton("R");
	private JButton b3 = new JButton("U");
	private JButton b4 = new JButton("D");
	
	/* Buttons used for toggles
	 * L (Left)
	 * R (Right)
	 * U (Up)
	 * D (Down)
	 */
	private JButton b5 = new JButton("Toggle Fog");
	private JButton b6 = new JButton("Toggle Score");
	private JButton b7 = new JButton("Add more enemys");
	
	
	// Finals for colors
	private final Color playerColor = new Color(255,255,255);
	private final Color enemyColor = new Color(128,128,128);
	private final Color abyss = new Color(0,0,0);
	private final Color grass = new Color(0,255,0);
	private final Color grassShaded = new Color(0,51,0);
	private final Color water = new Color(0,255,255);
	private final Color waterShaded = new Color(0,0,153);
	
	
	// Creates a JPanel known as panel
	private JPanel panel = new JPanel() {
		

		public void paint(Graphics g) {
			//Generates a new "mini map" to give the game the appearnce of having a scrolling screen
			Map.gen_currmap(Map.getMap(), Player.getPlayerx(), Player.getPlayery());
			//checks if fog is present
			if (!fog) {
				//runs through the whole temp map
				for (int x = 0; x < Map.getPlayermap().length; x++) {
					for (int y = 0; y < Map.getPlayermap()[x].length; y++) {
						//player is always in the center
						if (x == 20 && y == 20) g.setColor(playerColor);
						//else if (Map.getPlayermap()[x][y] == 1) g.setColor(Color.YELLOW);
						/*
						 * The map is comprised of ints, each one representing a diffrent meaing:
						 * 2 = grass
						 * 3 = water
						 * 4 = enemy
						 * 0 = abyss or edges
						 */
						else if (Map.getPlayermap()[x][y] == 2) g.setColor(grass);
						else if (Map.getPlayermap()[x][y] == 3) g.setColor(water);
						else if (Map.getPlayermap()[x][y] == 4) g.setColor(enemyColor);
						else if (Map.getPlayermap()[x][y] == 0) g.setColor(abyss);
							
						// fills a rectangle in the center of the screen
						g.fillRect(180+x*11, y*11, 11, 11);
						
					}
				}
			} else {
				//runs through the whole temp map
				for (int x = 0; x < Map.getPlayermap().length; x++) {
					for (int y = 0; y < Map.getPlayermap()[x].length; y++) {
						/*
						 * The map is comprised of ints, each one representing a diffrent meaing:
						 * 2 = grass
						 * 3 = water
						 * 4 = enemy
						 * 0 = abyss or edges
						 */
						if (x == 20 && y == 20) g.setColor(playerColor);
						//else if (Map.getPlayermap()[x][y] == 1) g.setColor(Color.YELLOW);
						else if (Map.getPlayermap()[x][y] == 2) g.setColor(grass);
						else if (Map.getPlayermap()[x][y] == 3) g.setColor(water);
						else if (Map.getPlayermap()[x][y] == 4) g.setColor(enemyColor);
						else if (Map.getPlayermap()[x][y] == 0) g.setColor(abyss);
						//Overides the pervious statment of the player's "view" is too small
						if ((x < 15 || x > 25) || (y < 15 || y > 25)) {
							//Adds a diffrent shade to give the apperance of fog
							if (Map.getPlayermap()[x][y] == 2) g.setColor(grassShaded);
							if (Map.getPlayermap()[x][y] == 3) g.setColor(waterShaded);
							if (Map.getPlayermap()[x][y] == 4) g.setColor(grassShaded);
							if (Map.getPlayermap()[x][y] == 0) g.setColor(abyss);
						}
						g.fillRect(180+x*11, y*11, 11, 11);
					} 
				}
			
			}
			//checks to see if score is true
			if (score) {
				g.setColor(abyss);
				//displays the score
				g.drawString("Your score is: " + scoreCounter, 20, 20);
			}
		};
	};
	
	//When the "Enable fog" button is pressed, will flip the value of fog and repaints the frame
	private ActionListener enfog = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//flips the boolean ex: true to false and false to true
			fog = !fog;
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
		}
			
	
	};
	//When the "Add Enemy" button is pressed, will make one more enemy
	private ActionListener add = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Creates one more enemy
			Map.makeEnemies(1);
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
		}
			
	
	};
	//When the "Enable Score" button is pressed, will flip the value of score and repaints the frame
	private ActionListener enscor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//flips the boolean ex: true to false and false to true
			score = !score;
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
		}
			
	
	};
	//Moves the player left and also checks for intersection
	private ActionListener laction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//checks to see if where the player is moving is made of grass
			if ((Player.getPlayerx() > 0) && Map.getMap()[Player.getPlayerx()-1][Player.getPlayery()] == 2){
				Player.setPlayerx(Player.getPlayerx()-1);
				//^^ moves the player
			}
			//Checks to see if there was an enemy instead
			else if(Map.getMap()[Player.getPlayerx()-1][Player.getPlayery()] == 4) {
				//Runs the battle code
				Map.battle(Player.getPlayerx()-1, Player.getPlayery());
				//Adds one to the score
				scoreCounter++;
			}
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
			
		}
			
	
	};
	//Moves the player down and also checks for intersection
	private ActionListener uaction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//checks to see if where the player is moving is made of grass
			if ((Player.getPlayery() > 0) && Map.getMap()[Player.getPlayerx()][Player.getPlayery()-1] == 2){
				Player.setPlayery(Player.getPlayery()-1);
			}
			//Checks to see if there was an enemy instead
			else if(Map.getMap()[Player.getPlayerx()][Player.getPlayery()-1] == 4) {
				//Runs the battle code
				Map.battle(Player.getPlayerx(), Player.getPlayery()-1);
				//Adds one to the score
				scoreCounter++;
			}
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
		}
			
	
	};
	//Moves the player right and also checks for intersection
	private ActionListener raction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//checks to see if where the player is moving is made of grass
			if ((Player.getPlayerx() < Map.getMap().length-1) && Map.getMap()[Player.getPlayerx()+1][Player.getPlayery()] == 2){
				Player.setPlayerx(Player.getPlayerx()+1);
			}
			//Checks to see if there was an enemy instead
			else if(Map.getMap()[Player.getPlayerx()+1][Player.getPlayery()] == 4) {
				//Runs the battle code
				Map.battle(Player.getPlayerx()+1, Player.getPlayery());
				//Adds one to the score
				scoreCounter++;
			}
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
		}
			
	
	};
	//Moves the player down and also checks for intersection
	private ActionListener daction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//checks to see if where the player is moving is made of grass
			if ((Player.getPlayery() < Map.getMap()[0].length-1) && Map.getMap()[Player.getPlayerx()][Player.getPlayery()+1] == 2){
				Player.setPlayery(Player.getPlayery()+1);
			}
			//Checks to see if there was an enemy instead
			else if(Map.getMap()[Player.getPlayerx()][Player.getPlayery()+1] == 4) {
				//Runs the battle code
				Map.battle(Player.getPlayerx(), Player.getPlayery()+1);
				//Adds one to the score
				scoreCounter++;
			}
			//repaints the frame
			Frame.repaint();
			//makes the buttons repaint too so they dont get covered
			Frame.requestFocus();
		}
			
	
	};
	
	
	
	// ------------------------------ CONSTRUCTOR ------------------------------
	public Game() {
		//Sets the frame size to 800 by 500
		Frame.setSize(800,580);
		//Sets the frame location to 100 by 100
		Frame.setLocation(100, 100);
		//Adds the panel to the frame
		Frame.add(panel);
		//Alows the JFrame to have an exit code and not give off an error
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Displays the Jframe
		Frame.setVisible(true);
		
		
		//ADD BUTTONS
		
		/*
		 * Layout:
		 * 1)Bounds
		 * 2)Add Actionlistner
		 * 3)Adds to the frame
		 */
		
		b1.setBounds(35, 480, 40, 40);
		b1.addActionListener(laction);
		Frame.add(b1);
		b2.setBounds(115, 480, 40, 40);
		b2.addActionListener(raction);
		Frame.add(b2);
		b3.setBounds(75, 440, 40, 40);
		b3.addActionListener(uaction);
		Frame.add(b3);
		b4.setBounds(75, 520, 40, 40);
		b4.addActionListener(daction);
		Frame.add(b4);
		
		b5.setBounds(200, 450, 180, 100);
		b5.addActionListener(enfog);
		Frame.add(b5);
		b6.setBounds(400, 450, 180, 100);
		b6.addActionListener(enscor);
		Frame.add(b6);
		b7.setBounds(600, 450, 180, 100);
		b7.addActionListener(add);
		Frame.add(b7);
		
		
		
	}
	// ------------------------------ MAIN METHOD ------------------------------
	public static void main(String[] args) {
		//Instancates the game object to allow everything to run
		Game games = new Game();
		
	}

}

