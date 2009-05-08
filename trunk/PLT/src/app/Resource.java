package app;

public class Resource {

	public String name;
	public Location location;
	public Float energy = 0f;
	public Float ammostash;
	
	public Resource ()
	{
		name = "";
		energy=0f;
		ammostash = 0f;
	}
	
	public Resource (String n, Location l, Float e, Float a)
	{
		name = n;
		location = l;
		energy= e;
		ammostash = a;
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
	
	
	public Resource copy() {
		return new Resource(name, location.copy(), new Float(energy.floatValue()), new Float(ammostash.floatValue()));
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
