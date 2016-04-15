import java.util.* ;

public class TestList {


public static void main( String[] args )
{  
	testLinkedLists() ;
	testArrays() ;
}

public static void testLinkedLists()
{
	LinkedList myList = new LinkedList() ;

	myList.add( "Hello" ) ;
	myList.add( "Java" ) ;
	myList.add( "Generics" ) ;
	myList.add( "World!" ) ;	

	System.out.println( myList ) ;	
}


public static void testArrays()
{  

   ArrayList myList = new ArrayList() ;
   myList.add( "String 1" ) ;
   myList.add( "String 2" ) ;
   myList.add( "String 3" ) ;
   myList.add( "String 4" ) ;
   myList.add( "String 5" ) ;
   myList.add( "String 6" ) ;
   myList.add( "String 7" ) ;
   List subList = myList.subList( 1, 5 ) ; // 1st View
   List subSubList = subList.subList( 2, 4 ) ; // View of a View
   ListIterator myIter = myList.listIterator() ;
   Iterator subIter = subList.iterator() ;
   System.out.println( subList.getClass() ) ;
   System.out.println( myList.getClass() ) ;
   System.out.println( "myList: " + myList ) ;
   System.out.println( "subList: " + subList ) ;
   System.out.println( "subSubList: " + subSubList ) ;
   System.out.println( "First myIter: " + myIter.next() ) ;
   System.out.println( "First subIter: " + subIter.next() ) ;

   // non-structural modifications - permitted, does not invalidate views
   myList.set( 6, "myList Modified This." ) ;
   subList.set( 1, "subList Modified This." ) ;
   subSubList.set( 1, "SubSubList Modified This." ) ;
   myIter.set( "myIter Modified This." ) ;
   System.out.println( "myList: " + myList ) ;
   System.out.println( "subList: " + subList ) ;
   System.out.println( "subSubList: " + subSubList ) ;
   System.out.println( "myIter: " + myIter ) ;
   System.out.println( "subIter: " + subIter ) ;

   // structural modifications
   System.out.println( "\n\n" ) ;
   if ( false ) {
      System.out.println( "Structural Change via subList..." ) ;     
      subList.add( "Added by subList 1" ) ; 
      subList.add( "Added by subList 2" ) ; 
      subList.add( "Added by subList 3" ) ; 
      /*
      Structural Change via subList...
      
      subList: [String 2, subList Modified This., String 4, SubSubList Modified This., Added by subList 1, Added by subList 2, Added by su
      bList 3]
      ERROR accessing myIter: java.util.ConcurrentModificationException
      ERROR accessing subIter: java.util.ConcurrentModificationException
      ERROR accessing subSubList: java.util.ConcurrentModificationException
      myList: [myIter Modified This., String 2, subList Modified This., String 4, SubSubList Modified This., Added by subList 1, Added by
      subList 2, Added by subList 3, String 6, myList Modified This.]
      */
   }
   if ( false ) {
      System.out.println( "Structural Change via subSubList..." ) ; // why is subList still not invalid?    
      subSubList.add( "Added by subSubList 1" ) ; 
      subSubList.add( "Added by subSubList 2" ) ; 
      subSubList.add( "Added by subSubList 3" ) ; 
      /*
      Structural Change via subSubList...
      
      subList: [String 2, subList Modified This., String 4, SubSubList Modified This., Added by subSubList 1, Added by subSubList 2, Added
      by subSubList 3]
      ERROR accessing myIter: java.util.ConcurrentModificationException
      ERROR accessing subIter: java.util.ConcurrentModificationException
      subSubList: [String 4, SubSubList Modified This., Added by subSubList 1, Added by subSubList 2, Added by subSubList 3]
      myList: [myIter Modified This., String 2, subList Modified This., String 4, SubSubList Modified This., Added by subSubList 1, Added
      by subSubList 2, Added by subSubList 3, String 6, myList Modified This.]
      */
   }
   if ( false ) {
      System.out.println( "Structural Change via myList..." ) ;
      myList.add( "Added by myList 1" ) ; 
      myList.add( "Added by myList 2" ) ; 
      myList.add( "Added by myList 3" ) ; 
      /*
      Structural Change via myList...
      
      ERROR accessing subList: java.util.ConcurrentModificationException
      ERROR accessing myIter: java.util.ConcurrentModificationException
      ERROR accessing subIter: java.util.ConcurrentModificationException
      ERROR accessing subSubList: java.util.ConcurrentModificationException
      myList: [myIter Modified This., String 2, subList Modified This., String 4, SubSubList Modified This., String 6, myList Modified Thi
      s., Added by myList 1, Added by myList 2, Added by myList 3]
      */
   }
   if ( false ) {
      System.out.println( "Structural Change via myIter..." ) ;      
      myIter.add( "Added by myIter 1" ) ; 
      myIter.add( "Added by myIter 2" ) ; 
      myIter.add( "Added by myIter 3" ) ;
      /*
      Structural Change via myIter...
      
      ERROR accessing subList: java.util.ConcurrentModificationException
      Second myIter: String 2
      ERROR accessing subIter: java.util.ConcurrentModificationException
      ERROR accessing subSubList: java.util.ConcurrentModificationException
      myList: [myIter Modified This., Added by myIter 1, Added by myIter 2, Added by myIter 3, String 2, subList Modified This., String 4,
      SubSubList Modified This., String 6, myList Modified This.]
      */ 
   }
   if ( false ) {
      System.out.println( "Structural Change via subIter..." ) ;     
      subIter.remove() ; 
      /*
      Structural Change via subIter...
      
      subList: [subList Modified This., String 4, SubSubList Modified This.]
      ERROR accessing myIter: java.util.ConcurrentModificationException
      Second subIter: subList Modified This.
      ERROR accessing subSubList: java.util.ConcurrentModificationException
      myList: [myIter Modified This., subList Modified This., String 4, SubSubList Modified This., String 6, myList Modified This.]
      */
   }
   

   // What's affected by structural modifications  
   System.out.println( "\n\n" ) ;
   try { 
      System.out.println( "subList: " + subList ) ; // valid! 
   }
   catch ( Exception e ) { 
      System.out.println( "ERROR accessing subList: " + e ) ; // Exception Thrown: java.util.ConcurrentModificationException
   }  
   try { 
      System.out.println( "Second myIter: " + myIter.next() ) ; // should get exception
   }
   catch ( Exception e ) { 
      System.out.println( "ERROR accessing myIter: " + e ) ; // Exception Thrown: java.util.ConcurrentModificationException
   }  
   try { 
      System.out.println( "Second subIter: " + subIter.next() ) ; // should get exception
   }
   catch ( Exception e ) { 
      System.out.println( "ERROR accessing subIter: " + e ) ; // Exception Thrown: java.util.ConcurrentModificationException
   }     
   try { 
      System.out.println( "subSubList: " + subSubList ) ; // should get exception
   }
   catch ( Exception e ) { 
      System.out.println( "ERROR accessing subSubList: " + e ) ; // Exception Thrown: java.util.ConcurrentModificationException
   }  
   try { 
      System.out.println( "myList: " + myList ) ;  // seems to be valid! Only Views & Iters Invalidated! 
   } 
   catch ( Exception e ) { 
      System.out.println( "ERROR accessing myList: " + e ) ;
   }  

}

}
