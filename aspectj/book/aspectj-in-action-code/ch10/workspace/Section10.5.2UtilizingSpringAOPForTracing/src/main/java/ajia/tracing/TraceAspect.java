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

//Listing 10.14 Tracing aspect written in @AspectJ 
package ajia.tracing;

//import ...
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TraceAspect {
    private Logger logger = Logger.getLogger(TraceAspect.class);

    @Pointcut("execution(* *.*(..))")
    public void traced() {
    }

    @Before("traced()")
    public void trace(JoinPoint jp) {
        Signature sig = jp.getSignature();
        logger.log(Level.INFO, "Entering [" + sig.toShortString() + "]");
        NDC.push(" ");
    }

    @After("traced()")
    public void exit() {
        NDC.pop();
    }
}