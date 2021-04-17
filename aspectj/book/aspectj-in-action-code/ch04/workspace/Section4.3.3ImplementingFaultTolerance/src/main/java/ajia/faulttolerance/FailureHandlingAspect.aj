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

//Listing 4.4 Aspect that handles failure by retrying
package ajia.faulttolerance;

import org.springframework.remoting.RemoteAccessException;

public aspect FailureHandlingAspect {
	private final int MAX_RETRIES = 3;

	Object around() : call(@Idempotent * *(..)) {
		int retry = 0;
		while (true) {
			try {
				return proceed();
			} catch (RemoteAccessException ex) {
				System.out.println("Encountered " + ex);
				if (++retry > MAX_RETRIES) {
					throw ex;
				}
				System.out.println("\tRetrying...");
			}
		}
	}
}