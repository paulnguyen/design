all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile:
	javac *.java
	
run-bad-lock: compile
	java Tester bad-lock

run-good-lock: compile
	java Tester good-lock

run-reentrant-lock: compile
	java Tester reentrant-lock
	