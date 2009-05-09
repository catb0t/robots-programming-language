
################
# DEPENDENCIES #
################

JARS   := cup-0.10k.jar JFlex-1.4.1.jar junit-4.6.jar
LIBDIR := ../../lib/

#############
# CLASSPATH #
#############

CLASSPATH := $(addprefix $(LIBDIR), $(JARS))

# This replaces spaces with colons (:)
EMPTY     :=
SPACE     := $(EMPTY) $(EMPTY)
CLASSPATH := $(subst $(SPACE),:,$(CLASSPATH))

#################
# JAVA SETTINGS #
#################

COMPILE       := javac
COMPILE_FLAGS := -classpath ".:$(CLASSPATH)" -Xlint:unchecked -d .

RUN           := java
RUN_FLAGS     := -classpath ".:$(CLASSPATH)" 

#####################
# LANGUAGE SETTINGS #
#####################

LEXER          := robot.jflex
GRAMMAR        := robot.cup 
PACKAGE_NAME   := app
JAR_NAME       := robot_parser.jar

PARSER_FILES   := parser sym
LEX_FILES      := Yylex

PARSE_SETTINGS := -dump_states -dump_grammar -expect 70

HELPER_FILES   := RobotVar RobotType RobotVarScope RobotScopeManager Expression

######################
# GENERATED SETTINGS #
######################

PACKAGE_DIR        := ./$(PACKAGE_NAME)/

PARSE              := $(RUN) $(RUN_FLAGS) $(PACKAGE_NAME).parser 
PARSER             := $(PACKAGE_DIR)parser.class

PARSER_SRC         := $(addsuffix .java, $(PARSER_FILES))
LEX_SRC            := $(addsuffix .java, $(LEX_FILES))

PARSER_LEX_FILES   := $(PARSER_FILES) $(LEX_FILES)
PARSER_LEX_CLASSES := $(addprefix $(PACKAGE_DIR), $(addsuffix .class, $(PARSER_LEX_FILES)))

HELPER_SRC         := $(addsuffix .java, $(HELPER_FILES))
HELPER_CLASSES     := $(addprefix $(PACKAGE_DIR), $(addsuffix .class, $(HELPER_FILES)))

###################################
# TARGETS FOR BUILDING THE PARSER #
###################################

$(PARSER_LEX_CLASSES): $(PARSER_SRC) $(LEX_SRC) $(HELPER_CLASSES)
	$(COMPILE) $(COMPILE_FLAGS) $(PARSER_SRC) $(LEX_SRC)

$(LEX_SRC): $(LEXER) 
	$(RUN) $(RUN_FLAGS) JFlex.Main $(LEXER) 

$(PARSER_SRC): $(GRAMMAR)
	$(RUN) $(RUN_FLAGS) java_cup.Main $(PARSE_SETTINGS) $(GRAMMAR) > grammar.txt 2>&1

$(HELPER_CLASSES): $(HELPER_SRC)
	$(COMPILE) $(COMPILE_FLAGS) $(HELPER_SRC)

#############################
# TARGETS FOR TEST PROGRAMS #
#############################

TEST_PROGRAMS := hello hello2 tutorial2 FindShoot PatrolRobot
UNIT_TESTS := RobotVarTest

$(TEST_PROGRAMS): $(PARSER)
	$(PARSE) $@.robot $@.java &> $@.parse && cat $@.java

test: $(TEST_PROGRAMS)

$(UNIT_TESTS): $(addsuffix .java, $(UNIT_TESTS))
	$(COMPILE) $(COMPILE_FLAGS) $<

unit: $(UNIT_TESTS)
	$(RUN) $(RUN_FLAGS) org.junit.runner.JUnitCore $(PACKAGE_NAME).$<

jar: $(PARSER)
	jar cvfm $(JAR_NAME) MANIFEST.MF $(PACKAGE_NAME) java_cup/runtime/*.class

# java -jar robot_parser.jar tutorial2.robot tutorial2.java
TEST_JAR := $(addprefix jar_, $(TEST_PROGRAMS))

$(TEST_JAR): $(TEST_PROGRAMS)
	java -jar $(JAR_NAME) $<.robot $<.java

#############
# UTILITIES #
#############

clean:
	rm $(addprefix ./, $(addsuffix .java, $(TEST_PROGRAMS))) \
           $(addprefix ./, $(addsuffix .parse, $(TEST_PROGRAMS))) \
           $(PARSER_LEX_CLASSES) \
           $(HELPER_CLASSES) \
           $(PARSER_SRC) \
           $(LEX_SRC) \
           grammar.txt

# If having trouble with make macros, you can print them using this target
debug_make:
	@echo PARSER_LEX_CLASSES $(PARSER_LEX_CLASSES)

