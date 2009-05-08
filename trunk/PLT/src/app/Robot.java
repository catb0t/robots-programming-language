package app;

import java.io.*;
import java.util.*;
import java.lang.Math;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import app.TextureReader;
import app.Robot;

public class Robot implements RobotInterface {
	
	GL gl;
	
	float cameraDistance = 30;
	
	private int energy;
	
	float max_speed=1f;
	float speed = 0.2f;
	float maxIncline = 1f;
	int ammo = 0;
	
	float verticalVelocity = 0;
	float robotDirection = 0;
	
	private GLU glu = new GLU();
	
	public Vector3 forwardDirection = null;
	public Vector3 cameraDirection = null;
	public Vector3 position = null;
	public Vector3 goal = null;
	
	RobotList<Enemy> enemy_list;
	RobotList<Resource> resource_list;
	
	Vector3 direction = null;
	Terrain terrain;

	Sphere sphere = null;
	
	private int robotFace;
    private int robotTexture;
    private int laserTexture;
	
    float oldTime = 0;
    
    public FuncSet funcset = new FuncSet();
    

	
	public Robot(Terrain t)
	{
		forwardDirection = new Vector3(0, 0, -1);
		cameraDirection = new Vector3(0, 10, -10);
		position = new Vector3(0f,0f,0f);
		goal = new Vector3(0, 0, 0);
		terrain = t;
	}
	
