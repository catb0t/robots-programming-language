package app;

import java.io.*;
import java.util.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Robot {
	
	GL gl;
	
	private int energy;
	
	float max_speed=1;
	
	public Vector3 forwardDirection = null;
	public Vector3 position = null;
	
	LinkedList<Enemy> enemy_list;
	LinkedList<Resource> resource_list;
	
	Vector3 direction = null;
	Terrain terrain;
	OBJ_Model robot_head;
	OBJ_Model robot_mouth;
	OBJ_Model robot_lefteye;
	OBJ_Model robot_leftear;
	OBJ_Model robot_leftear_ball;
	OBJ_Model robot_torso;
	OBJ_Model robot_shoulders;
	
	
	public Robot(Terrain t)
	{
		forwardDirection = new Vector3(0, 0, -1);
		position = new Vector3(0, 0, 0);
		terrain = t;
	}
	
	public Robot(GL g, Terrain t)
	{
		gl = g;
		forwardDirection = new Vector3(0, 0, -1);
		terrain = t;
		
//		robot_head = new OBJ_Model(gl, "robot/robot_head.obj");
//		robot_mouth = new OBJ_Model(gl, "robot/robot_mouth.obj");
//		robot_lefteye = new OBJ_Model(gl, "robot/robot_lefteye.obj");
//		robot_leftear = new OBJ_Model(gl, "robot/robot_leftear.obj");
//		robot_leftear_ball = new OBJ_Model(gl, "robot/robot_leftear_ball.obj");
		
		robot_torso = new OBJ_Model(gl, "robot/robot_torso.obj");
//		robot_shoulders = new OBJ_Model(gl, "robot/robot_shoulders.obj");
		
		
		forwardDirection = new Vector3(0, 0, -1);
		position = new Vector3(0, 0, 0);
	}
	
	//This is for only UnitTest.
	public Robot() {
		// TODO Auto-generated constructor stub
	}

	public void renderRobot(GL gl)
	{
		float scale = 30;
		gl.glScalef(scale, scale, scale);
		//robot_head.render(gl);
		//robot_mouth.render(gl);
	//	robot_lefteye.render(gl);
	//	robot_leftear.render(gl);
	//	robot_leftear_ball.render(gl);
		robot_torso.render(gl);
	//	robot_shoulders.render(gl);
		
		
	}
	
	
	private int t  = 0;
	
	/* the physics function handles all the physics dealing with the robot, for example, when 
	 * 
	 */
	public void setY(int Y)
	{
		position.y = Y;
	}
	
	public Vector3 getPosition()
	{
		return new Vector3(position.x, position.y, position.z);
	}
	
	public Vector2 getPositionVector2(){
		return new Vector2(position.x,position.y);
	}
	
	public void think()
	{
		t++;
		
		//physics();
		
		float tempx = 10*(float)Math.cos((double)t/10000000.0);
		float tempy = 10*(float)Math.sin((double)t/10000000.0);
		
		forwardDirection = new Vector3(tempx, position.y + 5, tempy);
		
		
	}
	
	
	public void say (String s)
	{
		System.out.println(s);
	}
	
	
	public void move_to (Vector3 location, float speed)
	{
		Vector3 direction = new Vector3(location.x - position.x, location.y - position.y, location.z - position.z);
		float step = speed*max_speed;
		
		position = new Vector3(position.x+ step*direction.x, position.y+ step*direction.y, position.z+ step*direction.z);
	}
	
	public void move_to (Vector2 location, float speed){
		
	}
	
	public void shoot ()
	{
		//decrease energy
		//shoot
	}
	
	public void ping ()
	{
		energy = energy - 5;
	}
	
	public double distance (Vector3 origin, Vector3 goal)
	{
		return Math.sqrt(Math.pow(origin.x - goal.x, 2)+Math.pow(origin.y - goal.y, 2)+Math.pow(origin.z - goal.z, 2));
	}
	
	public LinkedList<Enemy> get_enemies ()
	{
		return enemy_list;
	}
	
	public LinkedList<Resource> get_ressources ()
	{
		return resource_list;
	}
	
	public void get_environment_height ()
	{
		//don't use it now
	}
	
	public void sort_enemy_distance ()
	{
		//quicksort
		Percentage p = new Percentage(3);
	}
	
	public void revert_Enemy ()
	{
		LinkedList<Enemy> sol = new LinkedList<Enemy>();
		for (int i=0; i<enemy_list.size(); i++)
		{
			sol.add(enemy_list.removeLast());
		}
	}
	
	public void revert_Resource ()
	{
		LinkedList<Resource> sol = new LinkedList<Resource>();
		for (int i=0; i<resource_list.size(); i++)
		{
			sol.add(resource_list.removeLast());
		}
	}
	
	public void modify_list ()
	{
		
	}
	
	public double get_random_num ()
	{
		return Math.random();
	}
	
	public boolean flip_coin (double s)
	{
		return (Math.random()>s);
	}

}
