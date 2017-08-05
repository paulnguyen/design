// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_066.html

 	HandlerFunc demonstrates some unusual features of Go’s interface
 	mechanism. It is a function type that has methods and satisfies an
 	interface, http.Handler. The behavior of its ServeHTTP method is to call
 	the underlying function. HandlerFunc is thus an adapter that lets a
 	function value satisfy an interface, where the function and the
 	interface’s sole method have the same signature. In effect, this trick
 	lets a single type such as database satisfy the http.Handler interface
 	several different ways: once through its list method, once through its
 	price method, and so on.

	Because registering a handler this way is so common, ServeMux has a
	convenience method called HandleFunc that does it for us, so we can
	simplify the handler registration code to this:

	mux.HandleFunc("/list", db.list)
	mux.HandleFunc("/price", db.price)

*/

// Http4 is an e-commerce server that registers the /list and /price
// endpoints by calling (*http.ServeMux).HandleFunc.
package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	db := database{"shoes": 50, "socks": 5}
	mux := http.NewServeMux()

	mux.HandleFunc("/list", db.list)
	mux.HandleFunc("/price", db.price)

	log.Fatal(http.ListenAndServe("localhost:8000", mux))
}

type database map[string]int

func (db database) list(w http.ResponseWriter, req *http.Request) {
	for item, price := range db {
		fmt.Fprintf(w, "%s: $%d\n", item, price)
	}
}

func (db database) price(w http.ResponseWriter, req *http.Request) {
	item := req.URL.Query().Get("item")
	if price, ok := db[item]; ok {
		fmt.Fprintf(w, "$%d\n", price)
	} else {
		w.WriteHeader(http.StatusNotFound) // 404
		fmt.Fprintf(w, "no such item: %q\n", item)
	}
}

/*

package http

type HandlerFunc func(w ResponseWriter, r *Request)

func (f HandlerFunc) ServeHTTP(w ResponseWriter, r *Request) {
	f(w, r)
}

*/
