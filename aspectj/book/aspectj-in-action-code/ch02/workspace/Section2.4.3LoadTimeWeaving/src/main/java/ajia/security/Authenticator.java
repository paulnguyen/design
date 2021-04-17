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

package ajia.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authenticator {
	private ThreadLocal<String> authenticatedUser = new ThreadLocal<String>();
	
	public void authenticate() {
		if (isAuthenticated()) {
			return;
		}
		String[] userNamePassword = getUserNamePassword();
		if (!userNamePassword[0].equals(userNamePassword[1])) {
			throw new AuthenticationException("User/password didn't match");
		}
		authenticatedUser.set(userNamePassword[0]);
	}
	
	public boolean isAuthenticated() {
		return authenticatedUser.get() != null;
	}
	
	public String[] getUserNamePassword() {
		boolean usePrintln = Boolean.getBoolean("ant.run");
		String[] userNamePassword = new String[2];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			if (usePrintln) {
				System.out.println("Username: ");
			} else {
				System.out.print("Username: ");
			}
			userNamePassword[0] = in.readLine().trim();
			if (usePrintln) {
				System.out.println("Password: ");
			} else {
				System.out.print("Password: ");
			}
			userNamePassword[1] = in.readLine().trim();
		} catch (IOException ex) {
			// ignore... will return array of null strings
		}
		return userNamePassword;
	}
}
