package main

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_056.html

    Method Values and Expressions

*/

import (
	"fmt"
	"math"
)

type Point struct{ X, Y float64 }

func (p *Point) ScaleBy(factor float64) {
	p.X *= factor
	p.Y *= factor
}

func (p Point) Distance(q Point) float64 {
	dX := q.X - p.X
	dY := q.Y - p.Y
	return math.Sqrt(dX*dX + dY*dY)
}

func main() {

	fmt.Println("*** Example 1 ***")
	example1()
	fmt.Println("*** Example 2 ***")
	example2()

}

//  Usually we select and call a method in the same expression, as in
//  p.Distance(), but it’s possible to separate these two operations. The
//  selector p.Distance yields a method value, a function that binds a method
//  (Point.Distance) to a specific receiver value p. This function can then be
//  invoked without a receiver value; it needs only the non-receiver
//  arguments.

func example1() {

	p := Point{1, 2}
	q := Point{4, 6}

	distanceFromP := p.Distance        // method value
	fmt.Println(distanceFromP(q))      // "5"
	var origin Point                   // {0, 0}
	fmt.Println(distanceFromP(origin)) // "2.23606797749979", √5

	scaleP := p.ScaleBy // method value
	scaleP(2)           // p becomes (2, 4)
	scaleP(3)           //      then (6, 12)
	scaleP(10)          //      then (60, 120)

}

/*
	Method values are useful when a package’s API calls for a function value,
	and the client’s desired behavior for that function is to call a method on
	a specific receiver. For example, the function time.AfterFunc calls a
	function value after a specified delay. This program uses it to launch the
	rocket r after 10 seconds:

		type Rocket struct {  ...  }
		func (r *Rocket) Launch() { ...  }

		r := new(Rocket)
		time.AfterFunc(10 * time.Second, func() { r.Launch() })

	The method value syntax is shorter:

		time.AfterFunc(10 * time.Second, r.Launch)
*/

//  Related to the method value is the method expression. When calling a
//  method, as opposed to an ordinary function, we must supply the receiver in
//  a special way using the selector syntax. A method expression, written T.f
//  or (*T).f where T is a type, yields a function value with a regular first
//  parameter taking the place of the receiver, so it can be called in the
//  usual way.

func example2() {

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

//  Method expressions can be helpful when you need a value to represent a
//  choice among several methods belonging to the same type so that you can
//  call the chosen method with many different receivers. In the following
//  example, the variable op represents either the addition or the subtraction
//  method of type Point, and Path.TranslateBy calls it for each point in the
//  Path:

func (p Point) Add(q Point) Point { return Point{p.X + q.X, p.Y + q.Y} }
func (p Point) Sub(q Point) Point { return Point{p.X - q.X, p.Y - q.Y} }

type Path []Point

func (path Path) TranslateBy(offset Point, add bool) {
	var op func(p, q Point) Point
	if add {
		op = Point.Add
	} else {
		op = Point.Sub
	}
	for i := range path {
		// Call either path[i].Add(offset) or path[i].Sub(offset).
		path[i] = op(path[i], offset)
	}
}
