package Robot;

import org.junit.* ;
import static org.junit.Assert.* ;

public class RobotVarTest {

   @Test
   public void test_constructor() {
      System.out.println("Test RobotVar constructor...") ;
      RobotVar rv = new RobotVar("my_number_list#...");

      assertTrue(rv.name().equals("my_number_list"));
      assertTrue(rv.type().equals(RobotType.NUMBER));
      assertTrue(rv.isList() == true);

   }

}

