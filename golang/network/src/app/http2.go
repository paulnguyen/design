// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_066.html

	Now the handler decides what logic to execute based on the path component
	of the URL, req.URL.Path. If the handler doesn’t recognize the path, it
	reports an HTTP error to the client by calling 

		w.WriteHeader(http.StatusNotFound); 

	this must be done before writing any text to w. 

	(Incidentally, http.ResponseWriter is another interface. It
	augments io.Writer with methods for sending HTTP response headers.)
	
	Equivalently, we could use the http.Error utility function:

		msg := fmt.Sprintf("no such page: %s\n", req.URL)
		http.Error(w, msg, http.StatusNotFound) // 404
*/

// Http2 is an e-commerce server with /list and /price endpoints.
package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	db := database{"shoes": 50, "socks": 5}
	log.Fatal(http.ListenAndServe("localhost:8000", db))
}

type dollars float32

func (d dollars) String() string { return fmt.Sprintf("$%.2f", d) }

type database map[string]dollars

func (db database) ServeHTTP(w http.ResponseWriter, req *http.Request) {
	switch req.URL.Path {
	case "/list":
		for item, price := range db {
			fmt.Fprintf(w, "%s: %s\n", item, price)
		}
	case "/price":
		item := req.URL.Query().Get("item")
		price, ok := db[item]
		if !ok {
			w.WriteHeader(http.StatusNotFound) // 404
			fmt.Fprintf(w, "no such item: %q\n", item)
			return
		}
		fmt.Fprintf(w, "%s\n", price)
	default:
		w.WriteHeader(http.StatusNotFound) // 404
		fmt.Fprintf(w, "no such page: %s\n", req.URL)
	}
}


// Obviously we could keep adding cases to ServeHTTP, but in a realistic
// application, it’s convenient to define the logic for each case in a
// separate function or method. Furthermore, related URLs may need similar
// logic; several image files may have URLs of the form /images/*.png, for
// instance. For these reasons, net/http provides ServeMux, a request
// multiplexer, to simplify the association between URLs and handlers. A
// ServeMux aggregates a collection of http.Handlers into a single
// http.Handler. Again, we see that different types satisfying the same
// interface are substitutable: the web server can dispatch requests to any
// http.Handler, regardless of which concrete type is behind it.


