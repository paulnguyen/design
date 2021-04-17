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

//Listing 12.6 SynchronousExecutionAspect.java
package ajia.example;

import java.util.List;

import ajia.pattern.worker.RunnableWithReturn;

public aspect SynchronousExecutionAspect {
    public pointcut syncOperation()
    : (call(* Math.max(..))
    || call(* List.*(..))
    /* || ... */);

    Object around() : syncOperation() {
        RunnableWithReturn worker = new RunnableWithReturn() {
            public void run() {
                setReturnValue(proceed());
            }
        };
        System.out.println("About to run " + worker);
        worker.run();
        return worker.getReturnValue();
    }
}