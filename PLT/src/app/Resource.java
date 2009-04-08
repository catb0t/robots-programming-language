package app;

public class Resource {

	private String name;
	private float value = 0;
	
	public Resource ()
	{
		name = "";
		value=0;
	}
	
	
	public void setValue (float f)
	{
		value = f;
	}
	
	public void setName (String n)
	{
		name = n;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		String sol = "name: "+name+", resource="+Float.toString(value);
		return sol;
	}
}
