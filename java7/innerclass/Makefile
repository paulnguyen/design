all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

compile:
	javac *.java

decomp1:
	javap -private Outer

decomp2:
	javap -private Outer$1

run:
	java -cp . Main
	
	
