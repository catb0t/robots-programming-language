package plt;

public class Sphere {

	
	
	void renderSphere( float cx, float cy, float cz, float r, int p )
	{
	    const float PI     = 3.14159265358979f;
	    const float TWOPI  = 6.28318530717958f;
	    const float PIDIV2 = 1.57079632679489f;

	    float theta1 = 0.0;
	    float theta2 = 0.0;
	    float theta3 = 0.0;

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
	        glBegin( GL_POINTS );
	        glVertex3f( cx, cy, cz );
	        glEnd();
	        return;
	    }

	    for( int i = 0; i < p/2; ++i )
	    {
	        theta1 = i * TWOPI / p - PIDIV2;
	        theta2 = (i + 1) * TWOPI / p - PIDIV2;

	        glBegin( GL_TRIANGLE_STRIP );
	        {
	            for( int j = 0; j <= p; ++j )
	            {
	                theta3 = j * TWOPI / p;

	                ex = cosf(theta2) * cosf(theta3);
	                ey = sinf(theta2);
	                ez = cosf(theta2) * sinf(theta3);
	                px = cx + r * ex;
	                py = cy + r * ey;
	                pz = cz + r * ez;

	                glNormal3f( ex, ey, ez );
	                glTexCoord2f( -(j/(float)p) , 2*(i+1)/(float)p );
	                glVertex3f( px, py, pz );

	                ex = cosf(theta1) * cosf(theta3);
	                ey = sinf(theta1);
	                ez = cosf(theta1) * sinf(theta3);
	                px = cx + r * ex;
	                py = cy + r * ey;
	                pz = cz + r * ez;

	                glNormal3f( ex, ey, ez );
	                glTexCoord2f( -(j/(float)p), 2*i/(float)p );
	                glVertex3f( px, py, pz );
	            }
	        }
	        glEnd();
	    }
	}
	
	
}


