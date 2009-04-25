package app;

import org.junit.* ;
import static org.junit.Assert.* ;

public class AppTests {

   @Test
   public void test_robotlist() {
      System.out.println("Testing RobotList...");
      RobotList<Location> ll = new RobotList<Location>(Location.class);

      ll.get(1).x = 10f;
      ll.get(1).y = 10f;
      ll.get(5).x = 20f;
      ll.get(5).y = 20f;

      assertTrue(ll.get(1).x == 10f);
      assertTrue(ll.get(3).x == 0f);
      assertTrue(ll.get(5).x == 20f);

   }
}
