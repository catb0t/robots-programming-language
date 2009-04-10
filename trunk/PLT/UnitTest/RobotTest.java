import static org.junit.Assert.*;

import javax.media.opengl.GL;

import org.junit.Test;


public class RobotTest {
	
	app.Robot test = new app.Robot();
	
	@Test
	public void testDistance() {
		//fail("Not yet implemented"); // TODO
		//assertEquals("Result",1,1);
		assertEquals("Result",Math.sqrt(3*3+3*3+3*3),test.distance(new app.Vector3(1,2,3), new app.Vector3(4,5,6)));
	}

}
