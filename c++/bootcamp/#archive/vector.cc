#include <iostream>
#include <vector>
#include <string>

template<class T> class Vec : public vector<T> {
public:
   Vec() : vector<T>() {}
   Vec(int s) : vector<T>(s) {}

   T& operator[] (int i) { return at(i) ; }
   const T& operator[] (int i) const { return at(i) ; }
} ;

class Entry {
   string name ;
   int number ;
public:
   Entry() {}
   Entry( string name_val, int num_val ) {
      name = name_val ;
      number = num_val ;
   }

   void print() {
        cout << name << " (" << number << ")" << "\n" ;
   }

} ;


int main() {
   Entry e1 = Entry( "John Smith", 12345 ) ;
   Entry e2 = Entry( "Jane Smith", 67890 ) ;
   Entry e3 = Entry( "Mary Doe", 98765 ) ;

   Vec<Entry> database(2) ;
}
