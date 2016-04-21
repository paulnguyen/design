#include <stl.h>
#include <iostream>
#include <vector>
#include <string>

// Implement Swapper.
// Input Binary Function f(x,y) 
// Output Binary Function Class f(y,x)
// Example Usage:  
// Swapper<pointer_to_binary_function<double, double, double>>(ptr_fun(pow))(x,y)
// Computes Y^X

template < typename B >
class Swapper : public binary_function 
      < typename B::first_argument_type,
        typename B::second_argument_type,
        typename B::result_type
      >
{     
public:
       Swapper( B b ) : _b( b ) {}
       typename B::result_type operator()(
                const typename B::first_argument_type& x, 
                const typename B::second_argument_type& y ) const
       {
        return _b( y, x ) ;
       }        
private:
        B _b ;       
} ;

template < typename B >
Swapper<B> make_swapper( const B& b ) 
{
  return Swapper<B>(b) ;
}

double div_func( double x, double y )
{ return x / y ; }

void test1()
{
 cout << "30/10 => div_func(30,10) => " << div_func(30,10) << endl ;
 cout << "make_swapper( ptr_fun(div_func) )(30,10) => " << make_swapper( ptr_fun(div_func) )(30,10) << endl ;
}

template< typename In, typename Out>
Out rcopy( In first, In last, Out res )
{
 return copy( reverse_iterator<In>(first), reverse_iterator<In>(last), res ) ;  
}


void test2()
{
 vector<double> v ;
 v.push_back(1.0) ;
 v.push_back(2.0) ;
 v.push_back(3.0) ;

 cout << v[0] << endl ;
 cout << v[1] << endl ;
 cout << v[2] << endl ;

 transform( v.begin(), v.end(), v.begin(), bind2nd( multiplies<double>(), 2.0 ) ) ;
 
 cout << v[0] << endl ;
 cout << v[1] << endl ;
 cout << v[2] << endl ;

 ostream_iterator<double> oi(cout) ;
 rcopy( v.rbegin(), v.rend(), oi) ; 

 cout << endl ;
}


int main()
{
 //test1() ;
 test2() ;
}
