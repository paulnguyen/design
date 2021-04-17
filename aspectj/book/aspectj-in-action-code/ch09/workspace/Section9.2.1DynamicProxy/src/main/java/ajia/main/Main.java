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

//Listing 9.7 Creating a proxy to associate an invocation handler
package ajia.main;

import java.lang.reflect.Proxy;

import ajia.domain.Product;
import ajia.proxy.TracingInvocationHandler;
import ajia.service.InventoryService;
import ajia.service.impl.InventoryServiceStubImpl;

//import ...

public class Main {
	public static void main(String[] args) {
		InventoryService inventoryService = new InventoryServiceStubImpl();
		InventoryService inventoryServiceProxy = (InventoryService) Proxy
				.newProxyInstance(InventoryService.class.getClassLoader(),
						new Class[] { InventoryService.class },
						new TracingInvocationHandler(inventoryService));
		Product ajiaBook = new Product("AJIA", "AspectJ in Action", 44.95);
		inventoryServiceProxy.addProduct(ajiaBook, 1000000);
		inventoryServiceProxy.removeProduct(ajiaBook, 1000000);
	}
}