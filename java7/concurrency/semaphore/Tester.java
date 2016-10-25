public class Tester
{
    public static void semaphoreTest()
    {
        Semaphore sem = new Semaphore(2) ;
        ThreadTest T1 = new ThreadTest("T1",sem) ;
        ThreadTest T2 = new ThreadTest("T2",sem) ;
        ThreadTest T3 = new ThreadTest("T3",sem) ;
        ThreadTest T4 = new ThreadTest("T4",sem) ;
        T1.start() ;
        T2.start() ;
        T3.start() ;
        T4.start() ;
    }

    public static void javaSempahoreTest()
    {
        java.util.concurrent.Semaphore sem = new java.util.concurrent.Semaphore(2) ;
        ThreadTestJavaSemaphore T1 = new ThreadTestJavaSemaphore("T1",sem) ;
        ThreadTestJavaSemaphore T2 = new ThreadTestJavaSemaphore("T2",sem) ;
        ThreadTestJavaSemaphore T3 = new ThreadTestJavaSemaphore("T3",sem) ;
        ThreadTestJavaSemaphore T4 = new ThreadTestJavaSemaphore("T4",sem) ;
        T1.start() ;
        T2.start() ;
        T3.start() ;
        T4.start() ;
    }   

    public static void javaUnfairSempahoreTest()
    {
        java.util.concurrent.Semaphore sem = new java.util.concurrent.Semaphore(2,true) ;
        // permits - the initial number of permits available. This value may be negative, 
        //           in which case releases must occur before any acquires will be granted.
        // fair    - true if this semaphore will guarantee first-in first-out granting of 
        //           permits under contention, else false
        ThreadTestJavaSemaphore T1 = new ThreadTestJavaSemaphore("T1",sem) ;
        ThreadTestJavaSemaphore T2 = new ThreadTestJavaSemaphore("T2",sem) ;
        ThreadTestJavaSemaphore T3 = new ThreadTestJavaSemaphore("T3",sem) ;
        ThreadTestJavaSemaphore T4 = new ThreadTestJavaSemaphore("T4",sem) ;
        T1.start() ;
        T2.start() ;
        T3.start() ;
        T4.start() ;
    }   
    
       
    public static void boundedBufferTest()
    {
        BoundedBuffer buffer = new BoundedBuffer(10) ;
        ProducerThread P1 = new ProducerThread( "P1", buffer ) ;
        ConsumerThread C1 = new ConsumerThread( "C1", buffer ) ;
        ConsumerThread C2 = new ConsumerThread( "C2", buffer ) ;
        P1.start() ;
        C1.start() ;
        C2.start() ;
    }


    public static void main( String[] args )
    {
        if ( args.length < 1 ) {
            System.out.println( "java Tester <test> " ) ;
            System.out.println( "<test> = semaphore | java-semaphore | java-unfair-semaphore | bounded-buffer" ) ;
        }
        else {
            if ( args[0].equals("semaphore") )
                Tester.semaphoreTest() ;
            else if( args[0].equals("java-semaphore") )
                Tester.javaSempahoreTest() ;
            else if( args[0].equals("java-unfair-semaphore") )
                Tester.javaUnfairSempahoreTest() ;
            else if( args[0].equals("bounded-buffer") )
                Tester.boundedBufferTest() ;
        }
    }
    
}






