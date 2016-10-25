
import java.util.concurrent.locks.ReentrantLock ;

public class Tester
{ 
   public static void lockTest()
    {
        Lock theLock = new Lock() ;
        LockTestThread T1 = new LockTestThread(theLock) ;
        LockTestThread T2 = new LockTestThread(theLock) ;
        T1.start() ;
        T2.start() ;
    }

    public static void reentrantLockTest()
    {
        ReentrantLock theLock = new ReentrantLock() ;
        JavaLockTestThread T1 = new JavaLockTestThread(theLock) ;
        JavaLockTestThread T2 = new JavaLockTestThread(theLock) ;
        T1.start() ;
        T2.start() ;
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

    public static void bankMonitorTest()
    {
        BankAccountMonitor acct = new BankAccountMonitor() ;
        try { acct.Deposit( 1000.00 ) ; } catch (Exception e) {} ;
        BankAccountMonitorThread T1 = new BankAccountMonitorThread( acct, 50.00 ) ;
        BankAccountMonitorThread T2 = new BankAccountMonitorThread( acct, 100.00 ) ;
        BankAccountMonitorThread T3 = new BankAccountMonitorThread( acct, 150.00 ) ;
        BankAccountMonitorThread T4 = new BankAccountMonitorThread( acct, 200.00 ) ;
        T1.start() ;
        T2.start() ;
        T3.start() ;
        T4.start() ;        
    }
 
   public static void bankJavaMonitorTest()
    {
        BankAccountJavaMonitor acct = new BankAccountJavaMonitor() ;
        try { acct.Deposit( 1000.00 ) ; } catch (Exception e) {} ;
        BankAccountJavaMonitorThread T1 = new BankAccountJavaMonitorThread( acct, 50.00 ) ;
        BankAccountJavaMonitorThread T2 = new BankAccountJavaMonitorThread( acct, 100.00 ) ;
        BankAccountJavaMonitorThread T3 = new BankAccountJavaMonitorThread( acct, 150.00 ) ;
        BankAccountJavaMonitorThread T4 = new BankAccountJavaMonitorThread( acct, 200.00 ) ;
        T1.start() ;
        T2.start() ;
        T3.start() ;
        T4.start() ;
    }

    public static void main( String[] args )
    {
        if ( args.length < 1 ) {
            System.out.println( "java Tester <test> " ) ;
            System.out.println( "<test> = lock | reentrant-lock | bounded-buffer | bank-monitor | bank-java-monitor" ) ;
        }
        else {
            if ( args[0].equals("lock") )
                Tester.lockTest() ;
            else if( args[0].equals("reentrant-lock") )
                Tester.reentrantLockTest() ;
            else if( args[0].equals("bounded-buffer") )
                Tester.boundedBufferTest() ;
            else if( args[0].equals("bank-monitor") )
                Tester.bankMonitorTest() ;
            else if( args[0].equals("bank-java-monitor") )
                Tester.bankJavaMonitorTest() ;
        }
    }
    

    
}