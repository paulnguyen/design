package controller;

import model.Model ;
import view.View ;

public interface IController
{
    void initialize( Model m, View v ) ;  
    void setInput( String msg ) ;
  
}
