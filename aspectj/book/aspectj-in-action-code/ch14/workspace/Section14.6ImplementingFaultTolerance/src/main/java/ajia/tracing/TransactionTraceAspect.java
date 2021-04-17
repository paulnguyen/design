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

//Listing 14.6: Aspect to trace transaction manager interaction
package ajia.tracing;

import javax.persistence.EntityManager;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Aspect
public class TransactionTraceAspect {
    private Logger logger = Logger.getLogger(TransactionTraceAspect.class);

    @AfterReturning(value = "ajia.transaction.TransactionPointcuts.transactionBegin(txDefinition)", returning = "txStatus")
    public void traceBegin(TransactionDefinition txDefinition,
            TransactionStatus txStatus) {
        logger.log(Level.INFO, "Starting transaction [" + "Read only: "
                + txDefinition.isReadOnly() + ", New transaction: "
                + txStatus.isNewTransaction() + "]");
        NDC.push(" ");
    }

    @AfterReturning(value = "ajia.transaction.TransactionPointcuts.transactionEnd(txStatus)")
    public void traceEnd(JoinPoint.StaticPart tjpsp, TransactionStatus txStatus) {
        NDC.pop();
        logger.log(Level.INFO, "Ending transaction "
                + tjpsp.getSignature().getName());
    }

    @Before("ajia.jpa.JPAPointcuts.entityManagerExecution() "
            + "&& !ajia.core.JavaPointcuts.objectExecution() "
            + "&& this(entityManager)")
    public void traceEntityManager(JoinPoint.StaticPart tjpsp,
            EntityManager entityManager) {
        logger.log(Level.INFO, "EntityManager ["
                + tjpsp.getSignature().toShortString() + " on "
                + System.identityHashCode(entityManager) + "]");
    }
}