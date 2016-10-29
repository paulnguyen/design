all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile: 
	javac *.java

run-dinning-deadlock: compile
	java DiningPhilosophers1

run-dinning-global-order: compile
	java DiningPhilosophers2

run-dinning-lock-timeout: compile
	java DiningPhilosophers3

run-dinning-condition-variables: compile
	java DiningPhilosophers4
	