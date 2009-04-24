package app;

import java.io.*;
import java.util.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import app.TextureReader;
import app.Robot;


public class Powerup {
	
	//type = 0 is for energy, 1 speed, 2 is weapons, 3 is health and armor
	int type;
	Vector3 location;
	private GLU glu = new GLU();
    private int Texture;
    //once a powerup has been taken it becomes inactive and is no longer rendered
    boolean active = true;
    Sphere sphere = null;
	
	
	public Powerup(Vector3 position, int Type)
	{
		location = position;
		type = Type;
		
	}
	
	public void renderPowerup(GL gl)
	{
		//if nobody has gotten the power up yet then render it
		if(active == true)
		{
			gl.glPushMatrix();
				gl.glTranslatef(location.x, location.y, location.z);
				sphere = new Sphere(gl);
				sphere.renderSphere(0, 0, 0, 1, 15);
			gl.glPopMatrix();
		}
		
	}

}
