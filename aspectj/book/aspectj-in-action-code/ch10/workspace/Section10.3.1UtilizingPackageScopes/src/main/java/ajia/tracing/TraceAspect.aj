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

//Listing 10.5 Trace aspect utilizing package scopes
package ajia.tracing;

import org.apache.log4j.*;
import org.aspectj.lang.Signature;

//import ...

public aspect TraceAspect {
    private Logger logger = Logger.getLogger(TraceAspect.class);

    pointcut inDomainType()
    : within(ajia.domain..*);

    pointcut inControllerType()
    : within(ajia.web..*);

    pointcut inRepositoryType()
    : within(ajia.repository..*);

    pointcut inTracedType()
    : inDomainType() || inControllerType() || inRepositoryType();

    pointcut traced()
    : (execution(* *(..)) || execution(new(..)))
    && inTracedType();

    // ... advice to traced() ...
    before() : traced() {
        Signature sig = thisJoinPointStaticPart.getSignature();
        logger.log(Level.INFO, "Entering [" + sig.toShortString() + "]");
    }
}