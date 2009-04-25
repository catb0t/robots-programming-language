package app;

class Expression {

   public StringBuffer declarations;
   public StringBuffer javaCode;
   public RobotType type;

   public Expression() {
      
      super();

      declarations = new StringBuffer(); 
      javaCode = new StringBuffer();
      type = RobotType.NOTHING;

   }

   public Expression(String code) {
      
      this();

      javaCode.append(code);

   }

}
