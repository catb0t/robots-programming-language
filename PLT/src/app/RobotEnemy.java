package app;

import javax.media.opengl.GL;

public class RobotEnemy extends Robot {

	Enemy Enemyfield;
	Vector3 initialPosition;
	
	
	public RobotEnemy (Terrain t)
	{
		super(t);
	}
	
	public RobotEnemy (GL gl, Terrain t)
	{
		super(gl, t);
	}
	
	public void init(Terrain t) {
		forwardDirection = new Vector3(0, 0, -1);
		cameraDirection = new Vector3(0, 10, -10);
		//position = new Vector3(0f,0f,0f);
		position = new Vector3(2f, 2f, 2f);
		goal = new Vector3(0, 0, 0);
		terrain = t;
	}
	
	public void think()
	{
		move_to(funcset.times(new Location(10f *((float) Math.random()),10f), (float) Math.random()));
	}
	
}
