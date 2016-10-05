
public class GumballMachine {

  private String lastEventStatus = "" ;
  
  private State soldOutState;
  private State noQuarterState;
  private State hasQuarterState;
  private State soldState;

  private State state = soldOutState;
  private int count = 0;

  public GumballMachine(int numberGumballs) {
    soldOutState = new SoldOutState(this);
    noQuarterState = new NoQuarterState(this);
    hasQuarterState = new HasQuarterState(this);
    soldState = new SoldState(this);

    this.count = numberGumballs;
    if (numberGumballs > 0) {
      state = noQuarterState;
    }
  }

  private void resetEventStatus() {
    lastEventStatus = "" ; 
  }
  
  public void logEventStatus( String msg ) {
    lastEventStatus += msg + "\n" ; 
  }  

  public void insertQuarter() {
    resetEventStatus();
    state.insertQuarter();
  }

  public void ejectQuarter() {
    resetEventStatus();
    state.ejectQuarter();
  }

  public void turnCrank() {
    resetEventStatus();
    state.turnCrank();
    state.dispense();
  }

  void setState(State state) {
    this.state = state;
  }

  void releaseBall() {
    String msg = "A gumball comes rolling out the slot..." ;
    System.out.println(msg);
    logEventStatus(msg);
    if (count != 0) {
      count = count - 1;
    }
  }

  int getCount() {
    return count;
  }

  void refill(int count) {
    this.count = count;
    state = noQuarterState;
  }

  public State getState() {
    return state;
  }

  public State getSoldOutState() {
    return soldOutState;
  }

  public State getNoQuarterState() {
    return noQuarterState;
  }

  public State getHasQuarterState() {
    return hasQuarterState;
  }

  public State getSoldState() {
    return soldState;
  }

  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("\nMighty Gumball, Inc.");
    result.append("\nJava-enabled Standing Gumball Model #2004");
    result.append("\nInventory: " + count + " gumball");
    if (count != 1) {
      result.append("s");
    }
    result.append("\n");
    result.append("Machine is " + state + "\n");
    result.append("\n");
    result.append(lastEventStatus);
    return result.toString();
  }

}