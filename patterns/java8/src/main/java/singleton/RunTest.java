
package singleton ;

public class RunTest {
    
    public static void main( String [] args )
    {
        Factory instance1 = Factory.getFactory() ;
        System.out.println( "Instance #1: " + instance1.toString() ) ;
        Factory instance2 = Factory.getFactory() ;
        System.out.println( "Instance #2: " + instance2.toString() ) ;
    }

}
