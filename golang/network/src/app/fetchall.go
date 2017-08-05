// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/


package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
	"time"
)

// The next program, fetchall, does the same fetch of a URL’s contents as the
// previous example, but it fetches many URLs, all concurrently, so that the
// process will take no longer than the longest fetch rather than the sum of
// all the fetch times. This version of fetchall discards the responses but
// reports the size and elapsed time for each one:


// A goroutine is a concurrent function execution. A channel is a
// communication mechanism that allows one goroutine to pass values of a
// specified type to another goroutine. The function main runs in a goroutine
// and the go statement creates additional goroutines.

//  When one goroutine attempts a send or receive on a channel, it blocks
//  until another goroutine attempts the corresponding receive or send
//  operation, at which point the value is transferred and both goroutines
//  proceed. 

//  In this example, each fetch sends a value (ch <- expression) on
//  the channel ch, and main receives all of them (<-ch). Having main do all
//  the printing ensures that output from each goroutine is processed as a
//  unit, with no danger of interleaving if two goroutines finish at the same
//  time.

func main() {

	start := time.Now()
	ch := make(chan string)

	// The main function creates a channel of strings using make. For each
	// command-line argument, the go statement in the first range loop starts a
	// new goroutine that calls fetch asynchronously to fetch the URL using
	// http.Get.
	for _, url := range os.Args[1:] {
		go fetch(url, ch) // start a goroutine
	}

	// The second range loop in main receives and prints those lines
	// (sent by fetch to channel)
	for range os.Args[1:] {
		fmt.Println(<-ch) // receive from channel ch
	}

	// 
	fmt.Printf("%.2fs elapsed\n", time.Since(start).Seconds())
}

func fetch(url string, ch chan<- string) {
	start := time.Now()
	resp, err := http.Get(url)
	if err != nil {
		ch <- fmt.Sprint(err) // send to channel ch
		return
	}

	// The io.Copy function reads the body of the response and discards it by
	// writing to the ioutil.Discard output stream. Copy returns the byte
	// count, along with any error that occurred.

	nbytes, err := io.Copy(ioutil.Discard, resp.Body)
	resp.Body.Close() // don't leak resources
	if err != nil {
		ch <- fmt.Sprintf("while reading %s: %v", url, err)
		return
	}
	secs := time.Since(start).Seconds()

	// As each result arrives, fetch sends a summary line on the channel ch.
	ch <- fmt.Sprintf("%.2fs  %7d  %s", secs, nbytes, url)
}


