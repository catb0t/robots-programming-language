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
    
	private int powerup1;
    private int powerup2;
    private int powerup3;
    private int powerup4;
	
    
    
    public Powerup(Vector3 position, int Type)
	{
    	location = position;
    	type = Type;
    	
	}
	
    
	public Powerup(GL gl)
	{

		 		
		powerup1 = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, powerup1);
        TextureReader.Texture texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        		texture = TextureReader.readTexture("media/Powerups/powerup1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        
        
        
        powerup2 = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, powerup2);
        texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        		texture = TextureReader.readTexture("media/Powerups/powerup2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

        
        
        powerup3 = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, powerup3);
        texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        		texture = TextureReader.readTexture("media/Powerups/powerup3.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        
        
        
        powerup4 = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, powerup4);
        texture = null;
		gl.glEnable(GL.GL_TEXTURE_2D);

        //TextureReader.Texture waterTexture = null;
        try {
        		texture = TextureReader.readTexture("media/Powerups/powerup4.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        makeRGBTexture(gl, glu, texture, GL.GL_TEXTURE_2D, false);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		
	}

	
	
	public void renderPowerup(GL gl, Vector3 pos, int t, boolean active)
	{
		//if nobody has gotten the power up yet then render it
		if(active == true)
		{
			
			if(type == 0)
				gl.glBindTexture(GL.GL_TEXTURE_2D, powerup1);
        	if(type == 1)
        		gl.glBindTexture(GL.GL_TEXTURE_2D, powerup2);
        	if(type == 2)
        		gl.glBindTexture(GL.GL_TEXTURE_2D, powerup3);
        	if(type == 3)
        		gl.glBindTexture(GL.GL_TEXTURE_2D, powerup4);
			
			gl.glPushMatrix();
				gl.glTranslatef(pos.x, pos.y, pos.z);
				sphere = new Sphere(gl);
				sphere.renderSphere(0, 0, 0, 0.4f, 15);
			gl.glPopMatrix();
		}
		
	}
	
	
	
	
	
	
	
	
	  private void makeRGBTexture(GL gl, GLU glu, TextureReader.Texture img, int target, boolean mipmapped) {
	        
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
	    
	  
	    public Resource projectOnResourceClass()
	    {
	    	if (type==0)
	    	{
	    		return new Resource("", new Location(location.x, location.z), 100f, 0f, 0f, 0f);
	    	}
	    	else if (type==1)
	    	{
	    		return new Resource("", new Location(location.x, location.z), 0f, 0f, 100f, 0f);
	    	}
	    	else if (type==2)
	    	{
	    		return new Resource("", new Location(location.x, location.z), 0f, 100f, 0f, 0f);
	    	}
	    	else //if (type==3)
	    	{
	    		return new Resource("", new Location(location.x, location.z), 0f, 0f, 0f, 100f);
	    	}
	    }

}
