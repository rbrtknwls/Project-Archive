package pong;

public class ball {
	//Stores the increment, demenstions, and cords
	private float[] dem = {0.02f, 0.02f};
    private double[] coords = {0.0f, 0.0f};
    private float[] inc = {0.02f, 0.01f};
    
    public void updateBall (paddle player, ai ai){
    		//Top of the screen edge case
    		if (coords[1] + dem[1] > 1 || coords[1] - dem[1]< -1) inc[1] *= -1;
    		//Off to the Left of the screen
    		if (coords[0] - dem[0]< -1 + player.getDem()[0]) {
    			if (Math.abs(player.getCoords()[1] - coords[1]) <= player.getDem()[1]) {
    				//If ontop of the paddle bounce
    				inc[0] *= -1;
    			}
    			else {
    				//Else decrease score and reset ball
    				main.score -= 1;
    				coords[0] = 0f;
    				coords[1] = 0f;
    				inc[0] *= -1;
    			}
    		}
    		if (coords[0] + dem[0] > 1 - player.getDem()[0]) {
    			if (Math.abs(ai.getCoords()[1] - coords[1]) <= ai.getDem()[1]) {
    				//If ontop of the paddle bounce
    				inc[0] *= -1;
    				
    			}
    			else {
    			//Else decrease score and reset ball
    			main.score += 1;
    			coords[0] = 0f;
			coords[1] = 0f;
			inc[0] *= -1;
    			}
    				
    		}
    		//Increases position
    		this.velY(inc[1]);
    		this.velX(inc[0]);
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
