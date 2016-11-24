package view;

import model.IModel ;

public interface IView
{
    void initialize( IModel m ) ;
    void update( ) ;
    void display( ) ; 
}
