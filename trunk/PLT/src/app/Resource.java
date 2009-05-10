package app;

public class Resource {

	public String name;
	public Location location;
	public Float energy = 0f;
	public Float ammostash = 0f;
	public Float speed = 0f;
	public Float health = 0f;
	
	public Resource ()
	{
		name = "";
		energy=0f;
		ammostash = 0f;
		speed = 0f;
		health = 0f;
	}
	
	public Resource (String n, Location l, Float e, Float a, Float s, Float ar)
	{
		name = n;
		location = l;
		energy= e;
		ammostash = a;
		speed = s;
		health = ar;
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
		return new Resource(name, location.copy(), new Float(energy.floatValue()), new Float(ammostash.floatValue()), new Float(speed), new Float(health));
	}
	
	public String toString()
	{
		String sol = "resource "+name+" @"+location.toString()+", energy="+Float.toString(energy)+", ammostash="+Float.toString(ammostash)+", speed="+Float.toString(speed)+", health="+Float.toString(health) ;
		return sol;
	}
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Resource equalTest = (Resource) o;

		return (this.name.equals(equalTest.name)
				&&(this.location.equals(equalTest.location))
				&&(this.energy==equalTest.energy)
				&&(this.ammostash==equalTest.ammostash)
				&&(this.speed==equalTest.speed)
				&&(this.health==equalTest.health));
	}
}
