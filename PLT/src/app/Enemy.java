package app;

public class Enemy {

	private String name;
	public Vector3 position;
	private float energy;
	
	
	public String toString()
	{
		String sol = "name: "+name+" at ("+Float.toString(position.x)+","+Float.toString(position.y)+","+Float.toString(position.z)+","+"), energy="+Float.toString(energy);
		return sol;
	}
}
