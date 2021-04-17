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

//Listing 13.9: LogRoutingDetailsAspect
package ajia.swing;

import ajia.pattern.worker.*;

public aspect LogRoutingDetailsAspect {
    pointcut duringSyncRouting()
        : cflow(execution(* RunnableWithReturn.run()));

    before() : Swing.uiCall() && duringSyncRouting() {
        System.out.println("Executing operation synchronously");
    }

    pointcut duringAsyncRouting()
        : cflow(execution(* Runnable.run()))
          && !duringSyncRouting();

    before() : Swing.uiCall() && duringAsyncRouting() {
        System.out.println("Executing operation asynchronously");
    }
}