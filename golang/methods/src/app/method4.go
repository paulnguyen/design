package main

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_055.html

	Composing Types by Struct Embedding (Cont.)

*/

// Coloredpoint demonstrates struct embedding.

import (
	"fmt"
	"image/color"
	"math"
	"sync"
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

type ColoredPointA struct {
	Point
	Color color.RGBA
}

//  The type of an anonymous field may be a pointer to a named type, in which
//  case fields and methods are promoted indirectly from the pointed-to
//  object. Adding another level of indirection lets us share common
//  structures and vary the relationships between objects dynamically. The
//  declaration of ColoredPoint below embeds a *Point:

type ColoredPointB struct {
	*Point
	Color color.RGBA
}

func main() {

	red := color.RGBA{255, 0, 0, 255}
	blue := color.RGBA{0, 0, 255, 255}

	// Pointer Anonymous Field
	p1 := ColoredPointB{&Point{1, 1}, red}
	q1 := ColoredPointB{&Point{5, 4}, blue}
	fmt.Println(p1.Distance(*q1.Point)) // "5"
	q1.Point = p1.Point                 // p and q now share the same Point
	p1.ScaleBy(2)
	fmt.Println(*p1.Point, *q1.Point) // "{2 2} {2 2}"

	// Vs. Value Type Anonymous Field
	p2 := ColoredPointA{Point{1, 1}, red}
	q2 := ColoredPointA{Point{5, 4}, blue}
	fmt.Println(p2.Distance(q2.Point)) // "5"
	q2.Point = p2.Point                // p and q now have same values
	p2.ScaleBy(2)
	fmt.Println(p2.Point, q2.Point) // "{2 2} {1 1}"
}

// Here’s a nice trick to illustrate. This example shows part of a simple
// cache implemented using two package-level variables, a mutex (§9.2) and the
// map that it guards:

var (
	mu      sync.Mutex // guards mapping
	mapping = make(map[string]string)
)

func Lookup(key string) string {
	mu.Lock()
	v := mapping[key]
	mu.Unlock()
	return v
}

//  The version below is functionally equivalent but groups together the two
//  related variables in a single package-level variable, cache:

var cache = struct {
	sync.Mutex
	mapping map[string]string
}{
	mapping: make(map[string]string),
}

func Lookup2(key string) string {
	cache.Lock()
	v := cache.mapping[key]
	cache.Unlock()
	return v
}

// The new variable gives more expressive names to the variables related to
// the cache, and because the sync.Mutex field is embedded within it, its Lock
// and Unlock methods are promoted to the unnamed struct type, allowing us to
// lock the cache with a self-explanatory syntax.
