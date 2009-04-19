package app;

public enum RobotType {

   NUMBER     ("#"  , "float"  ),
   PERCENTAGE ("%"  , "Percent" ),
   BOOLEAN    ("?"  , "boolean" ),
   LOCATION   ("@"  , "Location"),
   ENEMY      ("!"  , "Enemy"   ),
   RESOURCE   ("$"  , "Resource"),
   LIST       ("...", "LinkList"    ),
   UNKNOWN    ("~unknown~","void");

   private final String postFix; 
   private final String javaType;

   RobotType(String postFix, String javaType) {
      this.postFix = postFix;
      this.javaType = javaType;
   }

   public String postFix()  { return postFix;  }
   public String javaType() { return javaType; }
}
