package Robot;

public class RobotVar {
   
   private String name;
   private RobotType type;
   private boolean isList;

   public RobotVar(String token) {
      String name;
      RobotType type = RobotType.UNKNOWN;
      boolean isList;
      int name_len;

      isList = token.endsWith(RobotType.LIST.postFix());
      name_len = token.length();
      
      if (isList) {
         name_len -= RobotType.LIST.postFix().length();
      }

      for (RobotType t : RobotType.values()) {
         if (token.substring(0, name_len).endsWith(t.postFix())) {
            type = t;
            name_len -= t.postFix().length();
            break;
         }
      }

      if (type.equals(RobotType.UNKNOWN)) {
         System.err.println("Couldn't determine type of var token" + token);
      }

      name = token.substring(0, name_len);

      init(name, type, isList);   
   }

   public RobotVar(String name, RobotType type, boolean isList) {
         init(name, type, isList);
   }

   private void init(String name, RobotType type, boolean isList) {
      this.type = type;
      this.name = name;
      this.isList = isList;
   }

   public String    name()    { return name;    }
   public RobotType type()    { return type;    }
   public boolean   isList()  { return isList;  }

}
