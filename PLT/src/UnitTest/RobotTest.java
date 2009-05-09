package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class RobotTest {

	@Test
    public void testDistance() {
		app.Robot test = new app.Robot();
        //fail("Not yet implemented"); // TODO
        //assertEquals("Result",1,1);
        assertEquals("Result",Math.sqrt(3*3+3*3),test.distance(new app.Location(1,2), new app.Location(4,5)));
    }


}
