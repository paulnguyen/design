
package tracer;

/* AspectJ Documentation
  	- https://www.eclipse.org/aspectj/docs.php
 	- https://www.eclipse.org/aspectj/doc/released/adk15notebook/index.html
 	- https://www.eclipse.org/aspectj/doc/released/progguide/index.html
 	- https://eclipse.org/aspectj/doc/released/runtime-api/index.html
 	- https://www.eclipse.org/aspectj/doc/released/progguide/semantics-joinPoints.html
 	- https://www.eclipse.org/aspectj/doc/released/runtime-api/org/aspectj/lang/JoinPoint.html
 */


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

public aspect TracingAspect {

	private Sequence seq = new Sequence() ;
	private int callDepth;

	pointcut traced() : !within(tracer.*) && ( execution(* *.*(..)) || call(*.new (..)) ) ;

	before() : traced() {
		print("Before", thisJoinPoint);
		Object target = thisJoinPoint.getTarget();	
		System.out.println( "--->     This: " + thisJoinPoint.getThis() ) ;
		System.out.println( "--->   Target: " + target ) ;
		System.out.println( "---> entering: " + thisJoinPoint);
		System.out.println( "--->   w/args: " + thisJoinPoint.getArgs());
		System.out.println( "--->       at: " + thisJoinPoint.getSourceLocation());	
		printParameters(thisJoinPoint) ;
		callDepth++;
	}

	after() returning(Object r) : traced() {
		callDepth--;
		print("After Returning", thisJoinPoint);
		System.out.println( "--->     Returning: " + r ) ; 
	}

	after() throwing(Exception e) : traced() {
		callDepth--;
		print("After Exception", thisJoinPoint);
		System.out.println( "--->     Exception: " + e ) ; 
	}
	
	private void print(String prefix, JoinPoint m) {
		for (int i = 0; i < callDepth; i++) {
			System.out.print(".");
		} 
		System.out.print(prefix + ": " + m.getKind() + " " + m.getSignature() );
		System.out.print( " args : [ " ) ;
		for (Object obj : m.getArgs())
		{
			System.out.print( obj + " " ) ;
		}
		System.out.println("]") ;
	}
	
	// print method args
	static private void printParameters(JoinPoint jp) {
		System.out.println( "---> Arguments <---") ;
		Object[] args = jp.getArgs();
		String[] names = ((CodeSignature)jp.getSignature()).getParameterNames();
		Class[] types = ((CodeSignature)jp.getSignature()).getParameterTypes();
		for (int i = 0; i < args.length; i++) {
			System.out.println(	"--->   "  + i + ". " + names[i] +
								" : " +            types[i].getName() +
								" = " +            args[i]);
		}
	}	
	
}