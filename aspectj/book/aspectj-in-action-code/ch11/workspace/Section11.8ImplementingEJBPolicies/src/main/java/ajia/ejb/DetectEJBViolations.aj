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

//Listing 11.20 DetectEJBViolations.java: ensuring EJB policy enforcement
package ajia.ejb;

//import ...
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public aspect DetectEJBViolations {
    Logger logger = Logger.getLogger(getClass());

    pointcut uiCall() : call(* java.awt..*+.*(..));

    declare error : uiCall() && EJBPointcuts.inEJB()
        : "UI calls are not allowed from EJB beans."
        + "See EJB 3.0 specification section 20.1.2";

    before() : uiCall() && cflow(EJBPointcuts.inEJB()) {
        logger.log(Level.ERROR, "Detected call to AWT from EJB."
                + "See EJB 3.0 core specification section20.1.2",
                new Throwable());
    }

    pointcut staticMemberAccess() :
        set(static !final * *) && EJBPointcuts.inEJB();

    declare error : staticMemberAccess()
        : "EJBs are not allowed to have nonfinal static variables."
        + "See EJB 3.0 core specification section 20.1.2";
}