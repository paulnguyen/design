all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;
	rm -rf target/*

compile:
	grails compile

run:
	grails run-app

run-test:
	grails test run-app
	