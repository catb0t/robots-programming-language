package app;

import javax.media.opengl.GL;

public class RobotEnemy extends Robot {
	
	
	public RobotEnemy (Terrain t, Vector3 initialP)
	{
		super(t);
		position = initialP;
	}
	
	public RobotEnemy (GL gl, Terrain t, Vector3 initialP)
	{
		super(gl, t);
		position = initialP;
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
		move_to(funcset.times(new Location(30f *((float) Math.random()),30f), (float) Math.random()));
		
		ping();
		
		RobotList<Resource> resource_list_perso = sort_incr_Resource(get_resources(),"");
		
		move_to(resource_list_perso.get(1f).location);
	}
	
	public Enemy projectOnEnemyClass() {
		return new Enemy("", new Location(position.x, position.y), health);
	}
	
	public void say (String s)
	{
		
	}
	
}
