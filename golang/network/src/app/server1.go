// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

package main

import (
	"fmt"
	"log"
	"net/http"
)

// The main function connects a handler function to incoming URLs whose path
// begins with /, which is all URLs, and starts a server listening for
// incoming requests on port 8000.

func main() {
	http.HandleFunc("/", handler) // each request calls handler
	log.Fatal(http.ListenAndServe("localhost:8000", nil))
}

// A request is represented as a struct of type http.Request, which contains a
// number of related fields, one of which is the URL of the incoming request.
// When a request arrives, it is given to the handler function, which extracts
// the path component (/hello) from the request URL and sends it back as the
// response, using fmt.Fprintf.

func handler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "URL.Path = %q\n", r.URL.Path)
}

/* 	https://golang.org/pkg/net/http/
https://golang.org/pkg/net/http/#Request
*/
