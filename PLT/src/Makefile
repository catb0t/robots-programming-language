
.PHONY: compiler

CLASSPATH = .:..:../lib/jogl.jar:../lib/gluegen-rt.jar:./Compiler/robot_parser.jar:../lib/junit-4.6.jar

default:
	javac -cp "$(CLASSPATH)" -Xlint:unchecked -d . app/*.java

run:
	java -cp "$(CLASSPATH)" app.Main

clean:
	rm app/*.class

compiler:
	cd Compiler; make jar; cd ..; cp Compiler/robot_parser.jar .

unit: default
	java -cp "$(CLASSPATH)" org.junit.runner.JUnitCore app.AppTests
