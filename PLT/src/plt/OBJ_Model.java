package plt;

import java.io.*;
import java.util.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


public class OBJ_Model {
	
	
    private int modelDL;
    GL gl;
    
    
	public OBJ_Model(GL g, String filename)
	{
		gl = g;
		loadModel(filename);
		
	}
	
    public void render(GL gl) {              // This Renders The Height Map As Quads

    	gl.glPushMatrix();
//        gl.glBindTexture(GL.GL_TEXTURE_2D, terrainTexture);
		//gl.glScalef(100.0f, 0.0f, 100.0f);
        float scale = 0.1f;
    	gl.glScalef(scale, scale, scale);
	//	gl.glTranslatef(-MAP_SIZE/2, 0, -MAP_SIZE/2);
        	gl.glCallList(modelDL);		
		gl.glPopMatrix();
    }
    
	public void loadModel(String filename)
	{ 
		filename = "media/" + filename;
		int vertCounter = 0;
		int texCounter = 0;
		int normCounter = 0;
		int faceCounter = 0;
		//first need to parse the file to discover the number of vertices, normals and tex_coords
		try{
		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    while ((strLine = br.readLine() ) != "end")   
	    {
	        // Print the content on the console
	        System.out.println (strLine);
	        StringTokenizer st = new StringTokenizer(strLine);
	        //while (st.hasMoreTokens()) {
	        //    System.out.println(st.nextToken());
	        String token = st.nextToken();
        	if(token.compareTo("v") == 0)
        		vertCounter++;
        	if(token.compareTo("vt") == 0)
        		texCounter++;
        	if(token.compareTo("vn") == 0)
        		normCounter++;
        	if(token.compareTo("f") == 0)
        		faceCounter++;
	        	
	    }
	    //Close the input stream
	    in.close();
		}catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }

		System.out.println("verts " + vertCounter);
		System.out.println("texs " + texCounter);
		System.out.println("norms " + normCounter);
		
		Vector3[] vertices = new Vector3[vertCounter];
		Vector3[] texCoords = new Vector3[texCounter];
		Vector3[] norms = new Vector3[normCounter];
		int vertCounter2 = 0;
		int texCounter2 = 0;
		int normCounter2 = 0;
		
		//parse file a second time to gather the model data
    	modelDL = gl.glGenLists(1);
		gl.glNewList(modelDL, GL.GL_COMPILE);
	    	
	        gl.glBegin(GL.GL_TRIANGLES);
	        
			try{
				FileInputStream fstream = new FileInputStream(filename);
				DataInputStream in = new DataInputStream(fstream);
			    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    while ((strLine = br.readLine() ) != null)   
			    {
			        // Print the content on the console
			        System.out.println (strLine);
			        StringTokenizer st = new StringTokenizer(strLine);
			        //while (st.hasMoreTokens()) {
			        //    System.out.println(st.nextToken());
			        String token = st.nextToken();
		        	if(token.compareTo("v") == 0)
		        	{
		        		vertices[vertCounter2] = new Vector3( Float.valueOf(st.nextToken()), Float.valueOf(st.nextToken()), Float.valueOf(st.nextToken()) );
		        		vertCounter2++;
		        	}
		        	if(token.compareTo("vt") == 0)
		        	{
		        		texCoords[texCounter2] = new Vector3( Float.valueOf(st.nextToken()), Float.valueOf(st.nextToken()), Float.valueOf(st.nextToken()) );
		        		texCounter2++;
		        	}
		        	if(token.compareTo("vn") == 0)
		        	{
		        		norms[normCounter2] = new Vector3( Float.valueOf(st.nextToken()), Float.valueOf(st.nextToken()), Float.valueOf(st.nextToken()) );
		        		normCounter2++;
		        	}
		        	//we have 3 new vertices to create
		        	if(token.compareTo("f") == 0)
		        	{
		        		//first vertex
		        		String firstVertex = st.nextToken();
		        		StringTokenizer vertToken = new StringTokenizer(firstVertex, "/");
		        		int vertID = Integer.valueOf(vertToken.nextToken())-1;
		        		int texID = Integer.valueOf(vertToken.nextToken())-1;
		        		int normID = Integer.valueOf(vertToken.nextToken())-1;
		        		gl.glTexCoord2f(texCoords[texID].x, texCoords[texID].y);
				        gl.glNormal3f(norms[normID].x, norms[normID].y, norms[normID].z);
				        gl.glVertex3f(vertices[vertID].x, vertices[vertID].y, vertices[vertID].z);
				           
				        //second vertex
				        firstVertex = st.nextToken();
		        		vertToken = new StringTokenizer(firstVertex, "/");
		        		vertID = Integer.valueOf(vertToken.nextToken())-1;
		        		texID = Integer.valueOf(vertToken.nextToken())-1;
		        		normID = Integer.valueOf(vertToken.nextToken())-1;
		        		gl.glTexCoord2f(texCoords[texID].x, texCoords[texID].y);
				        gl.glNormal3f(norms[normID].x, norms[normID].y, norms[normID].z);
	             		gl.glVertex3f(vertices[vertID].x, vertices[vertID].y, vertices[vertID].z);
	             		
				        //third vertex
				        firstVertex = st.nextToken();
		        		vertToken = new StringTokenizer(firstVertex, "/");
		        		vertID = Integer.valueOf(vertToken.nextToken())-1;
		        		texID = Integer.valueOf(vertToken.nextToken())-1;
		        		normID = Integer.valueOf(vertToken.nextToken())-1;
		        		gl.glTexCoord2f(texCoords[texID].x, texCoords[texID].y);
				        gl.glNormal3f(norms[normID].x, norms[normID].y, norms[normID].z);
	             		gl.glVertex3f(vertices[vertID].x, vertices[vertID].y, vertices[vertID].z);
		        		
		        	}
			        	
			    }
			    //Close the input stream
			    in.close();
				}catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
				
        	gl.glEnd();
            gl.glEndList();
		
		
		
	}
	
}
