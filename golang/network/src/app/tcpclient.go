package main

/*

REF:  https://www.safaribooksonline.com/library/view/go-design-patterns/9781788390552/ch11.html

*/

import (
	"fmt"
	"io"
	"net"
	"os"
)

func main() {

	// JoinHostPort combines host and port into a network address of the form "host:port"
	// REF:  https://golang.org/pkg/net/#JoinHostPort
	host, port := "www.gutenberg.org", "80"
	addr := net.JoinHostPort(host, port)
	fmt.Printf("net.JoinHostPort(%s, %s) = %s\n", host, port, addr)

	// Set HTTP Request Payload
	httpRequest := "GET  /cache/epub/16328/pg16328.txt HTTP/1.1\n" +
		"Host: " + host + "\n\n"
	fmt.Println("httpRequest = ", httpRequest)

	// Client programs use the net.Dial function to connect to a host service
	// over the network.
	// REF:  https://golang.org/pkg/net/#Dial
	conn, err := net.Dial("tcp", addr)
	if err != nil {
		fmt.Println(err)
		return
	}

	// https://tour.golang.org/flowcontrol/12  A defer statement defers the
	//
	// execution of a function until the surrounding function returns. The
	// deferred call's arguments are evaluated immediately, but the function
	// call is not executed until the surrounding function returns.
	//
	defer conn.Close()

	if _, err = conn.Write([]byte(httpRequest)); err != nil {
		fmt.Println(err)
		return
	}

	file, err := os.Create("beowulf.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	io.Copy(file, conn)
	fmt.Println("Text copied to file", file.Name())
}

// NOTE:  Because the net.Conn type implements the io.Reader and io.Writer, it
// can be used to both send data and receive data using streaming IO
// semantics.
