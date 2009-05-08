package app;


import java.util.*;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import app.Camera;
import app.OBJ_Model;
import app.Robot;
import app.Terrain;
import app.TextureReader;

import java.io.IOException;
import java.util.Random;



public class Scene implements GLEventListener {

	
	protected float pyramidRotation;
	protected float cubeRotation;

	private GLU glu = new GLU();
	private Camera cam = null;
	public Terrain terrain = null;
	public double DOUBLETROUBLEINMYROOM = 69;

	public Robot[] player = null;
	public int numberOfRobots = 1;
	public Robot playerAvatar = null;
	
    //ArrayList<Laser> lasers = new ArrayList<Laser>();
	LinkedList<Laser> lasers = new LinkedList<Laser>();
	
	Laser laserAvatar = new Laser();
	
    private int waterTexture;
	
	
	private int waterDL;
	
	//light
    private float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] lightDiffuse = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] lightPosition = {-5.0f, -5.0f, -5.0f, 0.0f};
    
    //fog
    private int fogMode[] = {GL.GL_EXP, GL.GL_EXP2, GL.GL_LINEAR};	// Storage For Three Types Of Fog ( new )
    private int fogfilter = 2;								// Which Fog Mode To Use      ( new )
    private float fogColor[] = {0.5f, 0.5f, 0.5f, 1.0f};		// Fog Color               ( new )
        
    int numberOfPowerups = 30;
    public Powerup[] resources = null;//new Powerup[numberOfPowerups];
    public Powerup resourceAvatar = null;

    int t = 0;
    long oldTime = 0;
    

	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();

		if(System.currentTimeMillis() > oldTime)
		{
			t++;
			oldTime = System.currentTimeMillis();
		}
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		//set the camera
		cam.updatePosition(player[0].cameraDirection.x, player[0].cameraDirection.y, player[0].cameraDirection.z);
		cam.lookAt(player[0].position.x, player[0].position.y, player[0].position.z);
		cam.updateCamera(gl, t++);
		lightPosition[0] = 0.5f;//lightDirection.x;
		lightPosition[1] = -0.5f;//lightDirection.y;
		lightPosition[2] = 0.5f;//lightDirection.z;
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, this.lightPosition, 0);

		
		//render water
		//
		//drawWater(gl);
		
		//draw the terrain
		terrain.renderHeightMap(gl);
		drawWater(gl);
		
		//**************************
		//render power-ups
		//**************************
        for(int i = 0; i < numberOfPowerups; i++)
        {
        	resourceAvatar.renderPowerup(gl, resources[i].location, resources[i].type, resources[i].active);
        }   
        
        //===========================
        //render lasers
        //===========================
        for(int i = 0; i < lasers.size(); i++)
        {
        	laserAvatar.renderLaser(gl, lasers.get(i).location  );
        	//lasers.get(i).renderLaser(gl,lasers.get(i).location);
        }

		//---------------------------
		//render robot
        //---------------------------
		for(int i = 0; i < numberOfRobots; i++)
		{
			//need to figure out if the robot is walking right now, and in what direction
	    	boolean walking = false;
			if( Math.abs(player[i].position.x - player[i].goal.x) > 0.1 || Math.abs(player[i].position.z - player[i].goal.z) > 0.1 )
			{
				//figure out walking direction
				player[i].robotDirection = player[i].direction(player[i].position, player[i].goal);
				player[i].robotDirection = -player[i].robotDirection*(180.0f/3.14f ) - 90;
				walking = true;
			}
			
			gl.glPushMatrix();
				float[] lightAmbient = {1.0f, 1.0f, 1.0f, 1.0f};
				gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightAmbient, 0);
			
				gl.glTranslatef(player[i].position.x, player[i].position.y, player[i].position.z);
				//gl.glCallList(robotDL);
			//		model.render(gl);
	
				playerAvatar.renderRobot(gl, (float)t/100.0f, walking, player[i].robotDirection );
				
				gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, this.lightAmbient, 0);
			gl.glPopMatrix();
		} //end robot render			
	}
	
	protected void drawWater(GL gl)
	{		
		
		gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, waterTexture);
        gl.glTranslatef(0, 5, 0);
		gl.glScalef(1000.0f, 0.0f, 1000.0f);
			gl.glCallList(waterDL);		
		gl.glPopMatrix();
	}
	
	protected void createWater(GL gl)
	{
		waterDL = gl.glGenLists(1);
		gl.glNewList(waterDL, GL.GL_COMPILE);
		
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1.0f,1.0f, 1.0f);
			
			gl.glTexCoord2f(100.0f, 0.0f);
			gl.glVertex3f( 1.0f, 0.0f, -1.0f);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-1.0f, 0.0f, -1.0f);
			gl.glTexCoord2f(100.0f, 100.0f);
			gl.glVertex3f( 1.0f, 0.0f,  1.0f);
			
			gl.glTexCoord2f(100.0f, 100.0f);
			gl.glVertex3f( 1.0f, 0.0f,  1.0f);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-1.0f, 0.0f, -1.0f);
			gl.glTexCoord2f(0.0f, 100.0f);
			gl.glVertex3f( -1.0f, 0.0f, 1.0f);
			gl.glEnd();	
	
		gl.glEndList();
	}
	

	

	//@Override
	public void displayChanged(GLAutoDrawable drawable, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	//@Override
	public void init(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glColorMaterial ( GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE ) ;
		
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, this.lightAmbient, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, this.lightDiffuse, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, this.lightPosition, 0);
        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glFogi(GL.GL_FOG_MODE, fogMode[fogfilter]);			// Fog Mode
        gl.glFogfv(GL.GL_FOG_COLOR, fogColor, 0);					// Set Fog Color
        gl.glFogf(GL.GL_FOG_DENSITY, 0.05f);						// How Dense Will The Fog Be
        gl.glHint(GL.GL_FOG_HINT, GL.GL_DONT_CARE);					// Fog Hint Value
        gl.glFogf(GL.GL_FOG_START, 25.0f);							// Fog Start Depth
        gl.glFogf(GL.GL_FOG_END, 50.0f);							// Fog End Depth
        gl.glEnable(GL.GL_FOG);									// Enables GL.GL_FOG
        

        gl.glEnable(GL.GL_CULL_FACE);
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		cam = new Camera();
		
		waterTexture = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, waterTexture);
        TextureReader.Texture texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        	texture = TextureReader.readTexture("media/water_deep.png");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        
       
        //create the terrain, water and skySphere display list
        terrain = new Terrain(gl);
        createWater(gl);


        laserAvatar = new Laser(gl);
        playerAvatar = new Robot(gl, terrain);
        player = new Robot[numberOfRobots];//(terrain);
        for(int i = 0; i < numberOfRobots; i++)
        	player[i] = new Robot(terrain);
        Random generator = new Random();
        resourceAvatar = new Powerup(gl);
        resources = new Powerup[numberOfPowerups];
        for(int i = 0; i < numberOfPowerups; i++)
        {
        	//find location
        //	float x = generator.nextFloat()*(terrain.maxx - terrain.minx) + terrain.minx;
        //	float z = generator.nextFloat()*(terrain.maxz - terrain.minz) + terrain.minz;
        //	Vector3 pos = new Vector3(x, z, 0);
        //	float y = terrain.terrainIntersection(pos);
        	Vector3 pos = new Vector3(0, 0, 0);
        	resources[i] = new Powerup(pos, generator.nextInt(4));
        }


	}

	
	//@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL gl = drawable.getGL();
		final GLU glu = new GLU();
		
		gl.setSwapInterval(1);

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(
				45.0f, 
				(double) width / (double) height, 
				0.1f,
				1000.0f);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		


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

}

