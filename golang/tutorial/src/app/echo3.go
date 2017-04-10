// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

package main

import (
	"fmt"
	"os"
	"strings"
)

func main() {
	fmt.Println(strings.Join(os.Args[1:], "-"))
}

/*

A simpler and more efficient solution would be to use the Join function
from the strings package:

	func main() {
    	fmt.Println(strings.Join(os.Args[1:], " "))
	}

 */