
// Old procedural Stack

#include <iostream>
#include <string>
#include <vector>
#include <cassert>
using namespace std;

typedef vector<string> Stack;

bool isEmpty (const Stack& s) {
	return 0 == s.size(); 
}

void pop (Stack& s) { 
	assert (!isEmpty(s)); 
	s.pop_back();
}

string top (const Stack& s) { 
	assert (!isEmpty(s)); 
	return s.back();
} 

void push (Stack& s, string e) { 
	s.push_back(e);
}


// Old procedural Stack

int main (int argc, char* argv[]) {
    Stack s1;
	Stack* s2 = new Stack; 
	push (s1, "alpaca"); 
	push (s1, "beaver"); 
	push (s1, "cat");
	push (s1, "dog");
	push (*s2, "one");
	push (*s2, "two");
	cout << top(s1) << endl; 
	cout << top(*s2) << endl; 
	pop(s1);
	cout << top(s1) << endl; 
}