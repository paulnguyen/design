all: clean

clean: 
	find . -name "*.class" -exec rm -rf {} \;

run_abstract_factory:
	javac abstract_factory/*.java
	java  abstract_factory.RunTest
	
run_class_adapter:
	javac adapter/classAdapter/*.java
	java  adapter.classAdapter.Client
	
run_object_adapter:
	javac adapter/objectAdapter/*.java
	java  adapter.objectAdapter.Client

run_chain_of_responsibility:
	javac chain_of_responsibility/*.java
	java  chain_of_responsibility.Client

run_command:
	javac command/*.java
	java  command.Client
	
run_composite:
	javac composite/*.java
	java  composite.Client
	
run_decorator:
	javac decorator/*.java
	java  decorator.Tester

run_factory_method:
	javac factory_method/*.java
	java  factory_method.RunTest
	
run_iterator:
	javac iterator/*.java
	java  iterator.QueryTool

run_observer:
	javac observer/*.java
	java  observer.ObserverTest
	
run_proxy:
	javac proxy/*.java
	java  proxy.Client
	
run_singleton:
	javac singleton/*.java
	java  singleton.Client
	
run_state:
	javac state/*.java
	java  state.Tester
	
run_strategy:
	javac strategy/*.java
	java  strategy.Tester
