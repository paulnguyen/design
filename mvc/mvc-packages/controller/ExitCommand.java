package controller;


public class ExitCommand implements IActionCommand
{
    IActionListener target ;
    
    public void execute() 
    {
        target.actionEvent( Constants.QUIT ) ;
    }
    
    public void setReceiver( IActionListener t ) 
    {
        target = t ;
    }
}

