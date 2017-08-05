package main

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_052.html

	Methods with a Pointer Receiver

*/

import (
	"fmt"
	"math"
)

func main() {

	//  The (*Point).ScaleBy method can be called by providing a *Point receiver,
	//  like this:

	r := &Point{1, 2}
	r.ScaleBy(2)
	fmt.Println(*r) // "{2, 4}"

	// or this:
	p1 := Point{1, 2}
	pptr := &p1
	pptr.ScaleBy(2)
	fmt.Println(p1) // "{2, 4}"

	// or this:
	p2 := Point{1, 2}
	(&p2).ScaleBy(2)
	fmt.Println(p2) // "{2, 4}"

	//  Named types (Point) and pointers to them (*Point) are the only types
	//  that may appear in a receiver declaration. Furthermore, to avoid
	//  ambiguities, method declarations are not permitted on named types that
	//  are themselves pointer types:

	/*
		type P *int
		func (P) f() { ... } // compile error: invalid receiver type
	*/

}

// 	Because calling a function makes a copy of each argument value, if a function
// 	needs to update a variable, or if an argument is so large that we wish to
// 	avoid copying it, we must pass the address of the variable using a pointer.
// 	The same goes for methods that need to update the receiver variable: we attach
// 	them to the pointer type, such as *Point.

func (p *Point) ScaleBy(factor float64) {
	p.X *= factor
	p.Y *= factor
}

// Defines simple types for plane geometry.
type Point struct{ X, Y float64 }

// traditional function
func Distance(p, q Point) float64 {
	return math.Hypot(q.X-p.X, q.Y-p.Y)
}

// same thing, but as a method of the Point type
func (p Point) Distance(q Point) float64 {
	return math.Hypot(q.X-p.X, q.Y-p.Y)
}

// Nil Is a Valid Receiver Value.  Just as some functions allow nil pointers
// as arguments, so do some methods for their receiver, especially if nil is a
// meaningful zero value of the type, as with maps and slices. In this simple
// linked list of integers, nil represents the empty list:

// An IntList is a linked list of integers.
// A nil *IntList represents the empty list.
type IntList struct {
	Value int
	Tail  *IntList
}

// Sum returns the sum of the list elements.
func (list *IntList) Sum() int {
	if list == nil {
		return 0
	}
	return list.Value + list.Tail.Sum()
}
