
./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP$parse$actions.class	:	parser.java sym.java Yylex.java parser.java
	javac -classpath "../../lib/cup-0.10k.jar" -Xlint:unchecked -d . parser.java sym.java Yylex.java

Yylex.java: robot.jflex
	java -classpath "../../lib/JFlex-1.4.1.jar" JFlex.Main robot.jflex

parser.java sym.java: robot.cup
	java -classpath "../../lib/cup-0.10k.jar" java_cup.Main -dump_states -dump_grammar -expect 10 robot.cup > grammar 2>&1
	
hello: ./Robot/parser.class
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser hello.robot hello.java

hello2: ./Robot/parser.class
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser hello2.robot hello2.java

tutorial2: ./Robot/parser.class
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser tutorial2.robot tutorial2.java
	
FindShoot: ./Robot/parser.class
	java -classpath ".:../../lib/cup-0.10k.jar" Robot.parser FindShoot.r FindShoot.java && cat FindShoot.java

clean:
	rm ./hello.java ./hello2.java ./tutorial2.java ./FindShoot.java ./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP\$$parser\$$actions.class Yylex.java parser.java sym.java
