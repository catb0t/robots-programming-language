package app;

public class Enemy extends RobotType {

	public String name;
	public Location location;
	public float energy;
	
	public Enemy() {
		super();
		name ="";
		location = new Location();
		energy = 0;
	}
	
	public String toString()
	{
		String sol = "enemy name: "+name+" at ("+Float.toString(location.getX())+","+Float.toString(location.getY())+","+"), energy="+Float.toString(energy);
		return sol;
	}
	
	
	//operators
	public float addition ()
	{
		return 0;
	}
}
