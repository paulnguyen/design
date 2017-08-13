package main

import (
	"fmt"
)

const (
   red = 1
   blue = 2
   green = 3
   yello = 4
)

type GumballMachine struct {
	Count       int
	Gumballs 	[]int
	Coins 		map[string]int
}

func main() {

	// allocate a new Struct
	var g1 *GumballMachine = new(GumballMachine)
	fmt.Println( g1 )
	fmt.Println( *g1 )

	fmt.Println( "===== setup array and slices =====")
	gumballs_array_literal := [7]int{red, blue, blue, green, yello, red, red}
	fmt.Println( "gumballs_array_literal = ", gumballs_array_literal, " len = ", len(gumballs_array_literal), " cap = ", cap(gumballs_array_literal) )
	gumballs_slice_literal := []int{red, blue, blue, green, yello, red, red}
	fmt.Println( "gumballs_slice_literal = ", gumballs_slice_literal, " len = ", len(gumballs_slice_literal), " cap = ", cap(gumballs_slice_literal)  )
	gumballs_slice_variable := make([]int,0,100) // make a new slice -- initial = 0, capacity = 100
	fmt.Println( "gumballs_slice_variable (initial) = ", gumballs_slice_variable, len(gumballs_slice_variable), " cap = ", cap(gumballs_slice_variable) )
	gumballs_slice_variable = append(gumballs_slice_variable, red)
	gumballs_slice_variable = append(gumballs_slice_variable, blue)
	gumballs_slice_variable = append(gumballs_slice_variable, blue)
	gumballs_slice_variable = append(gumballs_slice_variable, green)
	gumballs_slice_variable = append(gumballs_slice_variable, yello)
	fmt.Println( "gumballs_slice_variable (loaded) = ", gumballs_slice_variable, len(gumballs_slice_variable), " cap = ", cap(gumballs_slice_variable)  )

	fmt.Println( "===== struct initialization with array literal =====")
	g2 := GumballMachine {
		Count: 		len(gumballs_array_literal),
		Gumballs: 	gumballs_array_literal[:],  // slice of backing array
		Coins: 		map[string]int { "25 cents": 25, "10 cents": 10, "1 cent": 1},
	}
	fmt.Println( g2 )
	fmt.Println( &g2 )

	fmt.Println( "===== struct initialization with slice variable =====")
	g3 := GumballMachine {
		Count: 		0,
		Gumballs: 	nil,  
		Coins: 		map[string]int { "25 cents": 25, "10 cents": 10, "1 cent": 1},
	}
	g3.Gumballs = gumballs_slice_variable
	g3.Count = len(gumballs_slice_variable)
	fmt.Println( g3 )
	fmt.Println( &g3 )


	

}