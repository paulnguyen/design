public class BankAccountMonitorThread extends Thread
{
    private BankAccountMonitor theAcct ;
    private double transactionAmount ;
    
    public BankAccountMonitorThread(BankAccountMonitor acct, double transAmt)
    {
        this.theAcct = acct ;
        this.transactionAmount = transAmt ;
    }

    public void run()
    {     
        try {
            sleep(1000) ; // sleep for 1 second
            double bal = theAcct.Withdraw( transactionAmount ) ;
            System.out.println( Thread.currentThread().getName() + " DONE! [current balance = "+bal+"]" ) ;
        } catch ( Exception e )
        {
            System.out.println( e.getMessage() ) ;   
        }
    }
}
