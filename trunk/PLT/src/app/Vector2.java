package app;

public class Vector2 {

	public float x;
	public float y;
	
	//empty constructor
	public Vector2()
	{
		x = 0;
		y = 0;
	}
	
	public Vector2(float inX, float inY)
	{
		x = inX;
		y = inY;
	}
	
	public Vector2 add(Vector2 in)
	{
		Vector2 temp = new Vector2(x, y);
		temp.x += in.x;
		temp.y += in.y;
		return temp;		
	}
	
	public Vector2 multiply(float s)
	{
		Vector2 temp = new Vector2(x, y);
		temp.x *= s;
		temp.y *= s;
		return temp;
	}
	
	public void normalize()
	{
		float d = (float)Math.sqrt(x*x + y*y);
		if (d!=0)
		{
			x = x/d;
			y = y/d;
		}
	}
	
	public float distance()
	{
		float temp = (float)Math.sqrt(x*x + y*y);
		return temp;
	}
	
	public float dot(Vector2 in)
	{
		float total = 0;
		total = x*in.x + y*in.y;
		return total;
	}
	
}
