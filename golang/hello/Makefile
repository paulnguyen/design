
# https://tour.golang.org/welcome

all: clean

clean: 
	find . -name 'main' -type f -exec rm -f {} \; 
	go clean

build:
	go build main

run:
	go run src/main/hello.go

playground:
	go run src/playground/main.go

format:
	go fmt main

install:
	go install main


