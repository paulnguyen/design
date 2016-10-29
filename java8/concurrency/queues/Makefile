all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile: 
	javac *.java

run-concurrent-linked-queue: compile
	java ConcurrentLinkedQueueExample

run-linked-blocking-queue: compile
	java LinkedBlockingQueueExample
	