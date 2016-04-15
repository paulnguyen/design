
import java.lang.reflect.* ;


public class GumballMachine
{

    private int cnt = 0;
    private int coinValue = 0 ;

    public GumballMachine(int count)
    {
        this.cnt = count ;
    }

    public void insertCoin( int value )
    {
        if ( coinValue < 50 ) {
            coinValue += value ;
        }
        else {
            System.out.println( "There is already enough coins in the slot" ) ;
        }            
    }

    public void turnTheCrank()
    {
        if ( coinValue == 50 ) {
            coinValue = 0 ;
            if ( cnt > 0 ) {
                System.out.println( "Here is your Gumball!  Enjoy!" ) ;
                cnt-- ;
            }
            else {
                System.out.println( "Sorry!  We're out of Gumballs!" ) ;
            }
        }
        else {
            System.out.println( "Crank will not turn without enoush Coins!" ) ;
        }              

    }
  
	// Main Class - Dump Metadata
	public static void main( String args[ ] )
	{
		System.out.println( "***** Class Bytecode Dump *****" ) ;
		GumballMachine m = new GumballMachine(10) ;
		Class gmClass = m.getClass() ;
		Method gmMethods[] = gmClass.getMethods() ;
		for ( int i=0; i <gmMethods.length; i++ )
		{
			Method theMethod = gmMethods[i] ;
			String method = theMethod.toString() ;
			System.out.println( method ) ;
		}
		
	}  

}
