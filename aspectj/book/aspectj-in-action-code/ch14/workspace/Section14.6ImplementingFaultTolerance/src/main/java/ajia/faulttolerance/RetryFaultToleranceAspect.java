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

//Listing 14.13 Base aspect to introduce fault tolerance through retries
package ajia.faulttolerance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.batch.retry.RetryCallback;
import org.springframework.batch.retry.RetryContext;
import org.springframework.batch.retry.support.RetryTemplate;

@Aspect
public abstract class RetryFaultToleranceAspect {
    @Pointcut
    public abstract void retryOperation();

    private RetryTemplate retryTemplate;

    @Around("retryOperation()")
    public Object retry(final ProceedingJoinPoint pjp) throws Throwable {
        RetryCallback<Object> worker = new RetryCallback<Object>() {
            public Object doWithRetry(RetryContext retryContext)
                    throws Exception {
                try {
                    return pjp.proceed();
                } catch (Exception ex) {
                    throw ex;
                } catch (Error error) {
                    throw error;
                } catch (Throwable t) {
                    throw new IllegalStateException(
                            "Caught throwable that is neither "
                                    + "Exception nor Error");
                }
            }
        };
        return retryTemplate.execute(worker);
    }

    public void setRetryTemplate(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }
}