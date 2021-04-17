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

//Listing 10.9 Abstract aspect for indentation functionality
package ajia.tracing;

//import ...
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.aspectj.lang.Signature;

public abstract aspect IndentationTraceAspect {
    private Logger logger = Logger.getLogger(IndentationTraceAspect.class);

    public abstract pointcut traced();

    // ... before and after advice remain unchanged since listing 10.8
    before() : traced() {
        Signature sig = thisJoinPointStaticPart.getSignature();
        logger.log(Level.INFO, "Entering [" + sig.toShortString() + "]");
        NDC.push(" ");
    }

    after() : traced() {
        NDC.pop();
    }
}