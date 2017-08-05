// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_066.html

	mux.HandleFunc("/list", db.list)
	mux.HandleFunc("/price", db.price)

	It’s easy to see from the code above how one would construct a program in
	which there are two different web servers, listening on different ports,
	defining different URLs, and dispatching to different handlers. We would
	just construct another ServeMux and make another call to ListenAndServe,
	perhaps concurrently. But in most programs, one web server is plenty.
	Also, it’s typical to define HTTP handlers across many files of an
	application, and it would be a nuisance if they all had to be explicitly
	registered with the application’s ServeMux instance.

	So, for convenience, net/http provides a global ServeMux instance called
	DefaultServeMux and package-level functions called http.Handle and
	http.HandleFunc. To use DefaultServeMux as the server’s main handler, we
	needn’t pass it to ListenAndServe; nil will do.

	The server’s main function can then be simplified to...

*/

// Http5 is an e-commerce server that registers the /list and /price
// endpoint by calling http.HandleFunc.

package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	db := database{"shoes": 50, "socks": 5}
	http.HandleFunc("/list", db.list)
	http.HandleFunc("/price", db.price)
	log.Fatal(http.ListenAndServe("localhost:8000", nil))
}

type dollars float32

func (d dollars) String() string { return fmt.Sprintf("$%.2f", d) }

type database map[string]dollars

func (db database) list(w http.ResponseWriter, req *http.Request) {
	for item, price := range db {
		fmt.Fprintf(w, "%s: %s\n", item, price)
	}
}

func (db database) price(w http.ResponseWriter, req *http.Request) {
	item := req.URL.Query().Get("item")
	if price, ok := db[item]; ok {
		fmt.Fprintf(w, "%s\n", price)
	} else {
		w.WriteHeader(http.StatusNotFound) // 404
		fmt.Fprintf(w, "no such item: %q\n", item)
	}
}
