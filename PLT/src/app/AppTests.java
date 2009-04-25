package app;

import org.junit.* ;
import static org.junit.Assert.* ;

public class AppTests {

   @Test
   public void test_robotlist() {
      System.out.println("Testing RobotList...");
      RobotList<Location> ll = new RobotList<Location>(Location.class);

      ll.get(1f).x = 10f;
      ll.get(1f).y = 10f;
      ll.get(5f).x = 20f;
      ll.get(5f).y = 20f;

      Float f = new Float(1f);

      assertTrue(ll.get(1f).x == 10f);
      assertTrue(ll.get(f).x == 10f);
      assertTrue(ll.get(3f).x == 0f);
      assertTrue(ll.get(5f).x == 20f);
      assertTrue(ll.get(0f) == null);

   }

   @Test
   public void test_location_equality() {
      
      System.out.println("Testing Location.equals()...");
      
      Location l1 = new Location();
      Location l2 = new Location(0f, 0f);

      assertTrue(l1.equals(l2));

      l2.x = 1f;

      assertTrue(!l1.equals(l2));

   }

   @Test
   public void test_rollover() {
      
      System.out.println("Testing Func.rollover()...");
      
      float f1;
      
      f1 = Func.rollover(1f, 0f); assertTrue(f1 == 1f);
      f1 = Func.rollover(0f, 4f); assertTrue(f1 == 0f);
      f1 = Func.rollover(1f, 4f); assertTrue(f1 == 1f);
      f1 = Func.rollover(2f, 4f); assertTrue(f1 == 2f);
      f1 = Func.rollover(3f, 4f); assertTrue(f1 == 3f);
      f1 = Func.rollover(4f, 4f); assertTrue(f1 == 0f);
      f1 = Func.rollover(5f, 4f); assertTrue(f1 == 1f);

   }

}
