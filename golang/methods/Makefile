
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

format:
	go fmt app




