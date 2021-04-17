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

//Listing 2.7 Introducing security using the @AspectJ syntax
package ajia.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecurityAspect {
	private Authenticator authenticator = new Authenticator();

	@Pointcut("execution(* ajia.messaging.MessageCommunicator.deliver(..))")
	public void secureAccess() {
	}

	@Before("secureAccess()")
	public void secure() {
		System.out.println("Checking and authenticating user");
		authenticator.authenticate();
	}
}