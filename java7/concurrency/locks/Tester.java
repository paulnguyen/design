
import java.util.concurrent.locks.ReentrantLock ;

public class Tester
{ 

   public static void badLockTest()
    {
        BadLock theLock = new BadLock() ;
        BadLockTestThread T1 = new BadLockTestThread(theLock) ;
        BadLockTestThread T2 = new BadLockTestThread(theLock) ;
        T1.start() ;
        T2.start() ;
    }

   public static void goodLockTest()
    {
        Lock theLock = new Lock() ;
        LockTestThread T1 = new LockTestThread(theLock) ;
        LockTestThread T2 = new LockTestThread(theLock) ;
        LockTestThread T3 = new LockTestThread(theLock) ;
        T1.start() ;
        T2.start() ;
        T3.start() ;
    }

    public static void reentrantLockTest()
    {
        ReentrantLock theLock = new ReentrantLock() ;
        JavaLockTestThread T1 = new JavaLockTestThread(theLock) ;
        JavaLockTestThread T2 = new JavaLockTestThread(theLock) ;
        T1.start() ;
        T2.start() ;
    }
    
	public static void main( String[] args )
	{
        if ( args.length < 1 ) {
            System.out.println( "java Tester <test> " ) ;
            System.out.println( "<test> = bad-lock | good-lock | reentrant-lock" ) ;
        }
        else {
        	if ( args[0].equals("bad-lock") )
            	Tester.badLockTest() ;
        	else if( args[0].equals("good-lock") )
            	Tester.goodLockTest() ;
        	else if( args[0].equals("reentrant-lock") )
            	Tester.reentrantLockTest() ;
        }
    }
    
}