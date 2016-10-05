

import java.net.* ;
import java.util.* ;
import java.io.* ;

/**
   Defines a single test case running in its own thread.
   Each test case connects to a URL (via HTTP GET) and
   grabs the number of bytes or error condition.
   @version $Revision: 1.4 $ $Date: 2001/09/16 05:51:37 $
*/
public class RunLoadTest {

   /** URL to run the test */
   private String myURL ;  
   /** Request Counter to track number of repeated requests */
   private int reqCount ;  
   /** Group of Threads Running HTTP GETS on List of URLs */
   private ThreadGroup myGroup ;  
   /** Used to Signal a Stop Request to all running threads */
   private static boolean out_of_time = false ;  

   /**
   Constructor which assigns the thread to a group and its source URL.
   @param grp Thread Group
   @param srcURL URL specification
   */
   RunLoadTest( ThreadGroup grp, String srcURL ) 
   {
      this.myURL = srcURL ;
      this.reqCount = 0 ;
      this.myGroup = grp ;    
   }

   /**
   Signal the all threads to stop processing
   */
   public static void setOutOfTime() 
   {
      out_of_time = true ;
   }
   
   /**
   Test for threads to check for stop processing signal
   @return true, if out of time; otherwise, false.
   */
   public static boolean isOutOfTime() 
   {
      return out_of_time ;        
   }
   
   /**
   Creates a thread and runs a test case
   */   
   public void runTest() 
   {
         // anonymous inner class for thread target
         Thread myThread = new Thread ( 
               this.myGroup,
               new Runnable() 
               {
                    public void run() 
                    {
                           while( !LoadTest.isOutOfTime() ) 
                           {
                                 doTest() ;
                                 try { Thread.sleep( 500 ) ; }
                                 catch ( InterruptedException e ) {}
                            }
                    }
               }
         ) ;
      
         myThread.start() ;
   }

   /**
   Performs a test by sending HTTP Gets to the URL,
   and records the number of bytes returned.  Each
   test results are documented and displayed in
   standard out with the following information:
   <br/>
   <ul>
   <li> URL - The source URL of the test </li>
   <li> REQ CNT - How many times this test has run </li>
   <li> START - The start time of the last test run </li>
   <li> STOP - The stop time of the last test run </li>
   <li> THREAD - The thread id handling this test case </li>
   <li> RESULT - The result of the test.  Either the number of bytes returned or an error message. </li>
   </ul>
   */
   private void doTest() 
   {
      String result = "" ;
      this.reqCount++ ;

       // Connect and get number of bytes...
      Date startTime = new Date() ;
      try {
            URL urlSpec = new URL( this.myURL ) ;
            URLConnection urlCon = urlSpec.openConnection() ;
            InputStream is = urlCon.getInputStream() ;
            DataInputStream dis ;
            int cntBytes = 0 ;
            dis = new DataInputStream(new BufferedInputStream(is));
            try {
                  while ( true ) 
                  {
                     byte aByte = dis.readByte() ;
                     cntBytes++ ;
                  }
            } catch ( EOFException e ) { /* End-of-file expected */ }
            is.close() ;
            int len = urlCon.getContentLength() ;
            result = "Bytes Read = " + cntBytes ;
      }
      catch ( java.net.MalformedURLException e ) {
            result = e.toString() ;
      }
      catch ( java.io.IOException e ) {
            result = e.toString() ;
      }
      finally {
         Date stopTime = new Date() ;
         // Print Report of Result:
         System.out.println( "======================================\n" +
            "URL     => " + this.myURL + "\n" +
            "REQ CNT => " + this.reqCount + "\n" +
            "START   => " + startTime + "\n" +
            "STOP    => " + stopTime + "\n" +
            "THREAD  => " + Thread.currentThread().getName() + "\n" +
            "RESULT  => " + result + "\n" ) ;
      }
   }


