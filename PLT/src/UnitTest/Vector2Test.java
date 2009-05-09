package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class Vector2Test {

    @Test
    public void testAdd() {
            app.Vector2 tester = new app.Vector2(1,2);
            app.Vector2 result = new app.Vector2();
            result=tester.add(new app.Vector2(3,4));
            assertEquals("Result", 4, result.x);
            assertEquals("Result", 6, result.y);
    }
    
    @Test
    public void testMultiply(){
            app.Vector2 tester = new app.Vector2(1,2);
            app.Vector2 result = new app.Vector2();
            result=tester.multiply((float)3.0);
            assertEquals("Result", 3, result.x);
            assertEquals("Result", 6, result.y);
    }
    
    @Test
    public void testDistance(){
            app.Vector2 tester = new app.Vector2(1,2);
            assertEquals("Result", Math.sqrt(1*1+2*2), tester.distance());
    }
    
    @Test
    public void testDot(){
            app.Vector2 tester = new app.Vector2(1,2);
        app.Vector2 result = new app.Vector2(4,5);
        assertEquals("Result", tester.x*result.x+tester.y*result.y, result.dot(tester));
    }

}
