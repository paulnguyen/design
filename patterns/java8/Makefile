all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;
	rm -rf build/*

build:
	gradle build

run-singleton:
	java -cp build/libs/patterns.jar singleton.RunTest	

run-observer-1:
	java -cp build/libs/patterns.jar observer.ballistics.ShowBallistics	

run-observer-2:
	java -cp build/libs/patterns.jar observer.ballistics2.ShowBallistics2	

run-observer-3:
	java -cp build/libs/patterns.jar observer.ballistics3.ShowBallistics3	

run-adapter:
	java -cp build/libs/patterns.jar adapter.ShowRocketTable

run-proxy-1:
	java -cp build/libs/patterns.jar:src/main/java proxy.ShowProxy

run-proxy-2:
	java -cp build/libs/patterns.jar proxy.dynamic.ShowDynamicProxy

run-factory-method:
	java -cp build/libs/patterns.jar factoryMethod.ShowIterator

run-decorator-1:
	java -cp build/libs/patterns.jar decorator.ShowFun

run-decorator-2:
	java -cp build/libs/patterns.jar decorator.ShowDecorator
	cat sample.txt
	rm sample.txt

run-decorator-3:
	java -cp build/libs/patterns.jar decorator.ShowLowerCase

run-decorator-4:
	cat data/adcopy.txt
	java -cp build/libs/patterns.jar decorator.ShowFilters data/adcopy.txt data/adout.txt
	cat data/adout.txt
	rm data/adout.txt

run-decorator-5:
	cat data/adcopy.txt
	java -cp build/libs/patterns.jar decorator.ShowFilters2 data/adcopy.txt 

run-decorator-6:
	cat data/adcopy.txt
	java -cp build/libs/patterns.jar decorator.ShowFilters3 data/adcopy.txt 

run-decorator-7:
	java -cp build/libs/patterns.jar decorator.brightness.ShowBrightness 

run-decorator-8:
	java -cp build/libs/patterns.jar decorator.brightness.ShowBrightness2 





	
