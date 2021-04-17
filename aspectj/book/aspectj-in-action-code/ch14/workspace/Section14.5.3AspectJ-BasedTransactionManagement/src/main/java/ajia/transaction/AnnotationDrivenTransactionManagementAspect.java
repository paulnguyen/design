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

//Listing 14.3: Annotation-based transaction management aspect
package ajia.transaction;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.annotation.Transactional;

@Aspect
public class AnnotationDrivenTransactionManagementAspect extends
		AbstractTransactionManagementAspect {
	// org..Transactional abbreviation only for better formatting
	@Pointcut("execution(@org..Transactional * *(..)) "
			+ "|| execution(* (@org..Transactional *).*(..))")
	public void transactionalOp() {
	}

	public TransactionAttributeWithRollbackRules getTransactionAttribute(
			JoinPoint jp) {
		MethodSignature jpSignature = (MethodSignature) jp.getSignature();
		Transactional typeAnnotation = AnnotationUtils.findAnnotation(
				jpSignature.getDeclaringType(), Transactional.class);
		Transactional methodAnnotation = AnnotationUtils.findAnnotation(
				jpSignature.getMethod(), Transactional.class);
		return TransactionManagementUtil.createTransactionAttribute(
				typeAnnotation, methodAnnotation);
	}
}