
public class HelloThreads {

  private static Thread mainThread ;

  public static void main(String[] args) throws InterruptedException {

    Thread t1 = Thread.currentThread() ;

    Thread t2 = new Thread() {
        public void run() {
          System.out.println( "X: [T1] " + t1.getState() + " [T2] " + Thread.currentThread().getState() ) ;
          try { Thread.sleep(2000); } catch ( InterruptedException e ) { }
          System.out.println( "Y: [T1] " + t1.getState() + " [T2] " + Thread.currentThread().getState() ) ;
          System.out.println("==> 2");
          try { Thread.sleep(4000); } catch ( InterruptedException e ) { }
          System.out.println( "Z: [T1] " + t1.getState() + " [T2] " + Thread.currentThread().getState() ) ;
          try { Thread.sleep(4000); } catch ( InterruptedException e ) { }
          System.out.println("==> 3");
        }
      };
	  
    t2.start();
    System.out.println( "A: [T1] " + t1.getState() + " [T2] " + t2.getState() ) ;
    Thread.yield();
    System.out.println( "B: [T1] " + t1.getState() + " [T2] " + t2.getState() ) ;
    Thread.sleep(1000);
    System.out.println( "C: [T1] " + t1.getState() + " [T2] " + t2.getState() ) ;
    System.out.println( "==> 1" ) ;
    t2.join();
    System.out.println( "D: [T1] " + t1.getState() + " [T2] " + t2.getState() ) ;
    System.out.println( "==> 4" ) ;
  }
}
