package app;

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

   @Test
   public void test_equality() {
      System.out.println("Test RobotVar equality...") ;
      RobotVar rv1 = new RobotVar("my_number#");
      RobotVar rv2 = new RobotVar("my_number#");

      assertTrue(rv1.equals(rv2));

   }

   @Test
   public void test_equality2() {
      System.out.println("Test RobotVarScope equality...") ;
      
      RobotVar rv1 = new RobotVar("my_number#");
      RobotVar rv2 = new RobotVar("my_number#");

      RobotVarScope rvs1 = new RobotVarScope(rv1,(short)1);
      RobotVarScope rvs2 = new RobotVarScope(rv2,(short)2);

      assertTrue(rvs1.equals(rvs2));

   }

   @Test
   public void test_inequality() {
      System.out.println("Test RobotVar inequality...") ;
      RobotVar rv1 = new RobotVar("my_number#");
      RobotVar rv2 = new RobotVar("my_number2#");

      assertTrue(!rv1.equals(rv2));

   }

   @Test
   public void test_inequality2() {
      System.out.println("Test RobotVarScope inequality...") ;
      
      RobotVar rv1 = new RobotVar("my_number#");
      RobotVar rv2 = new RobotVar("my_number2#");

      RobotVarScope rvs1 = new RobotVarScope(rv1,(short)1);
      RobotVarScope rvs2 = new RobotVarScope(rv2,(short)2);

      assertTrue(!rvs1.equals(rvs2));

   }



}

