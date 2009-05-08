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


public class Laser {

	Vector3 location;
	Vector3 direction;
	OBJ_Model laser = null;
    private int laserTexture;
	private GLU glu = new GLU();
	float strength = 1;
	float speed = 1;
	float power = 1;
	float oldTime;
	
	public Laser(Vector3 pos, Vector3 dir, float time)
	{
		location = pos;
		direction = dir;
		oldTime = time;
		
	}
	
	public Laser(GL gl)
	{
		
		
		laserTexture = genTexture(gl);
        gl.glBindTexture(GL.GL_TEXTURE_2D, laserTexture);
        TextureReader.Texture texture = null;
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

        laser = new OBJ_Model(gl, "robot/laser.obj");
	}
	
	
    public void renderLaser(GL gl, Vector3 position)
    {
    	gl.glPushMatrix();
    	
    		gl.glRotatef(1, 0.0f, 1.0f, 0.0f);
    		gl.glTranslatef(0, 0, 0);
			laser.render(gl);
		gl.glPopMatrix();
    }
    
    
    
    public void update(float time)
    {
    	float timeLapse = time-oldTime;
    	oldTime = time;
    	location.x = location.x + direction.x*timeLapse*speed;
    	location.y = location.y + direction.y*timeLapse*speed;
    	location.z = location.z + direction.z*timeLapse*speed;
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
