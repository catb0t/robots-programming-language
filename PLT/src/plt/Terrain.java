package plt;

import java.io.*;			//added by soonhac @ 02/20/09

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Terrain {

    private static final int MAP_SIZE = 1024;                        // Size Of Our .RAW Height Map (NEW)
    private static final int STEP_SIZE = 16;                        // Width And Height Of Each Quad (NEW)
    private byte[] heightMap = new byte[MAP_SIZE * MAP_SIZE]; // Holds The Height Map Data (NEW)
    private float scaleValue = .15f;                        // Scale Value For The Terrain (NEW)
    private boolean zoomIn;
    private boolean zoomOut;
    float scale = -0.1f;
    float textureScale = 10;
	private GLU glu = new GLU();
	private int terrainTexture;
    
    private int terrainDL;
    GL gl;
	
	public Terrain(GL inGL)
	{
		gl = inGL;
		terrainDL = gl.glGenLists(1);
		
		
        try {
            loadRawFile("media/terrain.raw", heightMap);  // (NEW)
        } catch (IOException e) {
        	System.out.println("terrain map did not load...");
            throw new RuntimeException(e);
        }
        
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
		
        createHeightMap();
	}
	
    private void loadRawFile(String strName, byte[] pHeightMap) throws IOException {
        InputStream input = ResourceRetriever.getResourceAsStream(strName);
        readBuffer(input, pHeightMap);
        input.close();

        for (int i = 0; i < pHeightMap.length; i++)
            pHeightMap[i] &= 0xFF;                 //Quick fix
    }
    
    private static void readBuffer(InputStream in, byte[] buffer) throws IOException {
	    int bytesRead = 0;
	    int bytesToRead = buffer.length;
	    while (bytesToRead > 0) {
	        int read = in.read(buffer, bytesRead, bytesToRead);
	        bytesRead += read;
	        bytesToRead -= read;
	    }
	}
    

    public void renderHeightMap(GL gl) {              // This Renders The Height Map As Quads

    	gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, terrainTexture);
		//gl.glScalef(100.0f, 0.0f, 100.0f);
		gl.glTranslatef(-MAP_SIZE/2, 0, -MAP_SIZE/2);
        	gl.glCallList(terrainDL);		
		gl.glPopMatrix();
    }
    
    private void createHeightMap()
    {
    	terrainDL = gl.glGenLists(1);
		gl.glNewList(terrainDL, GL.GL_COMPILE);
	    	
	        gl.glBegin(GL.GL_QUADS);
	        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	
	        for (int X = 0; X < (MAP_SIZE - STEP_SIZE); X += STEP_SIZE)
	            for (int Y = 0; Y < (MAP_SIZE - STEP_SIZE); Y += STEP_SIZE) {
	                // Get The (X, Y, Z) Value For The Bottom Left Vertex
	                int x = X;
	                int y = (int)(height(heightMap, X, Y)*scale);
	                int z = Y;
	
	                // Set The Color Value Of The Current Vertex
	               // setVertexColor(gl, heightMap, x, z);
	            	gl.glTexCoord2f(X/textureScale, Y/textureScale);
	                gl.glVertex3i(x, y, z);                          // Send This Vertex To OpenGL To Be Rendered (Integer Points Are Faster)
	
	                // Get The (X, Y, Z) Value For The Top Left Vertex
	                x = X;
	                y = (int)(height(heightMap, X, Y + STEP_SIZE)*scale);
	                z = Y + STEP_SIZE;
	
	                // Set The Color Value Of The Current Vertex
	                //setVertexColor(gl, heightMap, x, z);
	                gl.glTexCoord2f(X/textureScale, (Y + STEP_SIZE)/textureScale);
	                gl.glVertex3i(x, y, z);	                         // Send This Vertex To OpenGL To Be Rendered
	
	                // Get The (X, Y, Z) Value For The Top Right Vertex
	                x = X + STEP_SIZE;
	                y = (int)(height(heightMap, X + STEP_SIZE, Y + STEP_SIZE)*scale);
	                z = Y + STEP_SIZE;
	
	                // Set The Color Value Of The Current Vertex
	                //setVertexColor(gl, heightMap, x, z);
	                gl.glTexCoord2f((X + STEP_SIZE)/textureScale, (Y + STEP_SIZE)/textureScale);
	                gl.glVertex3i(x, y, z);                          // Send This Vertex To OpenGL To Be Rendered
	
	                // Get The (X, Y, Z) Value For The Bottom Right Vertex
	                x = X + STEP_SIZE;
	                y = (int)(height(heightMap, X + STEP_SIZE, Y)*scale);
	                z = Y;
	
	                // Set The Color Value Of The Current Vertex
	                //setVertexColor(gl, heightMap, x, z);
	                gl.glTexCoord2f((X + STEP_SIZE)/textureScale, Y/textureScale);
	                gl.glVertex3i(x, y, z);                          // Send This Vertex To OpenGL To Be Rendered
	            }
        	gl.glEnd();
        gl.glEndList();
    }
    
    private float terrainIntersection(Vector3 position)
    {
    	Vector3 down = new Vector3(0, -1, 0);
    	//find the triangle which the position is currently over, need to translate and scale the position so it corresponds to the height map
    	position.x += MAP_SIZE/2;
    	position.z += MAP_SIZE/2;
    	position.x *= scale;
    	position.y *= scale;
    	
    	Vector3 position2 = position;
    	
    	float interpX = position2.x%STEP_SIZE;
    	float interpY = position2.y%STEP_SIZE;
    	interpX /= STEP_SIZE;
    	interpY /= STEP_SIZE;
    	
    	position2.x /= STEP_SIZE;
    	position2.z /= STEP_SIZE;
    	position2.x = (float)Math.floor(position2.x);
    	position2.y = (float)Math.floor(position2.y);
    	position2.x *= STEP_SIZE;
    	position2.y *= STEP_SIZE;
    	
    	
    	//get the 4 heights
    	int h1 = (int)(height(heightMap, (int)position2.x, (int)position2.y)*scale);
    	int h2 = (int)(height(heightMap, (int)position2.x + STEP_SIZE, (int)position2.y)*scale);
    	int h3 = (int)(height(heightMap, (int)position2.x, (int)position2.y + STEP_SIZE)*scale);
    	int h4 = (int)(height(heightMap, (int)position2.x + STEP_SIZE, (int)position2.y + STEP_SIZE)*scale);
    	
    	float newHeightX = h1*(1-interpX) + h2*(interpX);
    	float newHeightY = h3*(1-interpX) + h4*(interpX);
    	float newHeight = newHeightX*(1-interpY) + newHeightY*(interpY);
    	   	 	
    	return newHeight;
    }
    
    private void setVertexColor(GL gl, byte[] pHeightMap, int x, int y) {                 // Sets The Color Value For A Particular Index, Depending On The Height Index
        float fColor = -0.15f + (height(pHeightMap, x, y) / 256.0f);
        // Assign This Blue Shade To The Current Vertex
        gl.glColor3f(0, 0, fColor);
    }

    private int height(byte[] pHeightMap, int X, int Y) {                         // This Returns The Height From A Height Map Index

        int x = X % MAP_SIZE;                                               // Error Check Our x Value
        int y = Y % MAP_SIZE;                                               // Error Check Our y Value
        return pHeightMap[x + (y * MAP_SIZE)] & 0xFF;                        // Index Into Our Height Array And Return The Height
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
	    
} //end class


