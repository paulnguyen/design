package main

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_055.html

	Composing Types by Struct Embedding

*/

// Coloredpoint demonstrates struct embedding.

import (
	"fmt"
	"image/color"
	"math"
)

type Point struct{ X, Y float64 }

// We could have defined ColoredPoint as a struct of three fields, but instead
// we embedded a Point to provide the X and Y fields.
type ColoredPoint struct {
	Point
	Color color.RGBA
}

// Embedding lets us take a syntactic shortcut to defining a ColoredPoint that
// contains all the fields of Point, plus some more. If we want, we can select
// the fields of ColoredPoint that were contributed by the embedded Point
// without mentioning Point: -- A similar mechanism applies to the methods
// of Point...
/*

	var cp ColoredPoint
	cp.X = 1
	fmt.Println(cp.Point.X) // "1"
	cp.Point.Y = 2
	fmt.Println(cp.Y) // "2"

*/

func main() {

	//  A similar mechanism applies to the methods of Point. We can call
	//  methods of the embedded Point field using a receiver of type
	//  ColoredPoint, even though ColoredPoint has no declared methods:

	red := color.RGBA{255, 0, 0, 255}
	blue := color.RGBA{0, 0, 255, 255}
	var p = ColoredPoint{Point{1, 1}, red}
	var q = ColoredPoint{Point{5, 4}, blue}
	fmt.Println(p.Distance(q.Point)) // "5"
	p.ScaleBy(2)
	q.ScaleBy(2)
	fmt.Println(p.Distance(q.Point)) // "10"

	// The methods of Point have been promoted to ColoredPoint. In this way,
	// embedding allows complex types with many methods to be built up by the
	// composition of several fields, each providing a few methods.
}

/*

	Readers familiar with class-based object-oriented languages may be
	tempted to view Point as a base class and ColoredPoint as a subclass or
	derived class, or to interpret the relationship between these types as if a
	ColoredPoint “is a” Point. But that would be a mistake.

	type Point struct{ X, Y float64 }

	type ColoredPoint struct {
		Point
		Color color.RGBA
	}

	Notice the calls to Distance above. Distance has a parameter of type Point,
	and q is not a Point, so although q does have an embedded field of that type,
	we must explicitly select it. Attempting to pass q would be an error:

	p.Distance(q) // compile error: cannot use q (ColoredPoint) as Point
*/

// A ColoredPoint is not a Point, but it “has a” Point, and it has two
// additional methods Distance and ScaleBy promoted from Point. If you prefer
// to think in terms of implementation, the embedded field instructs the
// compiler to generate additional wrapper methods that delegate to the
// declared methods, equivalent to these:
/*

	func (p ColoredPoint) Distance(q Point) float64 {
	    return p.Point.Distance(q)
	}

	func (p *ColoredPoint) ScaleBy(factor float64) {
	    p.Point.ScaleBy(factor)
	}

	 When Point.Distance is called by the first of these wrapper methods, its
	 receiver value is p.Point, not p, and there is no way for the method to
	 access the ColoredPoint in which the Point is embedded.

*/

func (p Point) Distance(q Point) float64 {
	dX := q.X - p.X
	dY := q.Y - p.Y
	return math.Sqrt(dX*dX + dY*dY)
}

func (p *Point) ScaleBy(factor float64) {
	p.X *= factor
	p.Y *= factor
}

func init() {
	p := Point{1, 2}
	q := Point{4, 6}

	distance := Point.Distance   // method expression
	fmt.Println(distance(p, q))  // "5"
	fmt.Printf("%T\n", distance) // "func(Point, Point) float64"

	scale := (*Point).ScaleBy
	scale(&p, 2)
	fmt.Println(p)            // "{2 4}"
	fmt.Printf("%T\n", scale) // "func(*Point, float64)"
}

func init() {
	red := color.RGBA{255, 0, 0, 255}
	blue := color.RGBA{0, 0, 255, 255}

	type ColoredPoint struct {
		*Point
		Color color.RGBA
	}

	p := ColoredPoint{&Point{1, 1}, red}
	q := ColoredPoint{&Point{5, 4}, blue}
	fmt.Println(p.Distance(*q.Point)) // "5"
	q.Point = p.Point                 // p and q now share the same Point
	p.ScaleBy(2)
	fmt.Println(*p.Point, *q.Point) // "{2 2} {2 2}"
}
