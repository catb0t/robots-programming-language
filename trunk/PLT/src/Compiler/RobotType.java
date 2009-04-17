package Robot;

public enum RobotType {

   NUMBER     ("#"  , "double" ),
   PERCENTAGE ("%"  , "Percent"),
   BOOLEAN    ("?"  , "bool"   ),
   LOCATION   ("@"  , "Location"),
   ENEMY      ("!"  , "Enemy"   ),
   RESOURCE   ("$"  , "Resource"),
   LIST       ("...", "List"    );

   private final String postFix; 
   private final String javaType;

   RobotType(String postFix, String javaType) {
      this.postFix = postFix;
      this.javaType = javaType;
   }

   public String postFix()  { return postFix;  }
   public String javaType() { return javaType; }
}