package plt;

public class Robot {
	
	public float x = 0;
	public float y = 0;
	public float z = 0;
	private int energy;
	
	public Vector3 forwardDirection = null;
	
	Vector3 direction = null;
	Terrain terrain;
	
	
	public Robot(Terrain t)
	{
		forwardDirection = new Vector3(0, 0, -1);
		terrain = t;
	}
	
	private int t  = 0;
	
	/* the phsyics function handles all the physics dealing with the robot, for example, when 
	 * 
	 */
	public void setY(int Y)
	{
		y = Y;
	}
	
	public Vector3 getPosition()
	{
		return new Vector3(x, y, z);
	}
	
	
	public void think()
	{
		t++;
		
		//physics();
		
		float tempx = 10*(float)Math.cos((double)t/10000000.0);
		float tempy = 10*(float)Math.sin((double)t/10000000.0);
		
		forwardDirection = new Vector3(tempx, y + 5, tempy);
		
		
	}
	
	
	
	
	
	

}
