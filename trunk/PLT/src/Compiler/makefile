
./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP$parse$actions.class	:	parser.java sym.java Yylex.java parser.java
	javac -classpath "../../lib/cup-0.10k.jar" -d . parser.java sym.java Yylex.java

Yylex.java	:	robot.lex
	java -classpath "../../lib/JFlex-1.4.1.jar" JFlex.Main robot.lex

parser.java sym.java	:	robot.cup
	java -classpath "../../lib/cup-0.10k.jar" java_cup.Main -expect 90 robot.cup
	
hello:
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser hello.robot hello.java

hello2:
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser hello2.robot hello2.java

	
clean:
	rm ./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP\$$parser\$$actions.class Yylex.java parser.java sym.java
