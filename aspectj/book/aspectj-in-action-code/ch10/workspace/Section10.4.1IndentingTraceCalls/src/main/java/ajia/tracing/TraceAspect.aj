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

//Listing 10.8 Indentation using log4j’s nested diagnostic context
package ajia.tracing;

//import ...
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.aspectj.lang.Signature;

public aspect TraceAspect {
    private Logger logger = Logger.getLogger(getClass());

    pointcut traced()
    : execution(* *.*(..)) && !within(TraceAspect);

    before() : traced() {
        Signature sig = thisJoinPointStaticPart.getSignature();
        logger.log(Level.INFO, "Entering [" + sig.toShortString() + "]");
        NDC.push(" ");
    }

    after() : traced() {
        NDC.pop();
    }
}
