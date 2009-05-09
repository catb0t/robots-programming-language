package app;

public enum RobotType {

   NUMBER     ("#"  , "Float"     ),
   PERCENTAGE ("%"  , "Percentage"),
   BOOLEAN    ("?"  , "Boolean"   ),
   LOCATION   ("@"  , "Location"  ),
   ENEMY      ("!"  , "Enemy"     ),
   RESOURCE   ("$"  , "Resource"  ),
   LIST       ("...", "RobotList"),
   NOTHING    ("NULL", "void"     );

   private final String postFix; 
   private final String javaType;

   RobotType(String postFix, String javaType) {
      this.postFix = postFix;
      this.javaType = javaType;
   }

   public String postFix()  { return postFix;  }
   public String javaType() { return javaType; }
}
