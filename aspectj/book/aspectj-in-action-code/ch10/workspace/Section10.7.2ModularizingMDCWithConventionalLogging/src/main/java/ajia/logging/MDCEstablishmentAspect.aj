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

//Listing 10.21 MDC establishment aspect
package ajia.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

//import ...

public aspect MDCEstablishmentAspect {
    private static final String MDC_KEY = "caller";

    pointcut logCall() : call(* Logger.log(..));

    before() : logCall() {
        MDC.put(MDC_KEY, thisEnclosingJoinPointStaticPart.getSignature()
                .toShortString());
    }

    after() : logCall() {
        MDC.remove(MDC_KEY);
    }
}