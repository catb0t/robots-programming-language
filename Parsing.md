# Introduction #

Please post links and information relevant to parsing here.

# Links #

  * [CUP manual](http://www2.cs.tum.edu/projects/cup/manual.html)
  * [Directions to use Jlex, CUP and JUnit](http://cs.ua.edu/434/spring2005/SetupInstructions.pdf)
  * [Using Jlex](http://www.cs.princeton.edu/~appel/modern/java/JLex/current/manual.html)
  * [Installing CUP](http://www2.cs.tum.edu/projects/cup/)
  * [Compiling JLex and Cup code](http://www.cs.cornell.edu/Courses/cs412/2001sp/resources/skeleton.html)
  * [JFlex user manual](http://jflex.de/manual.html)
  * [Installing/Using the CUP/JFlex Plug-In for Eclipse](http://www.rose-hulman.edu/class/csse/csse404/Handouts/CUPLEX.html)

# Compiling the compiler #

Code location: svn/trunk/PLT/src/Compiler/

Make sure that JFlex.jar and cup.jar are in the class path (EDIT: these jars are now in svn/trunk/PLT/lib.  You don't need to do anything if you're using the makefile to compile/run the compiler).

These instructions are specifically for Linux but they do not differ too much form what you would do on other OSs. Let me know if you face issues compiling and running the code. Currently, the compiler reads a file called hello.robot from the current directory and sends the synthesized Java code to stdout.

## New instructions (using makefile) ##

1. $ make

2. $ make hello

## Old instructions ##

1. $ java java\_cup.Main robot.cup

2. $ jflex robot.lex

OR

java -cp JFLex.jar JFLex.Main robot.lex

3. $ javac -d . parser.java sym.java Yylex.java

4. $ java Robot.parser


Alternatively, you may also use the makefile to perform the first three steps.