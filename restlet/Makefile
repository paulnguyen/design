all: clean compile 

clean:
	rm build/*

compile: 
	javac -cp lib/org.restlet.jar -d build src/*.java
	
run-hello:
	echo Starting Service at:  http://localhost:8080/restlet/hello
	java -cp build:lib/org.restlet.jar HelloWorldServer

run-client:
	java -cp build:lib/org.restlet.jar HelloClient
