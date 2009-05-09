package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class Vector3Tes {

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
    
    @Test
    public void testDot(){
            app.Vector3 tester = new app.Vector3(1,2,3);
        app.Vector3 result = new app.Vector3(4,5,6);
        assertEquals("Result", tester.x*result.x+tester.y*result.y+tester.z*result.z, result.dot(tester));
    }
    
    @Test
    public void testCross(){
            app.Vector3 tester = new app.Vector3(1,2,3);
        app.Vector3 result = new app.Vector3(4,5,6);
        app.Vector3 result1= new app.Vector3();
        result1=result.cross(tester);
        
            assertEquals("Result", result.y*tester.z-tester.y*result.z, result1.x);
            assertEquals("Result", result.z*tester.x-tester.z*result.x, result1.y);
            assertEquals("Result", result.x*tester.y-tester.x*result.y, result1.z);
    }

}
