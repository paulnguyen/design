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

//Listing 6.16 InterAdvicePrecedenceAspect.aj: testing advice ordering in an aspect
package ajia.main;

public aspect InterAdvicePrecedenceAspect {
	public pointcut performCall() : call(* Main.perform());

	after() returning : performCall() {
		System.out.println("<after1/>");
	}

	before() : performCall() {
		System.out.println("<before1/>");
	}

	void around() : performCall() {
		System.out.println("<around>");
		proceed();
		System.out.println("</around>");
	}

	before() : performCall() {
		System.out.println("<before2/>");
	}
}