package app;

public class Location {
	
	public float x;
	public float y;
	
	public Location ()
	{
		x=0f;
		y=0f;
	}
	
	public Location (float a, float b)
	{
		x=a;
		y=b;
	}
	
	public float getX ()
	{
		return x;
	}
	
	public float getY ()
	{
		return y;
	}
	
	public void print() {
		System.out.println("x: "+x+", y: "+y);
	}
	
	public Vector3 getVector3()
	{
		return new Vector3(x,0f,y);
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

	      return (Math.abs(this.x - equalTest.x) < 0.000001
                   && Math.abs(this.y - equalTest.y) < 0.000001);
           }

}
