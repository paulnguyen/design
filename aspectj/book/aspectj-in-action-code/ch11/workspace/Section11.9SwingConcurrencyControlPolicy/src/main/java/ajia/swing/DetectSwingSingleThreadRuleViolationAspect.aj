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

//Listing 11.26 An aspect that detects the Swing single-thread rule
package ajia.swing;

//import ...
import java.awt.EventQueue;

public aspect DetectSwingSingleThreadRuleViolationAspect {
    before() : Swing.uiCall()
               && !Swing.threadSafeCall()
               && if(!EventQueue.isDispatchThread()) {
                   
        System.err.println("Violation: Swing method called from nonAWT thread"
                + "\nCalled method: " + thisJoinPointStaticPart.getSignature()
                + "\nCaller: "
                + thisEnclosingJoinPointStaticPart.getSignature()
                + "\nSource location: "
                + thisJoinPointStaticPart.getSourceLocation() + "\nThread: "
                + Thread.currentThread()
                + "\nChange code to use EventQueue.invokeLater() "
                + "or EventQueue.invokeAndWait()\n");
    }
}