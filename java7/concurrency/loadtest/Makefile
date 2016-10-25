all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

run: compile
	java LoadTest urls.txt 5
	
compile:
	javac LoadTest.java

javadoc:
	javadoc -author -version -private LoadTest.java

	