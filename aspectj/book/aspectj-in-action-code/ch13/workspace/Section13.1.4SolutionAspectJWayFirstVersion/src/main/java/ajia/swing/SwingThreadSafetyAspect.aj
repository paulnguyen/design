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

//Listing 13.4: The base swing thread-safety aspect: First version
package ajia.swing;

//import ...

import java.awt.EventQueue;

import ajia.pattern.worker.*;

public abstract aspect SwingThreadSafetyAspect {
    public abstract pointcut uiCall();

    pointcut nonRoutedCall()
        : Swing.threadSafeCall()
          || within(SwingThreadSafetyAspect)
          || if(EventQueue.isDispatchThread());

    pointcut routedCall()
        : uiCall() && ! nonRoutedCall();

    Object around() : routedCall() {
        RunnableWithReturn worker = new RunnableWithReturn() {
            public void run() {
                setReturnValue(proceed());
            }
        };
        try {
            EventQueue.invokeAndWait(worker);
        } catch (Exception ex) {
            // ... log exception
            return null;
        }
        return worker.getReturnValue();
    }
}