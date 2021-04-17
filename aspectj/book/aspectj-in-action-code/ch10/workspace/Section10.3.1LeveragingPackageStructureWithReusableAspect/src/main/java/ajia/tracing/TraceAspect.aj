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

//Listing 10.7 Trace aspect based on reusable aspect defining the system architecture
package ajia.tracing;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.Signature;

import ajia.architecture.SystemArchitecture;

//import ...

public aspect TraceAspect {
    private Logger logger = Logger.getLogger(TraceAspect.class);

    pointcut inTracedType()
    : SystemArchitecture.inDomainType()
    || SystemArchitecture.inControllerType()
    || SystemArchitecture.inRepositoryType();

    pointcut traced()
    : (execution(* *(..)) || execution(new(..)))
    && inTracedType();

    // ... advice to traced() ...

    before() : traced() {
        Signature sig = thisJoinPointStaticPart.getSignature();
        logger.log(Level.INFO, "Entering [" + sig.toShortString() + "]");
    }
} 