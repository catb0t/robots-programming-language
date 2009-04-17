package app;

public class Resource {

	private String name;
	private Location location;
	private float energy = 0;
	private float ammostash;
	
	public Resource ()
	{
		name = "";
		energy=0;
		ammostash = 0;
	}
	
	
	public void setValue (float f, float a)
	{
		energy = f;
		ammostash = a;
	}
	
	public void setName (String n)
	{
		name = n;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public float getEnergy()
	{
		return energy;
	}
	
	public float getAmmostash()
	{
		return ammostash;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		String sol = "resource name: "+name+", energy="+Float.toString(energy)+", ammostash="+Float.toString(ammostash);
		return sol;
	}
}
