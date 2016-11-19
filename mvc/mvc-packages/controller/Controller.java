
package controller ;

import model.* ;
import view.* ;

public abstract class Controller implements IObserver, IActionListener, IKeyListener, IWindowListener, IMouseListener
{

    private Model model ;
    private View view ;
    
    public Controller( )
    {
        model = null ;
        view = null ;
    }

    public void initialize( Model m, View v )
    {   
        this.model = m ;
        this.view = v ;
    }
    
    public void update() 
    {
    }
    
    public void setInput( String msg )
    {
        model.setData( "Hello " + msg ) ;
    }

    public void windowEvent() { }
    public void mouseEvent() { }
    public void keyEvent() { }
    
    public void actionEvent( Constants event ) 
    { 
      if ( event.equals( Constants.QUIT ) )
        System.out.println( "  Goodbye!" ) ;
    }
    
}
