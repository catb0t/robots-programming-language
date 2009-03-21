
./Robot/parser.class ./Robot/sym.class ./Robot/Yylex.class ./Robot/CUP$parse$actions.class	:	parser.java sym.java Yylex.java parser.java
	javac -d . parser.java sym.java Yylex.java

Yylex.java	:	robot.lex
	jflex robot.lex

parser.java sym.java	:	robot.cup
	java java_cup.Main -expect 2 robot.cup
