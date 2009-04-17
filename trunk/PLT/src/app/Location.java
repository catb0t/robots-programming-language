package app;

public class Location {
	
	private float x;
	private float y;
	private float height;
	
	public Location ()
	{
		x=0;
		y=0;
		height=0;
	}
	
	public Location (float a, float b, float c)
	{
		x=a;
		y=b;
		height=c;
	}
	
	public float getX ()
	{
		return x;
	}
	
	public float getY ()
	{
		return y;
	}
	
	public float getHeight ()
	{
		return height;
	}
}
