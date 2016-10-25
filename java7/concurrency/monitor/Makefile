all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile:
	javac *.java
	
run-lock: compile
	java Tester lock

run-reentrant-lock: compile
	java Tester reentrant-lock

run-bounded-buffer: compile
	java Tester bounded-buffer

run-bank-monitor: compile
	java Tester bank-monitor

run-bank-java-monitor: compile
	java Tester bank-java-monitor
	