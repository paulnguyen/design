all: clean compile 

clean:
	rm -rf build/*
	rm -f dist/app.jar
	find . -name "*.class" -exec rm -rf {} \;

jar: compile
	cd build ; jar -cvfe ../dist/app.jar HelloWorldServer .

test: compile
	java -cp dist/restlet.jar:dist/restlet-json.jar:dist/app.jar HelloWorldServer

compile: 
	javac -cp lib/org.restlet.jar:lib/org.restlet.ext.json.jar -d build \
	src/mailserver/*.java \
	src/org/json/*.java \
	src/helloworld/*.java

docker-build: jar
	docker build -t restapi .
	docker images

docker-clean:
	docker stop restapi
	docker rm restapi
	docker rmi restapi

docker-run:
	docker run --name restapi -td restapi
	docker ps

docker-run-host:
	docker run --name restapi -td --net=host restapi
	docker ps

docker-run-bridge:
	docker run --name restapi -td -p 80:8080 restapi
	docker ps

docker-network:
	docker network inspect host
	docker network inspect bridge

docker-stop:
	docker stop restapi
	docker rm restapi

docker-shell:
	docker exec -it restapi bash 
	
run-hello:
	echo Starting Service at:  http://localhost:8080/restlet/hello
	echo Cloud9 Endpoint: https://design-paulnguyen.c9users.io/restlet/hello
	java -cp build:lib/org.restlet.jar helloworld.HelloWorldServer

run-client:
	java -cp build:lib/org.restlet.jar helloworld.HelloWorldClient

run-echo:
	echo Starting Service at:  http://localhost:8111
	java -cp build:lib/org.restlet.jar helloworld.EchoServer

run-mail-server-filter:
	echo Starting Service at:  http://localhost:8111
	java -cp build:lib/org.restlet.jar mailserver.MailServerApplication

run-mail-server-resources:
	echo Starting Service at:  http://localhost:8111
	java -cp build:lib/org.restlet.jar mailserver.MailServerApplication2

run-mail-client:
	java -cp build:lib/org.restlet.jar mailserver.MailClient

run-mail-server-json:
	echo Starting Service at:  http://localhost:8080
	java -cp build:lib/org.restlet.jar:lib/org.restlet.ext.json.jar mailserver.MailServerApplication3
