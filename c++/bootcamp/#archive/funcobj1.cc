#include <iostream>
using namespace std;

// STL FIND by value
void find_arr() {
   cout << "First = 5: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   int* result =  find( a, a+5, 5 ) ;
   cout << *result << "\n" ;
}

// STL FIND by predicate via function pointer
bool pred( int val ) {
   return val > 4 ;
}

void find_pred1() {
   cout << "First > 4: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   int* result =  find_if( a, a+5, pred ) ;
   cout << *result << "\n" ;
}

// STL FIND by predicate via function class
class pred_class {
public:
   bool operator() ( int val ) {
      return val > 3 ;
   }
};

void find_pred2() {
   cout << "First > 3: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   pred_class fc ;
   int* result =  find_if( a, a+5, fc ) ;
   cout << *result << "\n" ;
}

// STL FIND by predicate via function object
// (an object which maintains state)
class pred_gt {
public:
   pred_gt( int gt_val ) { gt = gt_val ; }
   bool operator() ( int val ) {
      return val > gt ;
   }
private:
   int gt ;
} ;

void find_pred3() {
   cout << "First > 2: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   pred_gt gt2(2) ;
   int* result =  find_if( a, a+5, gt2 ) ;
   cout << *result << "\n" ;
}

// STL FOR EACH with sum function object via Template
template<class T> class Sum {
public:
   Sum( T i = 0 ) { res = 0 ; }
   void operator() (T x) { res += x ; }
   T result() const { return res ; }
private:
   T res ;
} ;

void for_each_sum() {
   cout << "SUM 1-5: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   Sum<int> s ;
   s =  for_each( a, a+5, s ) ;
   cout << s.result()<< "\n" ;
}

// STL FOR EACH with binders
template <class T> class greater_than : public unary_function<T, bool> {
public:
   explicit greater_than( const T& x ) : arg2(x) {} ;
   bool operator() ( T& x ) const { return x > arg2 ; } ;
private:
   T arg2 ;
} ;

void find_bind1() {
   cout << "First > 2: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   int* result =  find_if( a, a+5, greater_than<int>(2) ) ;
   cout << *result << "\n" ;
}

void find_bind2() {
   cout << "First > 4: " ;
   int a[5] = { 1, 2, 3 ,4 ,5 } ;
   int* result =  find_if( a, a+5, bind2nd(greater<int>(), 4) ) ;
   cout << *result << "\n" ;
}

void find_bind3() {
   cout << "Count How Many < 10: " ;
   int a[8] = { 35, 7, 52, 64, 6, 19, 4, 22 } ;
   int cnt =  count_if( a, a+8, bind2nd(less<int>(), 10) ) ;
   cout << cnt << "\n" ;
}

/**
   Class Template which implements compose(b,f,g) -> b( f(x), g(y) )
   @param   BinaryF1 Binary Function b(x,y)
   @param   UnaryF2 Unary Function f(x)
   @param   UnaryF3 Unary Function g(x)
*/
template <class BinaryF1, class UnaryF2, class UnaryF3>
class composer2 : public binary_function< typename UnaryF2::argument_type,
                                          typename UnaryF3::argument_type,
                                          typename BinaryF1::result_type >
{
public:
   composer2(  const BinaryF1& f1,
               const UnaryF2& f2,
               const UnaryF3& f3 ) : _f1(f1), _f2(f2), _f3(f3) {}
   inline typename BinaryF1::result_type
      operator()( const typename UnaryF2::argument_type &x,
                  const typename UnaryF3::argument_type &y
                 )
   const
   {
      return _f1( _f2(x), _f3(y) ) ;
   }
private:
   BinaryF1   _f1 ;
   UnaryF2    _f2 ;
   UnaryF3    _f3 ;
} ;

/**
   compose(b,f,g) template function which constructs a composer2 function object
   @param   f1 A Binary Function
   @param   f2 A Unary Function
   @param   f3 A Unary Function
   @return  a composer2( f1, f2, f3 ) function object
*/
template <typename BinaryF1, typename UnaryF2, typename UnaryF3 >
   inline composer2<BinaryF1, UnaryF2, UnaryF3>
      compose( const BinaryF1& f1, const UnaryF2& f2, const UnaryF3& f3 ) {
         return composer2<BinaryF1, UnaryF2, UnaryF3>( f1, f2, f3 ) ;
      }


void find_bind4() {
   cout << "Count How Many < 10 and > 5: " ;
   int a[8] = { 35, 7, 52, 64, 6, 19, 4, 22 } ;
   int cnt =  count_if(
         a,
         a+8,
         compose2 ( logical_and<bool>(),
                  bind2nd(less<int>(), 10),
                  bind1st(less<int>(), 5)
                  )
   ) ;
   cout << cnt << "\n" ;
}




// Main Routine
int main() {
   find_arr() ;
   find_pred1() ;
   find_pred2() ;
   find_pred3() ;
   for_each_sum() ;
   find_bind1() ;
   find_bind2() ;
   find_bind3() ;
   find_bind4() ;
}


/*
F:\PROJECTS\SJSU.MSCS\F01.CS.252\study>funcobj.exe
First = 5: 5
First > 4: 5
First > 3: 4
First > 2: 3
SUM 1-5: 15
First > 2: 3
First > 4: 5
Count How Many < 10: 3
Count How Many < 10 and > 5: 2
*/
