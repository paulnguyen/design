
import java.util.concurrent.locks.ReentrantLock ;

public class Tester
{     
    
    public static void runRobotTest1() throws Exception
    {
        Robot rbt = new Robot() ;
        RobotCommandThread order = new RobotCommandThread(rbt) ;
        RobotHumanInDangerEventThread savehuman = new RobotHumanInDangerEventThread(rbt) ;
        RobotSelfInDangerActionThread saveself = new RobotSelfInDangerActionThread(rbt) ;
        saveself.start();
        Thread.currentThread().sleep(2000);
        order.start();
        Thread.currentThread().sleep(5000);
        savehuman.start();        
    }

    
    public static void runRobotTest2() throws Exception
    {
        Robot rbt = new Robot() ;
        RobotCommandThread order = new RobotCommandThread(rbt) ;
        RobotHumanInDangerEventThread savehuman = new RobotHumanInDangerEventThread(rbt) ;
        RobotSelfInDangerActionThread saveself = new RobotSelfInDangerActionThread(rbt) ;
        saveself.start();
        Thread.currentThread().sleep(2000);
        order.start();
        Thread.currentThread().sleep(15000);
        savehuman.start();        
    }

    public static void main( String[] args )
    {
        if ( args.length < 1 ) {
            System.out.println( "java Tester <test> " ) ;
            System.out.println( "<test> = robot-test-1 | robot-test-2 " ) ;
        }
        else {
            if ( args[0].equals("robot-test-1") )
                try { Tester.runRobotTest1() ; } catch (Exception e) {} ;
            if ( args[0].equals("robot-test-2") )
                try { Tester.runRobotTest2() ; } catch (Exception e) {} ;
        }
    }
    
    
}