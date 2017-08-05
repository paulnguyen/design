package main

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_052.html

    In this chapter, the first of two on object-oriented programming, we’ll
	show how to define and use methods effectively.  We’ll also cover two key
	principles of object-oriented programming, encapsulation and composition.

*/

import (
	"fmt"
	"math"
)

// A method is declared with a variant of the ordinary function declaration in
// which an extra parameter appears before the functionname.The parameter
// attaches the function to the type of that parameter.

func main() {

	//  In a method call, the receiver argument appears before the method
	//  name. This parallels the declaration, in which the receiver parameter
	//  appears before the method name.

	p := Point{1, 2}
	q := Point{4, 6}
	fmt.Println("Distance(p, q) // function call => ", Distance(p, q)) // "5", function call
	fmt.Println("p.Distance(q) // method call    => ", p.Distance(q))  // "5", method call

	//  In the two calls to methods named Distance, the compiler
	//  determines which function to call based on both the method name and
	//  the type of the receiver. In the first, path[i-1] has type Point so
	//  Point.Distance is called; in the second, perim has type Path, so
	//  Path.Distance is called.

	perim := Path{{1, 1}, {5, 1}, {5, 4}, {1, 1}}
	fmt.Println("PathDistance(perim) // func call ==> ", PathDistance(perim)) // "12", standalone function
	fmt.Println("perim.Distance() // method call  ==> ", perim.Distance())    // "12", method of Path
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

// The extra parameter p is called the method’s receiver, a legacy from early
// object-oriented languages that described calling a method as “sending a
// message to an object". In Go, we don’t use a special name like this or self
// for the receiver; we choose receiver names just as we would for any other
// parameter.  Since the receiver name will be frequently used, it’s agood
// idea to choose something short and to be consistent across methods. A
// common choice is the first letter of the type name, like p for Point.

// A Path is a journey connecting the points with straight lines.
type Path []Point

// Distance returns the distance traveled along the path.
func (path Path) Distance() float64 {
	sum := 0.0
	for i := range path {
		if i > 0 {
			sum += path[i-1].Distance(path[i])
		}
	}
	return sum
}

func PathDistance(path Path) float64 {
	sum := 0.0
	for i := range path {
		if i > 0 {
			sum += path[i-1].Distance(path[i])
		}
	}
	return sum
}

//  Path is a named slice type, not a struct type like Point, yet we can still
//  define methods for it. In allowing methods to be associated with any type,
//  Go is unlike many other object-oriented languages. It is often convenient
//  to define additional behaviors for simple types such as numbers, strings,
//  slices, maps, and sometimes even functions. Methods may be declared on any
//  named type defined in the same package, so long as its underlying type is
//  neither a pointer nor an interface.
