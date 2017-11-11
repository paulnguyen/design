all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;
	rm -rf build/*


# Gradle: 		https://guides.gradle.org/creating-new-gradle-builds/
# CircleCI:		https://circleci.com/docs/2.0/language-go/

init:
	gradle init

compile:
	gradle build

test:
	gradle test

jar: compile
	gradle shadowJar

run: 
	java -cp build/libs/junit-all.jar gumball.Kid

