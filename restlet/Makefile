all: clean compile 

clean:
	rm build/*
	rm dist/app.jar

jar: compile
	cd build ; jar -cvfe ../dist/app.jar HelloWorldServer *.class

test: build
	java -cp dist/restlet.jar:dist/app.jar HelloWorldServer

compile: 
	javac -cp lib/org.restlet.jar -d build src/*.java

docker-build:
	docker build -t restapi .

docker-images:
	docker images

docker-run:
	docker run -td restapi
	
run-hello:
	echo Starting Service at:  http://localhost:8080/restlet/hello
	echo Cloud9 Endpoint: https://design-paulnguyen.c9users.io/restlet/hello
	java -cp build:lib/org.restlet.jar HelloWorldServer

run-client:
	java -cp build:lib/org.restlet.jar HelloWorldClient

run-echo:
	echo Starting Service at:  http://localhost:8111
	java -cp build:lib/org.restlet.jar EchoServer

run-mail-server:
	echo Starting Service at:  http://localhost:8111
	java -cp build:lib/org.restlet.jar MailServerApplication

run-mail-server2:
	echo Starting Service at:  http://localhost:8111
	java -cp build:lib/org.restlet.jar MailServerApplication2

run-mail-client:
	java -cp build:lib/org.restlet.jar MailClient