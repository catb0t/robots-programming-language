package app;

import javax.media.opengl.GL;



public class Sphere {

	GL gl;
	
	public Sphere(GL ingl)
	{
		gl = ingl;
	}
	
	void renderSphere( float cx, float cy, float cz, float r, int p)
	{
	    float PI     = 3.14159265358979f;
	    float TWOPI  = 6.28318530717958f;
	    float PIDIV2 = 1.57079632679489f;

	    float theta1 = 0.0f;
	    float theta2 = 0.0f;
	    float theta3 = 0.0f;

	    float ex = 0.0f;
	    float ey = 0.0f;
	    float ez = 0.0f;

	    float px = 0.0f;
	    float py = 0.0f;
	    float pz = 0.0f;

	    // Disallow a negative number for radius.
	    if( r < 0 )
	        r = -r;

	    // Disallow a negative number for precision.
	    if( p < 0 )
	        p = -p;

	    // If the sphere is too small, just render a OpenGL point instead.
	    if( p < 4 || r <= 0 ) 
	    {
	        gl.glBegin( GL.GL_POINTS );
	        gl.glVertex3f( cx, cy, cz );
	        gl.glEnd();
	        return;
	    }

	    for( int i = 0; i <= p/2; i++ )
	    {
	        theta1 = i * TWOPI / p - PIDIV2;
	        theta2 = (i + 1) * TWOPI / p - PIDIV2;

	        gl.glBegin( GL.GL_TRIANGLE_STRIP );
	        {
	            for( int j = p; j >= 0; --j )
	            {
	                theta3 = j * TWOPI / p;

	                ex = (float)(Math.cos(theta2) * Math.cos(theta3));
	                ey = (float)(Math.sin(theta2));
	                ez = (float)(Math.cos(theta2) * Math.sin(theta3));
	                px = cx + r * ex;
	                py = cy + r * ey;
	                pz = cz + r * ez;

	                gl.glNormal3f( -ex, -ey, -ez );
	                gl.glTexCoord2f( -(j/(float)p) , 2*(i+1)/(float)p );
	                gl.glVertex3f( px, py, pz );

	                ex = (float)(Math.cos(theta1) * Math.cos(theta3));
	                ey = (float)(Math.sin(theta1));
	                ez = (float)(Math.cos(theta1) * Math.sin(theta3));
	                px = cx + r * ex;
	                py = cy + r * ey;
	                pz = cz + r * ez;

	                gl.glNormal3f( -ex, -ey, -ez );
	                gl.glTexCoord2f( -(j/(float)p), 2*i/(float)p );
	                gl.glVertex3f( px, py, pz );
	            }
	        }
	        gl.glEnd();
	    }
	}
	
	
}


