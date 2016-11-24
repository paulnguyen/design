package model;


public interface IModel
{
    void attachObserver( IObserver o ) ;
    void detachObserver( IObserver o ) ;
    void notifyObservers() ;
    void setData( String data ) ;
    String getData() ;
}
