
all: clean

clean: 
	find . -name 'app' -type f -exec rm -f {} \; 
	rm -rf bin/*
	go clean

main:
	go run src/app/main.go

run-app:
	go run src/app/$(app).go

format:
	go fmt app
	go fmt main

doc:
	go doc app


