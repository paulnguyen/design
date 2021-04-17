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

//Listing 9.6 Tracing invocation handler to be uses in a proxy
package ajia.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//import ...

public class TracingInvocationHandler implements InvocationHandler {
	private Object target;

	public TracingInvocationHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Entering " + method);
		return method.invoke(target, args);
	}
}