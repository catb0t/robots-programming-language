package app;

public class Location {
	
	public float x;
	public float y;
	public float z;
	
	public Location ()
	{
		x=0f;
		y=0f;
		z=0f;
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
	
	public void print() {
		System.out.println("x: "+x+", y: "+z+", z: "+z);
	}
	
	public Vector3 getVector3()
	{
		return new Vector3(x,y,z);
	}
	
	 /*
	   public int hashCode() {
	      return (name != null ? name.hashCode() : 0);
	   }
*/
	   public boolean equals(Object o) {
	      
	      if (this == o) return true;
	     
	      if (o == null || getClass() != o.getClass()) return false;

	      Location equalTest = (Location) o;

	      return (this.x==equalTest.x&&this.y==equalTest.y&&this.z==equalTest.z);
	   }

}
