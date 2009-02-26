package plt;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import java.io.IOException;




public class Scene implements GLEventListener {

	
	protected float pyramidRotation;
	protected float cubeRotation;
	private int terrainTexture;
	private GLU glu = new GLU();
	private Camera cam = null;
	private Terrain terrain = null;
	public double DOUBLETROUBLEINMYROOM = 69;
	
	public Robot player = null;

	float t = 0;
	
	private int terrainDL;
	private int waterDL;
	private int skyDL;
	private int robotDL;

	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		//set the camera
		cam.updatePosition(player.forwardDirection.x, player.forwardDirection.y, player.forwardDirection.z);
		cam.lookAt(0, 0, 0);
		cam.updateCamera(gl, t++);



		//render robot
		gl.glPushMatrix();
		gl.glCallList(robotDL);
		gl.glPopMatrix();
		
		//render water
		drawWater(gl);
					
	}
	
	protected void drawWater(GL gl)
	{		
		
		gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, terrainTexture);
		gl.glScalef(100.0f, 0.0f, 100.0f);
			gl.glCallList(waterDL);		
		gl.glPopMatrix();
	}
	
	protected void createWater(GL gl)
	{
		waterDL = gl.glGenLists(1);
		gl.glNewList(waterDL, GL.GL_COMPILE);
		
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1.0f,1.0f, 1.0f);
			
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f( 1.0f, 0.0f, -1.0f);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-1.0f, 0.0f, -1.0f);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f( 1.0f, 0.0f,  1.0f);
			
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f( 1.0f, 0.0f,  1.0f);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-1.0f, 0.0f, -1.0f);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f( -1.0f, 0.0f, 1.0f);
			gl.glEnd();	
	
		gl.glEndList();
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
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		cam = new Camera();
		gl.glEnable(GL.GL_TEXTURE_2D);
        terrainTexture = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, terrainTexture);
        TextureReader.Texture texture = null;
        try {
            texture = TextureReader.readTexture("media/terrain.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        
        //create the terrain, water and skySphere display list
        terrainDL = gl.glGenLists(1);
        createWater(gl);
        //create the robot display list
        createRobot(gl);


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

