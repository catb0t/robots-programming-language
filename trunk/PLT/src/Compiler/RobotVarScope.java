package app;

public class RobotVarScope {
   
   private RobotVar v;
   private short scope;

   public RobotVarScope(RobotVar v, short scope) {
      this.v = v;
      this.scope = scope;
   }

   public RobotVar variable() { return v;     }
   public short    scope()    { return scope; }
   public String   toString() { return v + " (" + scope + ")"; } 
   
   public int hashCode() {
      return (v != null ? v.hashCode() : 0);
   }

   public boolean equals(Object o) {
      
      if (this == o) return true;
     
      if (o == null || getClass() != o.getClass()) return false;

      RobotVarScope equalTest = (RobotVarScope) o;

      if (v != null ? !v.equals(equalTest.variable()) : equalTest.variable() != null) return false;

      return true;
   }

}
