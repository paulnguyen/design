
all: clean

clean: 
	find . -name 'app' -type f -exec rm -f {} \; 
	rm -rf bin/*
	go clean

build-app:
	go build src/app/$(app).go
	mv $(app) bin

run-app:
	go run src/app/$(app).go

main:
	go run src/app/main.go

format:
	go fmt app

test1:
	curl http://localhost:8000

test2:
	curl http://localhost:8000/list
	curl http://localhost:8000/price?item=socks
	curl http://localhost:8000/price?item=shoes
	curl http://localhost:8000/price?item=hat
	curl http://localhost:8000/help
