package app;

import java.io.*;
import java.util.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import app.TextureReader;
import app.Robot;

public class Robot {
	
	GL gl;
	
	private int energy;
	
	float max_speed=1;
	
	private GLU glu = new GLU();
	
	public Vector3 forwardDirection = null;
	public Vector3 position = null;
	public Vector3 goal = null;
	
	LinkedList<Enemy> enemy_list;
	LinkedList<Resource> resource_list;
	
	Vector3 direction = null;
	Terrain terrain;
	OBJ_Model robot_head;
	OBJ_Model robot_torso;

	Sphere sphere = null;
	
	private int robotFace;
    private int robotTexture;
    private int laserTexture;
	
	
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
		
		robot_head = new OBJ_Model(gl, "robot/robotHead.obj");	
		robot_torso = new OBJ_Model(gl, "robot/robotTorso.obj");

		
		
		forwardDirection = new Vector3(0, 0, -1);
		position = new Vector3(0, 0, 0);
		goal = new Vector3(0, 0, 0);
		
		sphere = new Sphere(gl);
		
		
		robotFace = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, robotFace);
        TextureReader.Texture texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        	texture = TextureReader.readTexture("media/robot/robotFace.png");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

        
		
		robotTexture = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, robotTexture);
        texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        	texture = TextureReader.readTexture("media/robot/robotBody.png");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);


		laserTexture = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, laserTexture);
        texture = null;
        //TextureReader.Texture waterTexture = null;
        try {
        	texture = TextureReader.readTexture("media/robot/red.png");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

        
	}
	
	//This is for only UnitTest.
	public Robot() {
		// TODO Auto-generated constructor stub
	}
	
  private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img, 
            int target, boolean mipmapped) {
        
        if (mipmapped) {
            glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), 
                    img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        } else {
            gl.glTexImage2D(target, 0, GL.GL_RGB, img.getWidth(), 
                    img.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        }
    }
	
    private int genTexture(GL gl) {
        final int[] tmp = new int[1];
        gl.glGenTextures(1, tmp, 0);
        return tmp[0];
    }

    
    public void renderRobot(GL gl, float time)
    {
    	//need to figure out if the robot is walking right now, and in what direction
    	if( (position.x != goal.x) && (position.z != goal.z) )
    	{
    		gl.glPushMatrix();
    			//point our robot in the right direction
    			renderRobotWalking(gl, time);
    		gl.glPopMatrix();
    	}
    	else //our robot is at its goal, play idle animation
    	{
    		gl.glPushMatrix();
				//point our robot in the right direction
				renderRobotWalking(gl, time);
			gl.glPopMatrix();
    	}
    	
    	
    }
    
	private void renderRobotWalking(GL gl, float time)
	{
        gl.glBindTexture(GL.GL_TEXTURE_2D, robotFace);
		float scale = 0.5f;
//		gl.glTranslatef(0, -6, 0);
		gl.glScalef(scale, scale, scale);
		gl.glPushMatrix();
			float height = (float)(Math.cos(time)*Math.cos(time + 3.14f));
			gl.glTranslatef(0, 7 + height*0.45f, 0);
			sphere.renderSphere(0,0,0, 0.5f, 15);

		    gl.glBindTexture(GL.GL_TEXTURE_2D, robotTexture);
		       
			gl.glTranslatef(0, -1.3f - height*0.05f, 0);
			sphere.renderSphere(0,0,0, 0.8f, 15);
			
			//left arm
			gl.glPushMatrix();
				float leftArm = (float)Math.cos(time);
				gl.glTranslatef(1.1f, 0.4f, 0);
				sphere.renderSphere(0,0,0, 0.4f, 15);
				
				gl.glTranslatef(0.2f, -0.6f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(0.1f, -0.5f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(0.1f, -0.4f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.22f, 15);
				
				gl.glTranslatef(0.1f, -0.4f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.215f, 15);
			gl.glPopMatrix();
			
			//right arm
			gl.glPushMatrix();
				float rightArm = (float)Math.cos(time + 3.14f);
				gl.glTranslatef(-1.1f, 0.4f, 0);
				sphere.renderSphere(0,0,0, 0.4f, 15);
				
				gl.glTranslatef(-0.2f, -0.6f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(-0.1f, -0.5f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(-0.1f, -0.4f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.22f, 15);
				
				gl.glTranslatef(-0.1f, -0.4f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.215f, 15);
			gl.glPopMatrix();
		
		
			gl.glTranslatef(0, -1.0f, 0);
			sphere.renderSphere(0,0,0, 0.5f, 15);

			gl.glTranslatef(0, -0.9f - height*0.05f, 0);
			sphere.renderSphere(0,0,0, 0.3f, 15);
			
			//left leg
			gl.glPushMatrix();
				float leftLeg = (float)Math.cos(time + 1.57f);
				gl.glTranslatef(0.6f, -0.2f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(0.1f, -0.6f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(0.05f, -0.6f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(0.025f, -0.55f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(0.0125f, -0.45f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.27f, 15);
				
				gl.glTranslatef(0.0f, -0.45f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.31f, 15);
			gl.glPopMatrix();
			
			//right leg
			gl.glPushMatrix();
				float rightLeg = (float)Math.cos(time + 1.57f + 3.14f);
				gl.glTranslatef(-0.6f, -0.2f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(-0.1f, -0.6f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(-0.05f, -0.6f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(-0.025f, -0.55f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(-0.0125f, -0.45f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.27f, 15);
				
				gl.glTranslatef(-0.0f, -0.45f - height*0.05f, 0);
				sphere.renderSphere(0,0,0, 0.31f, 15);
			gl.glPopMatrix();
			
			
		
		gl.glPopMatrix();
		
		
	}
	
	
	
	private void renderRobotIdle(GL gl, float time)
	{
        gl.glBindTexture(GL.GL_TEXTURE_2D, robotFace);
		float scale = 0.5f;
//		gl.glTranslatef(0, -6, 0);
		gl.glScalef(scale, scale, scale);
		gl.glPushMatrix();
			float height = (float)(Math.cos(time)*Math.cos(time + 3.14f));
			gl.glTranslatef(0, 7 + height, 0);
			sphere.renderSphere(0,0,0, 0.5f, 15);

		    gl.glBindTexture(GL.GL_TEXTURE_2D, robotTexture);
		       
			gl.glTranslatef(0, -1.3f, 0);
			sphere.renderSphere(0,0,0, 0.8f, 15);
			
			//left arm
			gl.glPushMatrix();
				float leftArm = (float)Math.cos(time);
				gl.glTranslatef(1.1f, 0.4f, leftArm*0.5f);
				sphere.renderSphere(0,0,0, 0.4f, 15);
				
				gl.glTranslatef(0.2f, -0.6f, leftArm*0.4f);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(0.1f, -0.5f, leftArm*0.45f);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(0.1f, -0.4f, leftArm*0.5f);
				sphere.renderSphere(0,0,0, 0.22f, 15);
				
				gl.glTranslatef(0.1f, -0.4f, leftArm*0.6f);
				sphere.renderSphere(0,0,0, 0.215f, 15);
			gl.glPopMatrix();
			
			//right arm
			gl.glPushMatrix();
				float rightArm = (float)Math.cos(time + 3.14f);
				gl.glTranslatef(-1.1f, 0.4f, rightArm*0.5f);
				sphere.renderSphere(0,0,0, 0.4f, 15);
				
				gl.glTranslatef(-0.2f, -0.6f, rightArm*0.4f);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(-0.1f, -0.5f, rightArm*0.45f);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(-0.1f, -0.4f, rightArm*0.5f);
				sphere.renderSphere(0,0,0, 0.22f, 15);
				
				gl.glTranslatef(-0.1f, -0.4f, rightArm*0.6f);
				sphere.renderSphere(0,0,0, 0.215f, 15);
			gl.glPopMatrix();
		
		
			gl.glTranslatef(0, -1.0f, 0);
			sphere.renderSphere(0,0,0, 0.5f, 15);

			gl.glTranslatef(0, -0.9f, 0);
			sphere.renderSphere(0,0,0, 0.3f, 15);
			
			//left leg
			gl.glPushMatrix();
				float leftLeg = (float)Math.cos(time + 1.57f);
				gl.glTranslatef(0.6f, -0.2f - leftLeg*0.1f, rightArm*0.1f);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(0.1f, -0.6f - leftLeg*0.1f, rightArm*0.25f);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(0.05f, -0.6f - leftLeg*0.1f, rightArm*0.35f);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(0.025f, -0.55f - leftLeg*0.1f, rightArm*0.475f);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(0.0125f, -0.45f - leftLeg*0.1f, rightArm*0.6f);
				sphere.renderSphere(0,0,0, 0.27f, 15);
				
				gl.glTranslatef(0.0f, -0.45f - leftLeg*0.1f, rightArm*0.7f);
				sphere.renderSphere(0,0,0, 0.31f, 15);
			gl.glPopMatrix();
			
			//right leg
			gl.glPushMatrix();
				float rightLeg = (float)Math.cos(time + 1.57f + 3.14f);
				gl.glTranslatef(-0.6f, -0.2f - rightLeg*0.1f, leftArm*0.1f);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(-0.1f, -0.6f - rightLeg*0.1f, leftArm*0.25f);
				sphere.renderSphere(0,0,0, 0.3f, 15);
				
				gl.glTranslatef(-0.05f, -0.6f - rightLeg*0.1f, leftArm*0.35f);
				sphere.renderSphere(0,0,0, 0.25f, 15);
				
				gl.glTranslatef(-0.025f, -0.55f - rightLeg*0.1f, leftArm*0.475f);
				sphere.renderSphere(0,0,0, 0.225f, 15);
				
				gl.glTranslatef(-0.0125f, -0.45f - rightLeg*0.1f, leftArm*0.6f);
				sphere.renderSphere(0,0,0, 0.27f, 15);
				
				gl.glTranslatef(-0.0f, -0.45f - rightLeg*0.1f, leftArm*0.7f);
				sphere.renderSphere(0,0,0, 0.31f, 15);
			gl.glPopMatrix();
			
			
		
		gl.glPopMatrix();

		
		
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
		
		goal = new Vector3();
		
		forwardDirection = new Vector3(tempx, position.y + 5, tempy);
		
		move_to(position.add(forwardDirection), 0.0000001f);
		
		
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
	
	public float direction (Vector3 origin, Vector3 goal)
	{
		return (float)Math.atan2((double)(origin.z - goal.z), (double)(origin.y - goal.y));
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
	
	public float rollover (float a, float b)
	{
		if (b == 0)
		{
			return a;
		}
		else
		{
			return a-a/b;
		}
	}

}
