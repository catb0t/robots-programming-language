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

   public Expression(StringBuffer code) {
      
      this();

      javaCode.append(code);

   }

   public Expression(Expression e) {
      
      this();

      javaCode.append(e.javaCode);
      declarations.append(e.declarations);

   }

}
