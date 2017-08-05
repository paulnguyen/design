package main

import (
	"fmt"
	"time"
)

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_038.html

    These two statements declare a struct type called Employee and a
	variable called dilbert that is an instance of an Employee

*/

type Employee struct {
	ID        int
	Name      string
	Address   string
	DoB       time.Time
	Position  string
	Salary    int
	ManagerID int
}

var dilbert Employee

/*

	A named struct type S can’t declare a field of the same type S: an aggregate
	value cannot contain itself. (An analogous restriction applies to arrays.) But
	S may declare a field of the pointer type *S, which lets us create recursive
	data structures like linked lists and trees. This is illustrated in the code
	below, which uses a binary tree to implement an insertion sort:

*/

type tree struct {
	value       int
	left, right *tree
}

/*

	The struct type with no fields is called the empty struct, written struct{}.
	It has size zero and carries no information

*/

type empty struct{}

/*

	Point Struct

*/

type Point struct{ X, Y int }

/**

	MAIN ROUTINE

**/

func main() {

	fmt.Println("*** Employee Example ***")
	fmt.Println(dilbert)

	/*

		The individual fields of dilbert are accessed using dot notation like
		dilbert.Name and dilbert.DoB. Because dilbert is a variable, its fields
		are variables too, so we may assign to a field

	*/

	dilbert.Salary -= 5000 // demoted, for writing too few lines of code

	/*

	 or take its address and access it through a pointer:

	*/

	position := &dilbert.Position
	*position = "Senior " + *position // promoted, for outsourcing to Elbonia

	/*

		The dot notation also works with a pointer to a struct:

	*/

	var employeeOfTheMonth *Employee = &dilbert
	employeeOfTheMonth.Position = " (proactive team player)"

	// is equivalent to:

	(*employeeOfTheMonth).Position = " (proactive team player)"

	fmt.Println("*** Results of Struct Updates ***")
	fmt.Printf("dilbert: %+v\n", dilbert)

	/*

	 A value of a struct type can be written using a struct literal that
	 specifies values for its fields.

	*/

	fmt.Println("*** Struct Literals ***")

	// type Point struct{ X, Y int }
	p := Point{1, 2}
	fmt.Println(p)

	/*

		There are two forms of struct literal. The first form, shown above,
		requires that a value be specified for every field, in the right order

		More often, the second form is used, in which a struct value is
		initialized by listing some or all of the field names and their
		corresponding values:

	*/

	p2 := Point{X: 1}
	fmt.Printf("p2: %+v\n", p2)

	/*

		Struct values can be passed as arguments to functions and returned from them

	*/

	fmt.Println(Scale(Point{1, 2}, 5)) // "{5 10}"

	/*

		Because structs are so commonly dealt with through pointers, it’s
		possible to use this shorthand notation to create and initialize a
		struct variable and obtain its address:

		Note:  &Point{1, 2} can be used directly within an expression, such as
		a function call.

	*/

	pp := &Point{1, 2}
	fmt.Printf("pp: %+v\n", pp)

	/*

	 It is exactly equivalent to

	*/

	pp2 := new(Point)
	*pp2 = Point{1, 2}
	fmt.Printf("pp2: %+v\n", pp2)

	/*

		If all the fields of a struct are comparable, the struct itself is
		comparable, so two expressions of that type may be compared
		using == or !=.

	*/

	fmt.Println("*** Comparing Structs ***")

	p3 := Point{1, 2}
	q3 := Point{2, 1}
	x3 := Point{2, 1}
	fmt.Println(p3.X == q3.X && p3.Y == q3.Y) // "false"
	fmt.Println(p3 == q3)                     // "false"
	fmt.Println(q3 == x3)                     // "true"

	/*

	 Comparable struct types, like other comparable types, may be used as the
	 key type of a map.

	*/

	type address struct {
		hostname string
		port     int
	}

	hits := make(map[address]int)
	hits[address{"golang.org", 443}]++

	fmt.Println(hits)

	/*

			Struct Embedding and Anonymous Fields:

		    In this section, we’ll see how Go’s unusual struct embedding mechanism
		    lets us use one named struct type as an anonymous field of another
		    struct type, providing a convenient syntactic shortcut so that a
		    simple dot expression like x.f can stand for a chain of fields like
		    x.d.e.f.

	*/

	fmt.Println("*** Struct Embedding and Anonymous Fields ***")

	type Point1 struct {
		X, Y int
	}

	type Circle1 struct {
		Center Point1
		Radius int
	}

	type Wheel1 struct {
		Circle Circle1
		Spokes int
	}

	// Access to Fields verbose... Notice how the # adverb causes Printf’s %v
	// verb to display values in a form similar to Go syntax.

	var w1 Wheel1
	w1.Circle.Center.X = 8
	w1.Circle.Center.Y = 8
	w1.Circle.Radius = 5
	w1.Spokes = 20
	fmt.Printf("w1 ==> %#v\n", w1) // #%v ==> show struct field names go Go literal notation

	// Go lets us declare a field with a type but no name;
	// such fields are called anonymous fields.

	type Point2 struct {
		X, Y int
	}

	type Circle2 struct {
		Point2
		Radius int
	}

	type Wheel2 struct {
		Circle2
		Spokes int
	}

	//  Thanks to embedding, we can refer to the names at the leaves of the
	//  implicit tree without giving the intervening names:

	var w2 Wheel2
	w2.X = 8      // equivalent to w.Circle.Point.X = 8
	w2.Y = 8      // equivalent to w.Circle.Point.Y = 8
	w2.Radius = 5 // equivalent to w.Circle.Radius = 5
	w2.Spokes = 20
	fmt.Printf("w2 ==> %#v\n", w2) // #%v ==> show struct field names go Go literal notation

	/*

		The fields Circle and Point do have names—that of the named type—but those
		names are optional in dot expressions. We may omit any or all of the
		anonymous fields when selecting their subfields. Unfortunately, there’s no
		corresponding shorthand for the struct literal syntax, so neither of these
		will compile:

		w = Wheel{8, 8, 5, 20}                       // compile error: unknown fields
		w = Wheel{X: 8, Y: 8, Radius: 5, Spokes: 20} // compile error: unknown fields

	*/

	/*

		The struct literal must follow the shape of the type declaration, sowe
		must use one of the two forms below, which are equivalent to eachother:

	*/

	fmt.Println("*** Struct Embedding and Anonymous Fields (Struct Literals) ***")

	type Point struct {
		X, Y int
	}

	type Circle struct {
		Point
		Radius int
	}

	type Wheel struct {
		Circle
		Spokes int
	}

	var w Wheel
	w = Wheel{Circle{Point{8, 8}, 5}, 20}

	fmt.Printf("%#v\n", w)

	// Output:
	// Wheel{Circle:Circle{Point:Point{X:8, Y:8}, Radius:5}, Spokes:20}

	w = Wheel{
		Circle: Circle{
			Point:  Point{X: 8, Y: 8},
			Radius: 5,
		},
		Spokes: 20, // NOTE: trailing comma necessary here (and at Radius)
	}

	w.X = 42

	fmt.Printf("%#v\n", w)
	
	// Output:
	// Wheel{Circle:Circle{Point:Point{X:42, Y:8}, Radius:5}, Spokes:20}

}

/*

Struct values can be passed as arguments to functions and returned from them

*/

func Scale(p Point, factor int) Point {
	return Point{p.X * factor, p.Y * factor}
}

/*

For efficiency, larger struct types are usually passed to or returned from
functions indirectly using a pointer.

And this is required if the function must modify its argument, since in a
call-by-value language like Go, the called function receives only a copy of an
argument, not a reference to the original argument.

*/

func Bonus(e *Employee, percent int) int {
	return e.Salary * percent / 100
}
