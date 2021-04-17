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

////Listing 2.2 Class to exercise the message-delivery functionality
package ajia.main;

import ajia.messaging.MessageCommunicator;
import ajia.security.Authenticator;

public class Main {
	public static void main(String[] args) {
		MessageCommunicator messageCommunicator = new MessageCommunicator();
		messageCommunicator.deliver("Wanna learn AspectJ?");
		messageCommunicator.deliver("Harry", "having fun?");
		
        new Authenticator().authenticate();
	}
}