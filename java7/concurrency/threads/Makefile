all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

run:
	java LoadTest urls.txt 5
	
compile:
	javac LoadTest.java

javadoc:
	javadoc -author -version -private LoadTest.java

	