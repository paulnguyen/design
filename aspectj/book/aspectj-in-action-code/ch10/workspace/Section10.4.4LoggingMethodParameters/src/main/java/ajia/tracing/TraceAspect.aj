/*
Copyright 2009 Ramnivas Laddad

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 
    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/

//Listing 10.11 TraceAspect: modified to log method parameters
package ajia.tracing;

//import ...;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint;

public aspect TraceAspect {
    private Logger logger = Logger.getLogger(getClass());

    pointcut traced()
    : execution(* *.*(..)) && !within(TraceAspect);

    before() : traced() && !execution(* Object.*(..)) {
        Signature sig = thisJoinPointStaticPart.getSignature();
        logger.log(Level.INFO, "Entering [" + sig.toShortString() + "]"
                + createParameterMessage(thisJoinPoint));
    }

    private String createParameterMessage(JoinPoint joinPoint) {
        StringBuffer paramBuffer = new StringBuffer("\n\t[This: ");
        paramBuffer.append(joinPoint.getThis());
        Object[] arguments = joinPoint.getArgs();
        paramBuffer.append("]\n\t[Args: (");
        for (int length = arguments.length, i = 0; i < length; ++i) {
            Object argument = arguments[i];
            paramBuffer.append(argument);
            if (i != length - 1) {
                paramBuffer.append(',');
            }
        }
        paramBuffer.append(")]");
        return paramBuffer.toString();
    }
}