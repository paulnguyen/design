
public interface State {

  public void insertQuarter();
  public void ejectQuarter();
  public void turnCrank();
  public void dispense();
  
}


public class HasQuarterState implements State {
  GumballMachine gumballMachine;

  public HasQuarterState(GumballMachine gumballMachine) {
    this.gumballMachine = gumballMachine;
  }

  public void insertQuarter() {
    String msg = "You can't insert another quarter" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
  }

  public void ejectQuarter() {
    String msg = "Quarter returned" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
    gumballMachine.setState(gumballMachine.getNoQuarterState());
  }

  public void turnCrank() {
    String msg = "You turned the crank..." ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
    gumballMachine.setState(gumballMachine.getSoldState());
  }

  public void dispense() {
    String msg = "No gumball dispensed" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
  }

  public String toString() {
    return "waiting for turn of crank";
  }
}

public class NoQuarterState implements State {
  GumballMachine gumballMachine;

  public NoQuarterState(GumballMachine gumballMachine) {
    this.gumballMachine = gumballMachine;
  }

  public void insertQuarter() {
    String msg = "You inserted a quarter" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
    gumballMachine.setState(gumballMachine.getHasQuarterState());
  }

  public void ejectQuarter() {
    String msg = "You haven't inserted a quarter" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
  }

  public void turnCrank() {
    String msg = "You turned, but there's no quarter" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
  }

  public void dispense() {
    String msg = "You need to pay first" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
  } 

  public String toString() {
    return "waiting for quarter";
  }
}

public class SoldState implements State {
 
    GumballMachine gumballMachine;
 
    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
       
	public void insertQuarter() {
    String msg = "Please wait, we're already giving you a gumball" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public void ejectQuarter() {
    String msg = "Sorry, you already turned the crank" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public void turnCrank() {
    String msg = "Turning twice doesn't get you another gumball!" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public void dispense() {
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
      gumballMachine.releaseBall();
		} else {
      String msg = "Oops, out of gumballs!" ;
      gumballMachine.logEventStatus(msg) ;
      System.out.println(msg);
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}
 
	public String toString() {
		return "dispensing a gumball";
	}
}

public class SoldOutState implements State {
    GumballMachine gumballMachine;
 
    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
 
	public void insertQuarter() {
    String msg = "You can't insert a quarter, the machine is sold out" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public void ejectQuarter() {
    String msg = "You can't eject, you haven't inserted a quarter yet" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public void turnCrank() {
    String msg = "You turned, but there are no gumballs" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public void dispense() {
    String msg = "No gumball dispensed" ;
    gumballMachine.logEventStatus(msg) ;
    System.out.println(msg);
	}
 
	public String toString() {
		return "sold out";
	}
}