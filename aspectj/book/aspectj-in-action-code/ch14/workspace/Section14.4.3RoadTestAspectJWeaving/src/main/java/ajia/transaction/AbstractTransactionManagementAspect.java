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

//Listing 14.2 Abstract transaction management aspect
package ajia.transaction;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

//import ...

@Aspect
public abstract class AbstractTransactionManagementAspect {
	private PlatformTransactionManager transactionManager;

	@Pointcut
	protected abstract void transactionalOp();

	public abstract TransactionAttributeWithRollbackRules getTransactionAttribute(
			JoinPoint jp);

	@Around("transactionalOp()")
	public Object transact(ProceedingJoinPoint pjp) throws Throwable {
		TransactionAttributeWithRollbackRules txAttribute = getTransactionAttribute(pjp);
		TransactionStatus status = transactionManager
				.getTransaction(txAttribute);
		try {
			Object ret = pjp.proceed();
			transactionManager.commit(status);
			return ret;
		} catch (Throwable ex) {
			if (txAttribute.rollbackOn(ex)) {
				transactionManager.rollback(status);
			} else {
				transactionManager.commit(status);
			}
			throw ex;
		}
	}

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}