package app;

public class Enemy {

	private String name;
	public Location location;
	private float energy;
	
	public Enemy(){
		name ="";
		location = new Location();
		energy = 0;
	}
	
	public String toString()
	{
		String sol = "enemy name: "+name+" at ("+Float.toString(location.getX())+","+Float.toString(location.getY())+","+Float.toString(location.getHeight())+","+"), energy="+Float.toString(energy);
		return sol;
	}
	
	
	//operators
	public float addition ()
	{
		return 0;
	}
}
