// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_066.html

	For a more complex application, several ServeMuxes may be composed to
	handle more intricate dispatching requirements. Go doesn’t have a
	canonical web framework analogous to Ruby’s Rails or Python’s Django. This
	is not to say that such frameworks don’t exist, but the building blocks in
	Go’s standard library are flexible enough that frameworks are often
	unnecessary. Furthermore, although frameworks are convenient in the early
	phases of a project, their additional complexity can make longer-term
	maintenance harder.

	In the program below, we create a ServeMux and use it to associate the
	URLs with the corresponding handlers for the /list and /price operations,
	which have been split into separate methods. We then use the ServeMux as
	the main handler in the call to ListenAndServe.

*/

// Http3 is an e-commerce server that registers the /list and /price
// endpoints by calling (*http.ServeMux).Handle.
package main

import (
	"fmt"
	"log"
	"net/http"
)

type dollars float32

func (d dollars) String() string { return fmt.Sprintf("$%.2f", d) }

func main() {
	db := database{"shoes": 50, "socks": 5}
	mux := http.NewServeMux()
	mux.Handle("/list", http.HandlerFunc(db.list))
	mux.Handle("/price", http.HandlerFunc(db.price))
	log.Fatal(http.ListenAndServe("localhost:8000", mux))
}

//  Let’s focus on the two calls to mux.Handle that register the handlers. In
//  the first one, db.list is a method value (§6.4), that is, a value of type
//
//  func(w http.ResponseWriter, req *http.Request)  <== method value of db.list
//
//  that, when called, invokes the database.list method with the receiver value
//  db. So db.list is a function that implements handler-like behavior, but
//  since it has no methods, it doesn’t satisfy the http.Handler interface and
//  can’t be passed directly to mux.Handle.

//  The expression http.HandlerFunc(db.list) is a conversion, not a function
//  call, since http.HandlerFunc is a type. It has the following definition:
//
//	type HandlerFunc func(w ResponseWriter, r *Request)
//
//	func (f HandlerFunc) ServeHTTP(w ResponseWriter, r *Request) {
//	    f(w, r)
//	}

type database map[string]dollars

func (db database) list(w http.ResponseWriter, req *http.Request) {
	for item, price := range db {
		fmt.Fprintf(w, "%s: %s\n", item, price)
	}
}

func (db database) price(w http.ResponseWriter, req *http.Request) {
	item := req.URL.Query().Get("item")
	price, ok := db[item]
	if !ok {
		w.WriteHeader(http.StatusNotFound) // 404
		fmt.Fprintf(w, "no such item: %q\n", item)
		return
	}
	fmt.Fprintf(w, "%s\n", price)
}


/*

package http

type HandlerFunc func(w ResponseWriter, r *Request)

func (f HandlerFunc) ServeHTTP(w ResponseWriter, r *Request) {
	f(w, r)
}

*/
