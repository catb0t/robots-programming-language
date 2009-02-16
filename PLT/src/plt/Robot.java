package plt;

public class Robot {
	
	private float x;
	private float y;
	private int energy;
	
	public Vector3 forwardDirection = null;
	
	Vector3 direction = null;
	
	public Robot()
	{
		forwardDirection = new Vector3(0, 0, -1);
	
	}
	
	private int t  = 0;
	
	public void think()
	{
		t++;
		float tempx = 10*(float)Math.cos((double)t/10000000.0);
		float tempy = 10*(float)Math.sin((double)t/10000000.0);
		
		forwardDirection = new Vector3(tempx, 10, tempy);
		
		
	}
	
	
	
	
	
	

}
