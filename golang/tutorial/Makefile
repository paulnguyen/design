
all: clean

clean: 
	find . -name 'app' -type f -exec rm -f {} \; 
	go clean

build:
	go build app
	mv app bin

run:
	go run src/app/main.go

format:
	go fmt app

install:
	go install app

echo1:
	go run src/app/echo1.go arg1 arg2 arg3 arg4

echo2:
	go run src/app/echo2.go arg1 arg2 arg3 arg4

echo3:
	go run src/app/echo3.go arg1 arg2 arg3 arg4

echo4:
	go run src/app/echo4.go arg1 arg2 arg3 arg4