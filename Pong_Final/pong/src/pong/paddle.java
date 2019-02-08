package pong;

import static org.lwjgl.glfw.GLFW.*;

public class paddle{
	
	//Stores the dementions of the shape, as a float in a 2D array for easy access
	private float[] dem = {0.02f, 0.2f};
	//Stores the x and y value of the shape in a 2D array
    private double[] coords = {-1f + dem[0], 0.0f};
    
    //Code that will be used later, that allows the main class to pass in a key, and the player to process it later
    public void key_press (int key){
        if (key == GLFW_KEY_UP){
        		this.velX(main.max_vol);
        }
        else if (key == GLFW_KEY_DOWN){
        		this.velX(-main.max_vol);
        }
    }

    //Returns the Coords
    public double[] getCoords(){
        return coords;
    }
    //Returns the Dementions
    public float[] getDem() {
    		return dem;
    }

  //Sets the Coords
    public void setCoords(double[] newValues){
        coords = newValues;
    }
    //Will increase the y pos based on newValue
    public void velY(double newValue){
        coords[1] = coords[1] + newValue;
    }
   //Will increase the x pos based on newValue
    public void velX(double newValue){
        coords[0] = coords[0] + newValue;
    }
}
