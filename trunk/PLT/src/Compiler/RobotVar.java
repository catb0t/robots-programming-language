package Robot;

public class RobotVar {
   
   private String name;
   private RobotType type;

   public RobotVar(String token, RobotType type) {
      this.type = type;
      this.name = token.substring(0, token.length() - type.postFix().length());
   }

   public String name()     { return name;            }
   public String javaType() { return type.javaType(); }

}
