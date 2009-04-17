package app;



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



public class Scene implements GLEventListener {

	
	protected float pyramidRotation;
	protected float cubeRotation;

	private GLU glu = new GLU();
	private Camera cam = null;
	public Terrain terrain = null;
	public double DOUBLETROUBLEINMYROOM = 69;

	public Robot player = null;
	public Robot playerAvatar = null;
	OBJ_Model model = null;
	
    private int waterTexture;
	
	float t = 0;
	
	private int waterDL;
	private int skyDL;
	private int robotDL;
	
	//light
    private float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] lightDiffuse = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] lightPosition = {-5.0f, -5.0f, -5.0f, 0.0f};
    
    //fog
    private int fogMode[] = {GL.GL_EXP, GL.GL_EXP2, GL.GL_LINEAR};	// Storage For Three Types Of Fog ( new )
    private int fogfilter = 2;								// Which Fog Mode To Use      ( new )
    private float fogColor[] = {0.5f, 0.5f, 0.5f, 1.0f};		// Fog Color               ( new )
        

    

	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();

		
		int time = (int)System.currentTimeMillis();
		
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		//set the camera
		cam.updatePosition(player.forwardDirection.x, player.forwardDirection.y, player.forwardDirection.z);
		cam.lookAt(player.position.x, player.position.y, player.position.z);
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
		Sphere sphere = new Sphere(gl);

		
		//render robot
		physics();
		gl.glPushMatrix();
			float[] lightAmbient = {1.0f, 1.0f, 1.0f, 1.0f};
			gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightAmbient, 0);
		
			gl.glTranslatef(player.position.x, player.position.y, player.position.z);
			//gl.glCallList(robotDL);
		//		model.render(gl);

			playerAvatar.renderRobot(gl, (float)(time/1000.0f) );
			
			gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, this.lightAmbient, 0);
		gl.glPopMatrix();
					
	}
	
	protected void drawWater(GL gl)
	{		
		
		gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, waterTexture);
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
	
	protected void physics()
	{
		player.setY((int)terrain.terrainIntersection(player.getPosition()) );
		
	}
	
	protected void createRobot(GL gl){
		
		robotDL = gl.glGenLists(1);
        gl.glNewList(robotDL, GL.GL_COMPILE);
     
			gl.glBegin(GL.GL_TRIANGLES);					
			gl.glColor3f(1.0f,0.0f,0.0f);			
			gl.glVertex3f( 0.0f, 1.0f, 0.0f);			
			gl.glColor3f(0.0f,1.0f,0.0f);			
			gl.glVertex3f(-1.0f,-1.0f, 1.0f);			
			gl.glColor3f(0.0f,0.0f,1.0f);			
			gl.glVertex3f( 1.0f,-1.0f, 1.0f);			
			gl.glColor3f(1.0f,0.0f,0.0f);			
			gl.glVertex3f( 0.0f, 1.0f, 0.0f);			
			gl.glColor3f(0.0f,0.0f,1.0f);			
			gl.glVertex3f( 1.0f,-1.0f, 1.0f);			
			gl.glColor3f(0.0f,1.0f,0.0f);			
			gl.glVertex3f( 1.0f,-1.0f, -1.0f);			
			gl.glColor3f(1.0f,0.0f,0.0f);			
			gl.glVertex3f( 0.0f, 1.0f, 0.0f);			
			gl.glColor3f(0.0f,1.0f,0.0f);			
			gl.glVertex3f( 1.0f,-1.0f, -1.0f);			
			gl.glColor3f(0.0f,0.0f,1.0f);			
			gl.glVertex3f(-1.0f,-1.0f, -1.0f);			
			gl.glColor3f(1.0f,0.0f,0.0f);			
			gl.glVertex3f( 0.0f, 1.0f, 0.0f);			
			gl.glColor3f(0.0f,0.0f,1.0f);			
			gl.glVertex3f(-1.0f,-1.0f,-1.0f);			
			gl.glColor3f(0.0f,1.0f,0.0f);			
			gl.glVertex3f(-1.0f,-1.0f, 1.0f);			
		    gl.glEnd();

	    gl.glEndList();

	}
	
	protected void drawCube(GL gl){
		gl.glBegin(GL.GL_QUADS);					
			gl.glColor3f(0.0f,1.0f,0.0f);			
			gl.glVertex3f( 1.0f, 1.0f,-1.0f);			
			gl.glVertex3f(-1.0f, 1.0f,-1.0f);			
			gl.glVertex3f(-1.0f, 1.0f, 1.0f);			
			gl.glVertex3f( 1.0f, 1.0f, 1.0f);			
			gl.glColor3f(1.0f,0.5f,0.0f);			
			gl.glVertex3f( 1.0f,-1.0f, 1.0f);			
			gl.glVertex3f(-1.0f,-1.0f, 1.0f);			
			gl.glVertex3f(-1.0f,-1.0f,-1.0f);			
			gl.glVertex3f( 1.0f,-1.0f,-1.0f);			
			gl.glColor3f(1.0f,0.0f,0.0f);			
			gl.glVertex3f( 1.0f, 1.0f, 1.0f);			
			gl.glVertex3f(-1.0f, 1.0f, 1.0f);			
			gl.glVertex3f(-1.0f,-1.0f, 1.0f);			
			gl.glVertex3f( 1.0f,-1.0f, 1.0f);			
			gl.glColor3f(1.0f,1.0f,0.0f);			
			gl.glVertex3f( 1.0f,-1.0f,-1.0f);			
			gl.glVertex3f(-1.0f,-1.0f,-1.0f);			
			gl.glVertex3f(-1.0f, 1.0f,-1.0f);			
			gl.glVertex3f( 1.0f, 1.0f,-1.0f);			
			gl.glColor3f(0.0f,0.0f,1.0f);			
			gl.glVertex3f(-1.0f, 1.0f, 1.0f);			
			gl.glVertex3f(-1.0f, 1.0f,-1.0f);			
			gl.glVertex3f(-1.0f,-1.0f,-1.0f);			
			gl.glVertex3f(-1.0f,-1.0f, 1.0f);			
			gl.glColor3f(1.0f,0.0f,1.0f);			
			gl.glVertex3f( 1.0f, 1.0f,-1.0f);			
			gl.glVertex3f( 1.0f, 1.0f, 1.0f);			
			gl.glVertex3f( 1.0f,-1.0f, 1.0f);			
			gl.glVertex3f( 1.0f,-1.0f,-1.0f);			
		gl.glEnd();	
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
        //create the robot display list
        createRobot(gl);

        model = new OBJ_Model(gl, "sphere.obj");
        
        playerAvatar = new Robot(gl, terrain);
        player = new Robot(terrain);
        


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