   /**
   Main Class Routine that reads in URL specs from a file and a duration (in miliseconds)
   of how long to run the test.
   <br/>
   <b>USAGE: java LoadTest [Test File] [Duration in seconds]</b>
   <br/><br/>
   <em>EXAMPLE:</em>
   <pre>

		  java LoadTest urls.txt 5
		  
		  Start Test Run: Fri Aug 31 14:51:41 PDT 2001
		  java.lang.ThreadGroup[name=Test Suite,maxpri=10]
				Thread[Thread-0,5,Test Suite]
				Thread[Thread-1,5,Test Suite]
				Thread[Thread-2,5,Test Suite]
				Thread[Thread-3,5,Test Suite]
				Thread[Thread-4,5,Test Suite]
		  ======================================
		  URL     => http://www.yahoo.com
		  REQ CNT => 1
		  START   => Fri Aug 31 14:51:41 PDT 2001
		  STOP    => Fri Aug 31 14:51:42 PDT 2001
		  THREAD  => Thread-1
		  RESULT  => Bytes Read = 14427
		  
		  ======================================
		  URL     => http://www.sun.com
		  REQ CNT => 1
		  START   => Fri Aug 31 14:51:41 PDT 2001
		  STOP    => Fri Aug 31 14:51:43 PDT 2001
		  THREAD  => Thread-3
		  RESULT  => Bytes Read = 13995
		  
		  ======================================
		  URL     => http://www.oracle.com
		  REQ CNT => 1
		  START   => Fri Aug 31 14:51:41 PDT 2001
		  STOP    => Fri Aug 31 14:51:43 PDT 2001
		  THREAD  => Thread-2
		  RESULT  => Bytes Read = 24745
		  
		  ======================================
		  URL     => http://www.yahoo.com
		  REQ CNT => 2
		  START   => Fri Aug 31 14:51:43 PDT 2001
		  STOP    => Fri Aug 31 14:51:43 PDT 2001
		  THREAD  => Thread-1
		  RESULT  => Bytes Read = 14427
		  
		  ======================================
		  URL     => http://java.sun.com
		  REQ CNT => 1
		  START   => Fri Aug 31 14:51:41 PDT 2001
		  STOP    => Fri Aug 31 14:51:44 PDT 2001
		  THREAD  => Thread-4
		  RESULT  => Bytes Read = 44094
		  
		  ======================================
		  URL     => http://www.sun.com
		  REQ CNT => 2
		  START   => Fri Aug 31 14:51:43 PDT 2001
		  STOP    => Fri Aug 31 14:51:44 PDT 2001
		  THREAD  => Thread-3
		  RESULT  => Bytes Read = 13995
		  
		  ======================================
		  URL     => http://www.yahoo.com
		  REQ CNT => 3
		  START   => Fri Aug 31 14:51:44 PDT 2001
		  STOP    => Fri Aug 31 14:51:44 PDT 2001
		  THREAD  => Thread-1
		  RESULT  => Bytes Read = 14427
		  
		  ======================================
		  URL     => http://www.oracle.com
		  REQ CNT => 2
		  START   => Fri Aug 31 14:51:44 PDT 2001
		  STOP    => Fri Aug 31 14:51:45 PDT 2001
		  THREAD  => Thread-2
		  RESULT  => Bytes Read = 24745
		  
		  ======================================
		  URL     => http://www.sun.com
		  REQ CNT => 3
		  START   => Fri Aug 31 14:51:44 PDT 2001
		  STOP    => Fri Aug 31 14:51:45 PDT 2001
		  THREAD  => Thread-3
		  RESULT  => Bytes Read = 13995
		  
		  ======================================
		  URL     => http://www.yahoo.com
		  REQ CNT => 4
		  START   => Fri Aug 31 14:51:45 PDT 2001
		  STOP    => Fri Aug 31 14:51:46 PDT 2001
		  THREAD  => Thread-1
		  RESULT  => Bytes Read = 14427
		  
		  ======================================
		  URL     => http://java.sun.com
		  REQ CNT => 2
		  START   => Fri Aug 31 14:51:44 PDT 2001
		  STOP    => Fri Aug 31 14:51:46 PDT 2001
		  THREAD  => Thread-4
		  RESULT  => Bytes Read = 44099
		  
		  ======================================
		  URL     => http://www.yahoo.com
		  REQ CNT => 5
		  START   => Fri Aug 31 14:51:46 PDT 2001
		  STOP    => Fri Aug 31 14:51:47 PDT 2001
		  THREAD  => Thread-1
		  RESULT  => Bytes Read = 14427
		  
		  ======================================
		  URL     => http://www.sun.com
		  REQ CNT => 4
		  START   => Fri Aug 31 14:51:46 PDT 2001
		  STOP    => Fri Aug 31 14:51:47 PDT 2001
		  THREAD  => Thread-3
		  RESULT  => Bytes Read = 13995
		  
		  ======================================
		  URL     => http://www.oracle.com
		  REQ CNT => 3
		  START   => Fri Aug 31 14:51:46 PDT 2001
		  STOP    => Fri Aug 31 14:51:47 PDT 2001
		  THREAD  => Thread-2
		  RESULT  => Bytes Read = 24745
		  
		  ======================================
		  URL     => http://slashdot.org/article.pl?sid=01/06/14/1332242
		  REQ CNT => 1
		  START   => Fri Aug 31 14:51:41 PDT 2001
		  STOP    => Fri Aug 31 14:51:49 PDT 2001
		  THREAD  => Thread-0
		  RESULT  => Bytes Read = 67176
		  
		  Stop Test Run: Fri Aug 31 14:51:47 PDT 2001
   </pre>
   */
   public static void main( String[] args ) 
   {
      if ( args.length == 2 ) 
      {
         // Record Start of Test Run
         Date testStart = new Date() ; 
         System.out.println( "Start Test Run: " + testStart.toString() ) ;       

         // Load URLs from test file and run tests
         loadThreads( args[0] ) ;

         // Set Timer to stop testing
         Timer timer = new Timer() ;
         TimerTask task = new TimerTask() 
         {
            public void run() 
            {
               Date testStop = new Date() ;
               LoadTest.setOutOfTime() ;
               try { Thread.sleep(3000); } catch ( Exception e ) {}  // Pause to allow all threads to stop.
               System.out.println( "Stop Test Run: " + testStop.toString() ) ;   
            }
         } ;

         // Schedule Test Run according to the duration given
         long curDate = new Date().getTime() ;
         long milis = Long.parseLong( args[1] ) * 1000 ;
         timer.schedule( task, new Date( curDate + milis) );
      }
      else {
         System.out.println( "USAGE: java LoadTest [Test File[ [Duration in seconds]" ) ;
      }
   }

   /**
   Helper method for main to load list of URLs from a file into a thread group,
   and start each test case.
   @param fileSpec location of file
   */
   private static void loadThreads( String fileSpec ) {              
         // Check if file is valid
         File testFile = new File ( fileSpec ) ;
         if ( !testFile.exists() || !testFile.canRead() ) 
         {
            System.out.println( "ERROR: Can't read test file for list of URLs." ) ;
         }
         else 
         {
            // Open File and read in list of URLs.  Each URL gets a LoadTest object.
            try {
               FileReader fr = new FileReader( testFile ) ;
               BufferedReader in = new BufferedReader( fr ) ;
               ThreadGroup myThreadGroup = new ThreadGroup( "Test Suite" ) ;     
               String urlSpec = null ;  
               while( (urlSpec = in.readLine()) != null ) {
                    LoadTest test = new LoadTest( myThreadGroup, urlSpec ) ;
                    test.runTest() ;  // Run The Test in its own thread
               }
               myThreadGroup.list() ; 
            } catch ( Exception e ) {
               System.out.println( "ERROR: " + e.getMessage() );
            }
         }
   }



}
