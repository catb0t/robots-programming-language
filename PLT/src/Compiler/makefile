
./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP$parse$actions.class	:	parser.java sym.java Yylex.java parser.java
	javac -classpath "../../lib/cup-0.10k.jar" -d . parser.java sym.java Yylex.java

Yylex.java	:	robot.jflex
	java -classpath "../../lib/JFlex-1.4.1.jar" JFlex.Main robot.jflex

parser.java sym.java	:	robot.cup
	java -classpath "../../lib/cup-0.10k.jar" java_cup.Main -dump_states -dump_grammar -expect 4 robot.cup > grammar 2>&1
	
hello:
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser hello.robot hello.java

hello2:
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser hello2.robot hello2.java

	
clean:
	rm ./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP\$$parser\$$actions.class Yylex.java parser.java sym.java
