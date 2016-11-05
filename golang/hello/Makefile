
all: clean

clean: 
	find . -name "main" -exec rm -f {} \; > /dev/null 2>&1 
	go clean

compile:
	go build main

build:
	go build main

run:
	go run src/main/hello.go

format:
	go fmt main

install:
	go install main