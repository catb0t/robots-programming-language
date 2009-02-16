package plt;

public class Vector3 {

	public float x;
	public float y;
	public float z;
	
	//empty constructor
	public Vector3()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3(float inX, float inY, float inZ)
	{
		x = inX;
		y = inY;
		z = inZ;
	}
	
	public Vector3 add(Vector3 in)
	{
		Vector3 temp = new Vector3(x, y, z);
		temp.x += in.x;
		temp.y += in.y;
		temp.z += in.z;
		return temp;		
	}
	
	public Vector3 multiply(float s)
	{
		Vector3 temp = new Vector3(x, y, z);
		temp.x *= s;
		temp.y *= s;
		temp.z *= s;
		return temp;
	}
	
	public void normalize()
	{
		float d = (float)Math.sqrt(x*x + y*y + z*z);
		x = x/d;
		y = y/d;
		z = z/d;	
	}
	
	public float distance()
	{
		float temp = (float)Math.sqrt(x*x + y*y + z*z);
		return temp;
	}
	
	public float dot(Vector3 in)
	{
		float total = 0;
		total = x*in.x + y*in.y + z*in.z;
		return total;
	}
	
	public Vector3 cross(Vector3 in)
	{
		float newX = y*in.z - z*in.y;
		float newY = z*in.x - x*in.z;
		float newZ = x*in.y - y*in.x;
		
		Vector3 temp = new Vector3(newX, newY, newZ);
		return temp;
	}
	
	
	
	
}
