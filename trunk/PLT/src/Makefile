
CLASSPATH = .:..:../lib/jogl.jar:../lib/gluegen-rt.jar:./Compiler/robot_parser.jar:../lib/junit-4.6.jar

default:
	javac -cp "$(CLASSPATH)" -d . app/*.java

run:
	java -cp "$(CLASSPATH)" app.Main

clean:
	rm app/*.class

unit: default
	java -cp "$(CLASSPATH)" org.junit.runner.JUnitCore app.AppTests
