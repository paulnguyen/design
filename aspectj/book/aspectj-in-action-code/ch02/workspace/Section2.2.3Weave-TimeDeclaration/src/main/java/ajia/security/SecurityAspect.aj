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

////Listing 2.3 Aspect to secure access
package ajia.security;

import ajia.messaging.MessageCommunicator;

public aspect SecurityAspect {
	private Authenticator authenticator = new Authenticator();

	pointcut secureAccess()
        : execution(* MessageCommunicator.deliver(..));

	before() : secureAccess() {
		System.out.println("Checking and authenticating user");
		authenticator.authenticate();
	}
	
	declare warning
	    : call(void Authenticator.authenticate())
	      && !within(SecurityAspect)
	    : "Authentication should be performed only by SecurityAspect";
}