package main

import (
	"fmt"
	"log"
	"net/http"
)


/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_066.html
	https://golang.org/pkg/net/http/

	package http

	type Handler interface {
	    ServeHTTP(w ResponseWriter, r *Request)
	}

	func ListenAndServe(address string, h Handler) error

	The ListenAndServe function requires a server address, such as
	"localhost:8000", and an instance of the Handler interface to which all
	requests should be dispatched. It runs forever, or until the server fails (or
	fails to start) with an error, always non-nil, which it returns.

*/


// Http1 is a rudimentary e-commerce server.  Imagine an e-commerce site with
// a database mapping the items for sale to their prices in dollars. The
// program below shows the simplest imaginable implementation. It models the
// inventory as a map type, database, to which we’ve attached a ServeHTTP
// method so that it satisfies the http.Handler interface. The handler ranges
// over the map and prints the items.

func main() {
	db := database{"shoes": 50, "socks": 5}
	log.Fatal(http.ListenAndServe("localhost:8000", db))
}

type dollars float32

func (d dollars) String() string { return fmt.Sprintf("$%.2f", d) }

type database map[string]dollars

func (db database) ServeHTTP(w http.ResponseWriter, req *http.Request) {
	for item, price := range db {
		fmt.Fprintf(w, "%s: %s\n", item, price)
	}
}
