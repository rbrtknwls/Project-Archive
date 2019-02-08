package pong;


public class ai{
	//Stores the  demenstions, and cords
	private float[] dem = {0.02f, 0.2f};
    private double[] coords = {1f - dem[0], 0.0f};
    


    //Checks where to move bassed of ball position
    public void path (ball ball) {
    		if (ball.getCoords()[1] > this.getCoords()[1]) this.velY(main.max_vol);
    		else this.velY(-main.max_vol);
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
