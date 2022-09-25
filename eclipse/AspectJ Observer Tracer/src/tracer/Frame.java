package tracer;

import java.util.*;

public class Frame {
	
	public int level ;
	public String name = "" ;
	public String source = "" ;
	public String target = "" ;
	public String message = "" ;
	public String returns = "" ;
	public String pointcut = "" ;
	public String method = " " ;

	// set pointcut method
	public void setPointCut( String pointcut ) {
		this.pointcut = pointcut ;
		// extract method.  Sample: execution(void ConcreteSubject.setState(String))
		String[] s1 = pointcut.trim().split("\\(") ;			
		System.out.println( "s1: " + Arrays.toString(s1) ) ; 	//  [execution, void ConcreteSubject.setState, String))]
		String[] s2 = s1[1].trim().split(" ") ;
		System.out.println( "s2: " + Arrays.toString(s2) ) ; 	//  [void, ConcreteSubject.setState]
		String[] s3 = s2[1].trim().split("\\.") ;
		System.out.println( "s3: " + Arrays.toString(s3) ) ; 	// 	[ConcreteSubject, setState]
		this.method = s3[1] ;		
	}
	
	// set message
	public void setMessage() {
		// construct message as: method() or method(arg1, arg2, ...)
		if ( args.isEmpty() ) {
			this.message = this.method + "()" ;
		} else {
			this.message = this.method + "(" ;
			for (int i = 0; i < args.size(); i++) {
				MethodArg arg = args.get(i) ;
				if (i==0) {
					this.message = this.message + arg.value ;
				} else {
					this.message = this.message + ", " + arg.value ;
				}
			}
			this.message = this.message + ")" ;
		}
	}
	
	// list of message arguments
	public ArrayList<MethodArg> args = new ArrayList<MethodArg>() ;
	public void addArg( String name, String type, String value) {
		MethodArg arg = new MethodArg() ;
		arg.name = name ;
		arg.type = type ;
		arg.value = value ;
		args.add(arg) ;
	}
	
	// child stack frames
	public ArrayList<Frame> nested = new ArrayList<Frame>() ;
	public void add(Frame frame) {
		nested.add(frame) ;
	}

}
