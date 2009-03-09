import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Vector3Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() {
		plt.Vector3 tester = new plt.Vector3(1,2,3);
		plt.Vector3 result = new plt.Vector3();
		result=tester.add(new plt.Vector3(3,4,5));
		assertEquals("Result", 4, result.x);
		assertEquals("Result", 6, result.y);
		assertEquals("Result", 8, result.z);
	}
}
