#include <stl.h>

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


template <class Op1, class Op2, class Op3>
class compose
  : public unary_function<typename Op2::argument_type,
                          typename Op1::result_type> {
protected:
 Op1 op1;
 Op2 op2;
 Op3 op3;
public:
  compose(const Op1& x, const Op2& y, const Op3& z )
      : op1(x), op2(y), op3(z) {}
  typename Op1::result_type operator()(const typename Op2::argument_type& x)
   const {
    return op1(op2(x), op3(x));
   }
};


/*
template <class Op1, class Op2, class Op3>
class compose
{
protected:
 Op1 op1;
 Op2 op2;
 Op3 op3;
public:
  compose(const Op1& x, const Op2& y, const Op3& z )
      : op1(x), op2(y), op3(z) {}
  bool operator()(const Op2& x)
   const {
    return op1(op2(x), op3(x));
   }
};
*/

void find_bind4() {
   cout << "Count How Many < 10 and > 5: " ;
   int a[8] = { 35, 7, 52, 64, 6, 19, 4, 22 } ;
   int cnt =  count_if(
         a,
         a+8,
         compose ( logical_and<bool>(),
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
