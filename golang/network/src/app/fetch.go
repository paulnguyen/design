// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
)

// To illustrate the minimum necessary to retrieve information over HTTP,
// here’s a simple program called fetch that fetches the content of each
// specified URL and prints it as uninterpreted text; it’s inspired by the
// invaluable utility curl. Obviously one would usually do more with such
// data, but this shows the basic idea.

func main() {
	for _, url := range os.Args[1:] {
		resp, err := http.Get(url)
		if err != nil {
			fmt.Fprintf(os.Stderr, "fetch: %v\n", err)
			os.Exit(1)
		}
		b, err := ioutil.ReadAll(resp.Body)
		resp.Body.Close()
		if err != nil {
			fmt.Fprintf(os.Stderr, "fetch: reading %s: %v\n", url, err)
			os.Exit(1)
		}
		fmt.Printf("%s", b)
	}
}


//  This program introduces functions from two packages, net/http and
//  io/ioutil. The http.Get function makes an HTTP request and, if there is no
//  error, returns the result in the response struct resp. The Body field of
//  resp contains the server response as a readable stream. Next,
//  ioutil.ReadAll reads the entire response; the result is stored in b. The
//  Body stream is closed to avoid leaking resources, and Printf writes the
//  response to the standard output.
