import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Vector3Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() {
		app.Vector3 tester = new app.Vector3(1,2,3);
		app.Vector3 result = new app.Vector3();
		result=tester.add(new app.Vector3(3,4,5));
		assertEquals("Result", 4, result.x);
		assertEquals("Result", 6, result.y);
		assertEquals("Result", 8, result.z);
	}
	
	@Test
	public void testMultiply(){
		app.Vector3 tester = new app.Vector3(1,2,3);
		app.Vector3 result = new app.Vector3();
		result=tester.multiply((float)3.0);
		assertEquals("Result", 3, result.x);
		assertEquals("Result", 6, result.y);
		assertEquals("Result", 9, result.z);
	}
	
	@Test
	public void testDistance(){
		app.Vector3 tester = new app.Vector3(1,2,3);
		assertEquals("Result", Math.sqrt(1*1+2*2+3*3), tester.distance());
	}
}
