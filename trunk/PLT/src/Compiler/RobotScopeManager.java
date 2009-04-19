package app;

import java.util.Stack;

public class RobotScopeManager {

   private static short level = 1;
   private static Stack<RobotVarScope> vars = new Stack<RobotVarScope>();
   
   public static String declareIfNecessary(RobotVar v) {

      RobotVarScope rvs = new RobotVarScope(v, level);
      
      int indexFromEnd = vars.search(rvs);

      boolean isDeclared = (indexFromEnd != -1);
      
      System.out.println("RSM: " + vars);
      System.out.println("RSM: " + indexFromEnd);

      if (isDeclared) {
         int element = vars.size() - indexFromEnd;
         System.out.println("RSM: " + v.name() + " is ALREADY defined in level " + vars.elementAt(element).scope() + " (element: "+element+")!");
         return new String();
      } else {
         System.out.println("RSM: " + v.name() + " is NOT defined.");
         System.out.println("RSM: Declaring " + v.name() + " now...");
         vars.push(new RobotVarScope(v, level));
         System.out.println("RSM: " + vars);
         return v.javaType() + " ";
      }
 
   }

   public static void  increaseLevel() {
      System.out.println("RSM: Increasing level...");
      level++;
      System.out.println("RSM: level=" + level);

   }
   
   public static void  decreaseLevel() {
      System.out.println("RSM: Decreasing level...");
      while (!vars.empty() && vars.peek().scope() == level) {
         RobotVarScope delete = vars.pop();
         System.out.println("RSM: " + vars);
      }

      level--;
      System.out.println("RSM: level=" + level);

   }
   
   public static short level() { return level; }

   public static String indent() {

      String indent = new String();

      for(short i=1; i <= level; i++) {
         indent += "\t";
      }

      return indent;
   }

}
