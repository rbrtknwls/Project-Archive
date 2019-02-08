package pong;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.Scanner;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

/*
 * Author: Robbie Knowles
 * Goal: To create pong
 */



public class main {
	//--------Global Varibales--------
	
	//Max Velocity of each object, placeholder for acceleration
    public static float max_vol = 0.05f;
    //Score of the person
    public static float score = 0;
    //Name of the player (I would get input with a scanner, but when you wait for the input via a scanner), the game still processes)
    public static String name = "Alex";
    //--------------------------------

	public static void main(String args[]){
		//--------Initating/Instanciating  the Graphics Library--------
		//Starts the Graphics library we are using
        if (!glfwInit()){
            throw new IllegalStateException("Failed to initialize GLFW!");
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        //Creates the window object
        long window = glfwCreateWindow(videoMode.width(), videoMode.height(), "RuR", glfwGetPrimaryMonitor(), 0);
        glfwShowWindow(window);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glClearColor(0, 0, 0, 0);
        
      //--------Instantiating each object--------
        //Instantiates a paddle class, which acts a player.
        paddle p1 = new paddle();
        //Instantiates an ai class, which is the enemy.
        ai en = new ai();
        //Instantiates a ball, which is a ball.
        ball ball = new ball();
      //--------Game Loop--------
        while (!glfwWindowShouldClose(window)){
            //Processes events
            glfwPollEvents();
            //Clears the screen (context)
            glClear(GL_COLOR_BUFFER_BIT);
            
            
          //--------Updates the player/ball/enemy--------
            //Checks to see if the button up or button down is pressed
            	if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_TRUE && p1.getCoords()[1] + p1.getDem()[1] <= 1) p1.velY(max_vol);
            	if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_TRUE && p1.getCoords()[1] - p1.getDem()[1] >= -1) p1.velY(-max_vol);
            	
            	//Updates the ball based on the player and enemy
            	ball.updateBall(p1, en);
            	// Updates the enemy based off the ball
            	en.path(ball);
            	
          //--------Drawing stage--------
            	draw_rect(ball);
            draw_rect(p1);
            draw_rect(en);
            //Swaps buffers (display and draw)
            glfwSwapBuffers(window);
        }
        //Prints out the score at the end with the name, edited so that it was capitilzed
        if (name.length() > 1) System.out.println(name.substring(0,1).toUpperCase() +name.substring(1).toLowerCase() + " had a final score of " + score);
        else System.out.println(name.toUpperCase() + " had a final score of " + score);
        glfwTerminate();
        //Closes app and cleans memory
        
    }
    
	//Repeated for each class, as I was to lazy to do some class inheritence stuff
    public static void draw_rect(paddle p){
    		//@param: paddle class
    		//Begins the Draw
        glBegin(GL_QUADS);
        glColor4f(0, 255, 128, 0);
        //Creates 4 points based on the player position and dementions, and with a color
        glVertex2f(-p.getDem()[0]+(float)p.getCoords()[0], p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(p.getDem()[0]+(float)p.getCoords()[0], p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(p.getDem()[0]+(float)p.getCoords()[0], -p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(-p.getDem()[0]+(float)p.getCoords()[0], -p.getDem()[1]+(float)p.getCoords()[1]);
        glEnd();
   
    }
    
    public static void draw_rect(ball p){
    		//@param: ball class
    		//Begins the Draw
        glBegin(GL_QUADS);
        glColor4f(0, 255, 128, 0);
        //Creates 4 points based on the player position and dementions, and with a color
        glVertex2f(-p.getDem()[0]+(float)p.getCoords()[0], p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(p.getDem()[0]+(float)p.getCoords()[0], p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(p.getDem()[0]+(float)p.getCoords()[0], -p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(-p.getDem()[0]+(float)p.getCoords()[0], -p.getDem()[1]+(float)p.getCoords()[1]);
        glEnd();
    }
    
    public static void draw_rect(ai p){
    		//@param: ai class
		//Begins the Draw
        glBegin(GL_QUADS);
        //Creates 4 points based on the player position and dementions, and with a color
        glColor4f(0, 255, 128, 0);
        glVertex2f(-p.getDem()[0]+(float)p.getCoords()[0], p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(p.getDem()[0]+(float)p.getCoords()[0], p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(p.getDem()[0]+(float)p.getCoords()[0], -p.getDem()[1]+(float)p.getCoords()[1]);
        glVertex2f(-p.getDem()[0]+(float)p.getCoords()[0], -p.getDem()[1]+(float)p.getCoords()[1]);
        glEnd();
    }

  

}
