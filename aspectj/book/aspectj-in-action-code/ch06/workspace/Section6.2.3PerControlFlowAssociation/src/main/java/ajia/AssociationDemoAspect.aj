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

//Listing 6.4 AssociationDemoAspect with percflow() association
package ajia;

public aspect AssociationDemoAspect
percflow(accountOperationExecution(Account)) {
	// ... unchanged from listing 6.1
	public AssociationDemoAspect() {
		System.out.println("Creating aspect instance");
	}

	pointcut accountOperationExecution(Account account)
	: (execution(* Account.credit(..))
			|| execution(* Account.debit(..)))
			&& this(account);
	
	before(Account account)
		: accountOperationExecution(account)
		|| (execution(* Account.setBalance(..)) && this(account)) {
		System.out.println("JoinPoint: " + thisJoinPointStaticPart
				+ "\n\taspect: " + this + "\n\tobject: " + account);
	}
}