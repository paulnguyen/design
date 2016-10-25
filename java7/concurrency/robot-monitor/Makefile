all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile:
	javac *.java
	
run-robot-test-1: compile
	java Tester robot-test-1

run-robot-test-2: compile
	java Tester robot-test-2
 