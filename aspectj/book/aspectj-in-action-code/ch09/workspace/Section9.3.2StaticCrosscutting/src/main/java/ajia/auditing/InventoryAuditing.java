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

//Listing 9.8 Using declare parents with @AspectJ style
package ajia.auditing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import ajia.domain.Product;

//import ...

@Aspect
public class InventoryAuditing {
	@DeclareParents(value = "ajia.service.*+", defaultImpl = AuditRecorderDefaultImpl.class)
	private AuditRecorder mixin;

	@Pointcut("execution(* *(ajia.domain.Product, int))"
			+ "&& this(auditRecorder)" + "&& args(product, quantity)")
	public void audited(AuditRecorder auditRecorder, Product product,
			int quantity) {
	}

	@Before("audited(auditRecorder, product, quantity)")
	public void audit(JoinPoint jp, AuditRecorder auditRecorder,
			Product product, int quantity) {
		auditRecorder
				.record("Operation = " + jp.getSignature().getName()
						+ " product = " + product.getName() + " quantity = "
						+ quantity);
	}
}