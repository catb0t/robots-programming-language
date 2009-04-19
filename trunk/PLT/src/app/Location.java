package app;

public class Location {
	
	public float x;
	public float y;
	public float z;
	
	public Location ()
	{
		x=0;
		y=0;
		z=0;
	}
	
	public Location (float a, float b, float c)
	{
		x=a;
		y=b;
		z=c;
	}
	
	public float getX ()
	{
		return x;
	}
	
	public float getY ()
	{
		return y;
	}
	
	public float getZ ()
	{
		return z;
	}
	
	public Vector3 getVector3()
	{
		return new Vector3(x,y,z);
	}
}
