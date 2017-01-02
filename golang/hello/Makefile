
all: clean

clean: 
	find . -name 'main' -type f -exec rm -f {} \; 
	go clean

build:
	go build main

run:
	go run src/main/hello.go

format:
	go fmt main

install:
	go install main


