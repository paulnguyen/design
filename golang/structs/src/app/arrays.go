package main

import "fmt"

/*

By default, the elements of a new array variable are initially set to the zero
value for the element type, which is 0 for numbers.

*/

func main() {

	fmt.Println( "*** Arrays Basics ***")
	var a [3]int                               // array of 3 integers
	fmt.Println("a[0] = ", a[0])               // print the first element
	fmt.Println("a[len(a)-1] = ", a[len(a)-1]) // print the last element, a[2]

	// Print the indices and elements.
	fmt.Println( "*** Array Enumeration (Indices & Elements) ***")
	for i, v := range a {
		fmt.Printf("%d = %d\n", i, v)
	}

	// Print the elements only.
	fmt.Println( "*** Array Enumeration (Elements Only) ***")
	for _, v := range a {
		fmt.Printf("%d\n", v)
	}

	/*

		We can use an array literal to initialize an array with a list of values

	*/

	fmt.Println( "*** Array Literals ***")
	var q [3]int = [3]int{1, 2, 3}
	var r [3]int = [3]int{1, 2}

	fmt.Println("q = ", q)
	fmt.Println("r = ", r)
	fmt.Println("q[2] = ", q[2]) // "3"
	fmt.Println("r[2] = ", r[2]) // "0"

	/*

		In an array literal, if an ellipsis “...” appears in place of the length,
		the array length is determined by the number of initializers.

	*/

	q2 := [...]int{1, 2, 3}
	fmt.Printf("%T\n", q2) // "[3]int"

	/*

		The size of an array is part of its type, so [3]int and [4]int are
		different types

	*/

	//q3 := [3]int{1, 2, 3}
	//q3 = [4]int{1, 2, 3, 4} // compile error: cannot assign [4]int to [3]int

	/*

		The literal syntax is similar for arrays, slices, maps, and structs. The
		specific form above is a list of values in order, but it is also possible
		to specify a list of index and value pairs, like this:

	*/

	type Currency int
	const (
		USD Currency = iota
		EUR
		GBP
		RMB
	)

	symbol := [...]string{USD: "$", EUR: "€", GBP: "£", RMB: "¥"}
	fmt.Println(RMB, symbol[RMB]) // "3 ¥"

	/*

		Indices can appear in any order and some may be omitted;as before,
		unspecified values take on the zero value for the element type.

		The following defines an array r with 100 elements, all zero except for
		the last, which has value −1.

	*/

	fmt.Println( "*** Sparse Arrays ***")
	array1 := [...]int{99: -1}
	fmt.Println("array1 = ", array1)

	/*

	 If an array’s element type is comparable then the array type is
	 comparable too, so we may directly compare two arrays of that type using
	 the == operator, which reports whether all corresponding elements are
	 equal. The != operator is its negation.

	*/

	fmt.Println( "*** Array Comparisons ***")
	a1 := [2]int{1, 2}
	b1 := [...]int{1, 2}
	c1 := [2]int{1, 3}
	fmt.Println(a1 == b1, a1 == c1, b1 == c1) // "true false false"
	//d := [3]int{1, 2}
	//fmt.Println(a == d) // compile error: cannot compare [2]int == [3]int
}
