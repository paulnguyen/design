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

//Listing 10.22 AbstractPerformanceMonitoringAspect: monitoring operation time
package ajia.monitoring;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//import ...

@Aspect
public abstract class AbstractPerformanceMonitoringAspect {
    private Logger logger = Logger
            .getLogger(AbstractPerformanceMonitoringAspect.class);

    @Pointcut
    public abstract void monitoredOp();

    @Around("monitoredOp()")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        try {
            return pjp.proceed();
        } finally {
            long complete = System.nanoTime();
            logger.log(Level.INFO, "Operation "
                    + pjp.getSignature().toShortString() + " took "
                    + (complete - start) + " nanoseconds");
        }
    }
}