	public Robot(GL g, Terrain t)
	{
		gl = g;
		forwardDirection = new Vector3(0, 0, -10);

		terrain = t;

		
		
		forwardDirection = new Vector3(0, 0, -1);
		cameraDirection = new Vector3(0, 10, -1);
		position = new Vector3(0f,0f,0f);
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
	
	
	public void init(Terrain t) {
		forwardDirection = new Vector3(0, 0, -1);
		cameraDirection = new Vector3(0, 10, -10);
		position = new Vector3(0f,0f,0f);
		goal = new Vector3(0, 0, 0);
		terrain = t;
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

    public void update(float time)
    {	
  //  	System.out.println(time);
    	//update the robots position
    	forwardDirection = new Vector3(goal.x - position.x, 0, goal.z - position.z);
    	forwardDirection.normalize();
    	float timeInterval = time - oldTime;
    	oldTime = time;
    	float distance = timeInterval*speed;
    	float realDistance = (float)Math.sqrt(Math.pow(position.x - goal.x, 2)+ Math.pow(position.z - goal.z, 2));
    	if(realDistance < distance)
    		distance = realDistance;
    	//figure out what direction we're moving to
    	float directionRadians = this.direction(position, goal);
    	Vector3 newPosition = new Vector3((float)(position.x + distance*forwardDirection.x), (float)(position.z + distance*forwardDirection.z), (float)0);
    	//now that I have a new position, need to find out my distance from the ground
    	float newY = terrain.terrainIntersection(newPosition);
    	float nextY = terrain.terrainIntersection(new Vector3(newPosition.x + forwardDirection.x*0.001f, newPosition.z + forwardDirection.z*0.001f, 0));
    	float incline = (nextY - newY)/0.001f;
 //   	System.out.println(newY);
    	//if we've hit an incline higher than we can traverse then keep old position
    	//...
    	//else
    	if(goal.x != position.x || goal.z != position.z) //if we haven't moved then don't do anything
    	{

    	//	System.out.println(incline);
	    	if( incline < maxIncline)
	    	{
	    		//update the position along the terrain
	    		position.x = newPosition.x;
	    		position.z = newPosition.y;
	    		//now figure out what's going on with falling and physics
	    // 		if(newY < position.y)//we're falling
	    //		{
	    //			verticalVelocity += 
	    //		}
	    		
	    		
	    	}
    	}
    	position.y = newY;
 //   	goal.x = position.x + 10;
   // 	goal.z = position.z + 10;
    	
    	//update the camera
    	float tempx = position.x + cameraDistance*(float)Math.cos((double)time/100.0);
		float tempy = position.z + cameraDistance*(float)Math.sin((double)time/100.0);
		
		
		//figure out the camera direction... camera should probably look at the robot but be pointed in the direction of the closest object of interest
		cameraDirection = new Vector3(tempx, position.y + cameraDistance/2.0f, tempy);
    }
    
    public void renderRobot(GL gl, float time, boolean walking, float direction)
    {
		
		//move_to(position.add(forwardDirection), 0.0000001f);
    	
    	gl.glRotatef(direction, 0.0f, 1.0f, 0.0f);
    	
    	//need to figure out if the robot is walking right now, and in what direction
    	if(walking)
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
				renderRobotIdle(gl, time);
			gl.glPopMatrix();
    	}
    	
    	
    }
    
	private void renderRobotIdle(GL gl, float time)
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
	
	
	
	private void renderRobotWalking(GL gl, float time)
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
	
	//public Vector2 getPositionVector2(){
	//	return new Vector2(position.x,position.z);
	//}
	
	//******************************************************************************************************
	// THINK!!!!
	//******************************************************************************************************
	
	public void think()
	{		
		//goal = new Vector3(0, 0, 0);
		
		
		//move_to(position.add(forwardDirection), 0.0000001f);
		
		System.out.println("empty process");
		String l = Global.outputArea.getText();
		Global.outputArea.setText(l.concat("\n Robot says: ").concat("hello"));
		
		
	}
	//******************************************************************************************************
	
	
	public void say (String s)
	{
		//System.out.println(s);
		String l = Global.outputArea.getText();
		Global.outputArea.setText(l.concat("\n").concat(s));
	}
	
	public void move_to (Location location)
	{
		
		goal = location.getVector3();
		
		speed = 1;
	}
	
	public void move_to (Location location, Percentage my_speed)
	{
		
		goal = location.getVector3();
		
		speed = my_speed.percent/100f;
	}
	
	public void move_to (Location location, float my_speed)
	{
		move_to (location, new Percentage(my_speed));
	}
	
	public void shoot (Enemy en)
	{
		//decrease energy
		//shoot
	}
	
	public void shoot (Location loc)
	{
		//decrease energy
		//shoot
	}
	
	public void ping ()
	{
		energy = energy - 5;
		
		//updateEnememies
		enemy_list = new RobotList<Enemy>(Enemy.class);
		for (Enemy e: Global.enemy_list)
		{
			enemy_list.addLast(e);
		}
		
		//update Ressources
		resource_list = new RobotList<Resource>(Resource.class);
		for (Resource r: Global.resource_list)
		{
			resource_list.addLast(r);
		}
	}
	
	public float distance (Location origin, Location goal)
	{
		return (float) Math.sqrt(Math.pow(origin.x - goal.x, 2)+ Math.pow(origin.y - goal.y, 2));
	}
	
	public float direction (Vector3 origin, Vector3 goal)
	{
		
		forwardDirection = new Vector3(goal.x - origin.x, 0, goal.z - origin.z);
		forwardDirection.normalize();
		return (float) Math.atan2((double)(origin.z - goal.z), (double)(origin.y - goal.y));
	}
	
	public Percentage getEnergy()
	{
		return new Percentage(this.energy);
	}
	
	
	public RobotList<Enemy> get_enemies ()
	{
		return enemy_list;
	}
	
	public RobotList<Resource> get_ressources ()
	{
		return resource_list;
	}
	
	public void get_environment_height ()
	{
		//don't use it now
	}
	
	
	public RobotList<Float> sort(RobotList<Float> list)
	{
		int length = list.size();
		Index_value[] distri = new Index_value[length];
		for (int i=0; i<length; i++)
		{
			distri[i] = new Index_value(i, list.get(i));
		}
		
		boolean permut;

	    do
	    {
	    	permut=false;
	    	for(int i=0;i<length-1;i++)
	    	{
	    		if(distri[i].value>distri[i+1].value)
	    		{
	    			Index_value a = distri[i];
	    			distri[i] = distri[i+1];
	    			distri[i+1] = a;
	    			permut=true;
	    		}
	    	}
	    }
	    while(permut);
	    
	    RobotList<Float> sol = new RobotList<Float>(Float.class); 
	    for (int i=0; i<length; i++)
	    {
	    	sol.addLast(new Float(distri[i].value));
	    }
	    return sol;
	}
	
	
	public RobotList<Enemy> sort_enemy_distance ()
	{
		//bubble sort
		int length = enemy_list.size();
		Index_value[] enemy_dist = new Index_value[length];
		Location cur_loc = this.getLocation();
		for (int i=0; i<length; i++)
		{
			enemy_dist[i] = new Index_value(i,distance(cur_loc, enemy_list.get(i).location));
		}

		
	    boolean permut;

	    do
	    {
	    	permut=false;
	    	for(int i=0;i<length-1;i++)
	    	{
	    		if(enemy_dist[i].value>enemy_dist[i+1].value)
	    		{
	    			Index_value a = enemy_dist[i];
	    			enemy_dist[i] = enemy_dist[i+1];
	    			enemy_dist[i+1] = a;
	    			permut=true;
	    		}
	    	}
	    }
	    while(permut);
	    
	    RobotList<Enemy> new_enemy_list = new RobotList<Enemy>(Enemy.class); 
	    for (int i=0; i<length; i++)
	    {
	    	new_enemy_list.addLast(enemy_list.get(enemy_dist[i].index));
	    }
	    enemy_list = new_enemy_list;
	    
	    return enemy_list;
	}
	
	
	public RobotList<Resource> sort_resource_distance ()
	{
		//bubble sort
		int length = resource_list.size();
		Index_value[] resource_dist = new Index_value[length];
		Location cur_loc = this.getLocation();
		for (int i=0; i<length; i++)
		{
			resource_dist[i] = new Index_value(i,distance(cur_loc, resource_list.get(i).location));
		}

		
	    boolean permut;

	    do
	    {
	    	permut=false;
	    	for(int i=0;i<length-1;i++)
	    	{
	    		if(resource_dist[i].value>resource_dist[i+1].value)
	    		{
	    			Index_value a = resource_dist[i];
	    			resource_dist[i] = resource_dist[i+1];
	    			resource_dist[i+1] = a;
	    			permut=true;
	    		}
	    	}
	    }
	    while(permut);
	    
	    RobotList<Resource> new_resource_list = new RobotList<Resource>(Resource.class); 
	    for (int i=0; i<length; i++)
	    {
	    	new_resource_list.addLast(resource_list.get(resource_dist[i].index));
	    }
	    resource_list = new_resource_list;
	    
	    return resource_list;
	}
	
	
	public void revert_Enemy ()
	{
		RobotList<Enemy> sol = new RobotList<Enemy>(Enemy.class);
		for (int i=0; i<enemy_list.size(); i++)
		{
			sol.addLast(enemy_list.removeLast());
		}
		enemy_list = sol;
	}
	
	public void revert_Resource ()
	{
		RobotList<Resource> sol = new RobotList<Resource>(Resource.class);
		for (int i=0; i<resource_list.size(); i++)
		{
			sol.addLast(resource_list.removeLast());
		}
		resource_list = sol;
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
	
	public void setPosition(float x, float y, float z)
	{
		position = new Vector3(x, y, z);
	}
	
	public Location getLocation() {
		return new Location(position.x, position.z);
	}
	
}
