#include <stl.h>
#include <iostream>
#include <vector>
#include <string>

class Hello {
private:
   string _msg ;
public:
   Hello( string msg ) { _msg = msg ; }
   void sayHello() {
      cout << "Hello Member Function! Msg:" << _msg << endl ;
   }
} ;

void iter1() {
   cout << "iter1()" << endl ;
   
   // List of Strings, Find Empty
   string s1 = string("abc") ;
   string s2 = string("xyz") ;
   string s3 = string("xyz") ;
   vector<string> strs ;
   strs.push_back( s1 ) ;
   strs.push_back( s2 ) ;
   strs.push_back( s3 ) ;
   typedef vector<string>::const_iterator ITER ;
   ITER p = find_if( strs.begin(), strs.end(), mem_fun_ref( &string::empty ) ) ;
   if ( p != strs.end() )
      cout << "Found Empty String! (size:" << p->length() << ") " << endl ;
   else
      cout << "Empty String Not Found!" << endl ;
   
}

void iter2() {
   cout << "iter2()" << endl ;

   // Hello Mem Fun Test
   Hello h1("Message 1") ;
   Hello h2("Message 2") ;
   Hello h3("Message 3") ;
   vector<Hello*> hellolist ;
   hellolist.push_back( &h1 ) ;
   hellolist.push_back( &h2 ) ;
   hellolist.push_back( &h3 ) ;
   for_each( hellolist.begin(), hellolist.end(), mem_fun(&Hello::sayHello) ) ;
   
}

// Distance Template.  If In is random access, can just take (last-first).
// However, if not, must walk iterator from first to last to count the distance
template <class In>
typename iterator_traits<In>::difference_type MyDistance( In first, In last ) ;

// We could use helper functions, operator overloading, and iterator tags
template <class In>
typename iterator_traits<In>::difference_type
MyDistance_helper( In first, In last, input_iterator_tag )
{ cout << "Not A Random Access Container... walking the list to count." << endl ;
  typename iterator_traits<In>::difference_type d = 0 ;
  while( first++ != last) d++ ;
  return d ;
}

template <class In>
typename iterator_traits<In>::difference_type
MyDistance_helper( In first, In last, random_access_iterator_tag )
{ cout << "A Random Access Container..." << endl ;
  return last-first ;
}

template <class In>
typename iterator_traits<In>::difference_type MyDistance( In first, In last ) 
{
  return MyDistance_helper( first, last, 
         iterator_traits<In>::iterator_category()) ;
}


void iter3() {
   cout << "iter3()" << endl ;

   Hello h1("Message 1") ;
   Hello h2("Message 2") ;
   Hello h3("Message 3") ;

   list<Hello*> hellolist ;
   hellolist.push_back( &h1 ) ;
   hellolist.push_back( &h2 ) ;
   hellolist.push_back( &h3 ) ;
   
   vector<Hello*> hellovec ;
   hellovec.push_back( &h1 ) ;
   hellovec.push_back( &h2 ) ;
   hellovec.push_back( &h3 ) ;   
   
   cout << "LIST: " << MyDistance( hellolist.begin(), hellolist.end() ) << endl ;
   cout << "VECTOR: " << MyDistance( hellovec.begin(), hellovec.end() ) << endl ;
   
}

void iter4()
{     
   cout << "iter4()" << endl ;
   
  // Stream Iterator Wrappers
  ostream_iterator<int> oi(cout) ;
  
  list<int> lotteryNumbers ;
  lotteryNumbers.push_back( 10 ) ;
  lotteryNumbers.push_back( 20 ) ;
  lotteryNumbers.push_back( 30 ) ;
  
  copy( lotteryNumbers.begin(), 
        lotteryNumbers.end(),
        oi ) ;
        
  /* Copy Template:  
     template < typename I, typename O >
     void copy( I start, O end, O to )
     {  for ( I p=start; I end; O to )
        *to = *p ;
        to++ ;
     }  
  */

  cout << endl ;
  
  copy( istream_iterator<int>(cin),
        istream_iterator<int>(), lotteryNumbers.begin() ) ;

  copy( lotteryNumbers.begin(), 
        lotteryNumbers.end(),
        oi ) ;

}

int main() {
    iter1() ;
    iter2() ;
    iter3() ;
    iter4() ;
}

