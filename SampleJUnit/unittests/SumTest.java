import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Joseph
 *
 */
public class SumTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link Sum#add(int, int)}.
	 */
	@Test
	public void testAdd() {
		Sum tester = new Sum();
		assertEquals("Result", 15, tester.add(10, 5));
	}

}
