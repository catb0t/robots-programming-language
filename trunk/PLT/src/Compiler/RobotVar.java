package app;

public class RobotVar {
   
   private String name;
   private RobotType type;
   private boolean isList;
   private String token;

   public RobotVar(String token) {
      String name;
      RobotType type = RobotType.NOTHING;
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

      if (type.equals(RobotType.NOTHING)) {
         System.err.println("Couldn't determine type of var token" + token);
      }

      name = token.substring(0, name_len);

      this.type = type;
      this.name = name;
      this.isList = isList;
      this.token = token;
   }

   public String    name()     { return name;    }
   public RobotType type()     { return type;    }
   public boolean   isList()   { return isList;  }
   public String    toString() { return token;   }

   public String javaType() {
      String javaType = type.javaType();
      
      if (isList) {
         javaType = RobotType.LIST.javaType() + "<" + javaType + ">";
      }

      return javaType;
   }
   
   public int hashCode() {
      return (name != null ? name.hashCode() : 0);
   }

   public boolean equals(Object o) {
      
      if (this == o) return true;
     
      if (o == null || getClass() != o.getClass()) return false;

      RobotVar equalTest = (RobotVar) o;

      if (name != null ? !name.equals(equalTest.name()) : equalTest.name() != null) return false;

      return true;
   }

   
}
