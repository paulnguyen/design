
import java.util.* ;

class C1 {}
interface I1 {}
interface I2 {}
class noBounds<A> { }
class singleBound<A extends C1> { }

/* There seems to be a bug in GJ compiler
not recognizing implments clause...
class multipleBounds<A implements I1>{ 
	public int foo( A obj )
	{	
		return obj.methodA() ;
	}
}
*/

class Cell<A> {
	A value ;
	Cell ( A v ) { 
		value = v ; 
	}
	A get() { return value ; }
	void set( A v ) { 
		value = v ; 
	}
}

// Run Some Misc. Test Cases
class GenericJava
{

	public static void main( String[] args ) 
	{
		// all parameterized types share same run-time class
		Vector<String> x = new Vector<String>() ;
		Vector<Integer> y = new Vector<Integer>() ;
		System.out.println( "Parameterized types share same run-time class: " + 
			(x.getClass() == x.getClass()) ) ; // true	

		// Uncheck Warnings
		Cell x1 = new Cell<String>("abc") ;		
		System.out.println( x1.value ) ;
		System.out.println( x1.get() ) ;		
		x1.set( "def" ) ; // Warning. Erasure changed argument type to Object
		/*
			GenericJava.java:42: warning: unchecked call to set(A) as a member of the raw type Cell
                x1.set( "def" ) ;
		*/
	}	

}
