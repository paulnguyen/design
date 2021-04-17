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

//Listing 10.16 ExceptionLoggerAspect: logging exceptions using log4j
package ajia.tracing;

//import ...;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.Signature;

public aspect ExceptionTraceAspect {
    private Logger logger = Logger.getLogger("exceptions");
    private ThreadLocal<Throwable> lastLoggedException = new ThreadLocal<Throwable>();

    pointcut exceptionTraced()
    : execution(* *.*(..)) && !within(ExceptionTraceAspect);

    after() throwing(Throwable ex) : exceptionTraced() {
        if (lastLoggedException.get() != ex) {
            lastLoggedException.set(ex);
            Signature sig = thisJoinPointStaticPart.getSignature();
            logger.log(Level.ERROR, "Exception trace aspect ["
                    + sig.toShortString() + "]", ex);
        }
    }
}