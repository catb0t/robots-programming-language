package plt;

import javax.media.opengl.GL;

public class Camera {
	
	public float x, y, z;
	public float viewX, viewY, viewZ;
	Vector3 up = new Vector3(0, 1, 0);
	float hAngle = 0;
	float vAngle = 0;
	float d = 0;
	Vector3 translation = null;
	
	public Camera()
	{
		
	}
	
	public Camera(float inX, float inY, float inZ, float lookAtX, float lookAtY, float lookAtZ) {
		updatePosition(inX, inY, inZ);
		lookAt(lookAtX, lookAtY, lookAtZ);
	}

	public void updatePosition(float xIn, float yIn, float zIn)
	{
		x = xIn;
		y = yIn;
		z = zIn;
	}
	
	public void lookAt(float lookAtX, float lookAtY, float lookatZ)
	{
		Vector3 temp = new Vector3(lookAtX - x, lookAtY - y, lookatZ - z);
		d = temp.distance();
		temp.normalize();
		translation = temp.multiply(-d);
		Vector3 newUp = temp.cross(up);
		Vector3 newSide = temp.cross(newUp);
		
		//get the vertical rotation
		Vector3 forwardDirection = new Vector3(1, 0, 0);
		Vector3 verticalDirection = new Vector3((float)Math.sqrt((temp.x*temp.x) + (temp.z*temp.z)), temp.y, 0);
		verticalDirection.normalize();
		float vertAngle = forwardDirection.dot(verticalDirection);
		vertAngle = (float)Math.acos((double)vertAngle);
		
		forwardDirection = new Vector3(0, 0, -1);
		Vector3 horizontalDirection = new Vector3(temp.x, 0, temp.z);
		horizontalDirection.normalize();
		float horizontalAngle = forwardDirection.dot(horizontalDirection);
		horizontalAngle = (float)Math.acos((double)horizontalAngle);
		if(horizontalDirection.x  > 0)
			horizontalAngle = -horizontalAngle;
		
		vAngle = vertAngle;
		hAngle = horizontalAngle;
	
	}
	
	public void updateCamera(GL gl, float t)
	{
		gl.glLoadIdentity();
	//	gl.glRotatef(t, 0.0f, 1.0f, 0.0f);
		//gl.glTranslatef(translation.x, translation.y, translation.z);
		gl.glRotatef(-(float)((180.0f/Math.PI)*vAngle), -1.0f, 0.0f, 0.0f);
		gl.glRotatef(-(float)((180.0f/Math.PI)*hAngle), 0.0f, 1.0f, 0.0f);

		gl.glTranslatef(-x, -y, -z);


	}
	
	
}
