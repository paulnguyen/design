all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile:
	javac *.java
	
run-semaphore: compile
	java Tester semaphore

run-java-semaphore: compile
	java Tester java-semaphore

run-java-unfair-semaphore: compile
	java Tester java-unfair-semaphore

run-bounded-buffer: compile
	java Tester bounded-buffer
