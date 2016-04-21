#include <iostream>
#include "stack.h"

/* stack.h
namespace Stack {
	void push(char) ;
	char pop() ;
}
*/

namespace Stack {
	const int MAX_SIZE = 10 ;
	char v[MAX_SIZE] ;
	int top = 0 ;

	class Overflow {} ;

	void push( char c ) {
        cout << "Push " << c << "\n" ;
		if (top==MAX_SIZE)
			throw Overflow() ;
		v[top++] = c ;
	}

	char pop() {
        cout << "Popping...\n" ;
		return v[--top] ;
	}
}

void test1() {
	char ch ;

	Stack::push('c') ;
	ch = Stack::pop() ;

	if ( ch != 'c' )
		cout << "Impossible!"  ;
	else
		cout << "Got: " << ch ;
}

void test2() {
  try {
	for(;;) {
		Stack::push('c') ;
	}
  }
  catch (Stack::Overflow) {
	cout << "Caught Overflow Exception...\n" ;
  }
}

int main() {
    //test1() ;
	test2() ;
}
