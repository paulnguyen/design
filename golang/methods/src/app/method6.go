package main

/*

	https://www.safaribooksonline.com/library/view/the-go-programming/9780134190570/ebook_split_058.html

	A variable or method of an object is said to be encapsulated if it is
	inaccessible to clients of the object. Encapsulation, sometimes called
	information hiding, is a key aspect of object-oriented programming.

	Go has only one mechanism to control the visibility of names: capitalized
	identifiers are exported from the package in which they are defined, and
	uncapitalized names are not. The same mechanism that limits access to
	members of a package also limits access to the fields of a struct or the
	methods of a type. As a consequence, to encapsulate an object, we must
	make it a struct.

	Encapsulation provides three benefits. First, because clients cannot
	directly modify the object’s variables, one need inspect fewer statements
	to understand the possible values of those variables.

	Second, hiding implementation details prevents clients from depending on
	things that might change, which gives the designer greater freedom to
	evolve the implementation without breaking API compatibility.

    The third benefit of encapsulation, and in many cases the most important,
	is that it prevents clients from setting an object’s variables arbitrarily.
	Because the object’s variables can be set only by functions in the same
	package, the author of that package can ensure that all those functions
	maintain the object’s internal invariants.  

*/

import "fmt"

func main() {
	fmt.Println("Hello Go Methods")
}


// As an example, consider the bytes.Buffer type. It is frequently used to
// accumulate very short strings, so it is a profitable optimization to
// reserve a little extra space in the object to avoid memory allocation in
// this common case. Since Buffer is a struct type, this space takes the form
// of an extra field of type [64]byte with an uncapitalized name. When this
// field was added, because it was not exported, clients of Buffer outside the
// bytes package were unaware of any change except improved performance.
// Buffer and its Grow method are shown below, simplified for clarity:

type Buffer struct {
    buf     []byte
    initial [64]byte
    /* ... */
}

// Grow expands the buffer's capacity, if necessary,
// to guarantee space for another n bytes. [...]
func (b *Buffer) Grow(n int) {
    if b.buf == nil {
        b.buf = b.initial[:0] // use preallocated space initially
    }
    if len(b.buf)+n > cap(b.buf) {
        buf := make([]byte, b.Len(), 2*cap(b.buf) + n)
        copy(buf, b.buf)
        b.buf = buf
    }
}

func (b Buffer) Len() int {
	return 0
}


// 	For example, the Counter type below permits clients to increment the
// 	counter or to reset it to zero, but not to set it to some arbitrary
// 	value:


