all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;
	rm -rf build/*

init:
	gradle init
	mkdir -p libs
	mkdir -p src/main/java
	mkdir -p src/test/java

compile:
	gradle build -x test 

test:
	gradle test

jar: compile
	gradle shadowJar

run: jar
	java -cp build/libs/helloworld-all.jar Main

