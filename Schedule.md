# Major Schedule #
  * Ten-minute project presentations in class. : **Apr 27 and 29**
  * Final project report & Demo: **May 11-12**

# Task List #
  * Software
    * Application
      * GUI : load file + save file + run : Aurelien for Apr 10
      * standard ROBOT library
      * new classes : Enemy, Resources, Percentage and their methods : Aurelien for Apr 10
      * standard output simulation
      * include automatic class loader
      * 3D viewer : last thing to do
    * Compiler
      * Simple version (Hello robot...) : Apr 10
      * map from ROBOT classes to Java classes
      * translation from token to java code
      * Intermediate representation design
    * Integration Test and Debugging : Apr 28 ~ May 5
    * Release Final version : **May 6**
  * Documentation
    * Presentation :
      * Initialize : Apr 15
      * Review : Apr 20
      * Release : **Apr 22**
    * Final Project Report :
      * Initialize : Apr 28
      * Review : May 4
      * Release : **May 6**

# Thoughts #
  * First, test without automatic class loader and without 3D viewer.
  * Use standard output.
  * IR design
  * overload each operator by a function. ex : "+" -> "Func.add(param1,param2)" to avoid type checking
  * keep track of the line in Lex program : "Func.add(param1,param2,line number)" to give a good error feedback to the user

# Weekly Meeting #
  * **Monday and Wednesday 5:30 ~ 6:30** @ CS Lounge (after class every week)
  * **Friday 4:30 ~ 5:30** @ CS Lounge