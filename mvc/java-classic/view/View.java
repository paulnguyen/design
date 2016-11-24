
package view;

import model.IObserver ;
import model.IModel ;

public class View implements IObserver
{
    private IModel model ;
    private String output ;
    
    public View()
    {
        model = null ;
    }
    
    public void initialize( IModel m )
    {
        model = m ;
    }

    public void update( ) 
    {
        output = model.getData() ;
    }
    
    public void display( )
    {
        System.out.println( this.output ) ;
    }
    
    
}
