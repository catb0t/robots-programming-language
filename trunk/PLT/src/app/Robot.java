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
	
	public Vector3 forwardDirection = null;
	public Vector3 position = null;
	
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
	
	/* the phsyics function handles all the physics dealing with the robot, for example, when 
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
	
	
	public void think()
	{
		t++;
		
		//physics();
		
		float tempx = 10*(float)Math.cos((double)t/10000000.0);
		float tempy = 10*(float)Math.sin((double)t/10000000.0);
		
		forwardDirection = new Vector3(tempx, position.y + 5, tempy);
		
		
	}
	
	
	
	
	
	

}
