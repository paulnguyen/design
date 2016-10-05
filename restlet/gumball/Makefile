
all: clean compile 

clean:
	rm -rf build/*
	rm -f dist/app.jar
	find . -name "*.class" -exec rm -rf {} \;

jar: compile
	cd build ; jar -cvfe ../dist/app.jar GumballServer .

test: compile
	java -cp dist/restlet.jar:dist/restlet-json.jar:dist/app.jar GumballServer

compile: 
	javac -cp lib/org.restlet.jar:lib/org.restlet.ext.json.jar -d build \
	src/gumball/*.java \
	src/org/json/*.java \
	src/api/*.java

run:
	echo Starting Service at:  http://localhost:8080
	java -cp build:lib/org.restlet.jar:lib/org.restlet.ext.json.jar api.GumballServer

docker-build: jar
	docker build -t gumball .
	docker images

docker-clean:
	docker stop gumball
	docker rm gumball
	docker rmi gumball

docker-run:
	docker run --name gumball -td gumball
	docker ps

docker-run-host:
	docker run --name gumball -td --net=host gumball
	docker ps

docker-run-bridge:
	docker run --name gumball -td -p 80:8080 gumball
	docker ps

docker-network:
	docker network inspect host
	docker network inspect bridge

docker-stop:
	docker stop gumball
	docker rm gumball

docker-shell:
	docker exec -it gumball bash 
	
