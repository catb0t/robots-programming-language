package app;

public class Resource extends RobotType {

	public String name;
	public Location location;
	public float energy = 0;
	public float ammostash;
	
	public Resource ()
	{
		super();
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
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Resource equalTest = (Resource) o;

		return (this.name.equals(equalTest.name)
				&&(this.location.equals(equalTest.location))
				&&(this.energy==equalTest.energy))
				&&(this.ammostash==equalTest.ammostash);
	}
}
