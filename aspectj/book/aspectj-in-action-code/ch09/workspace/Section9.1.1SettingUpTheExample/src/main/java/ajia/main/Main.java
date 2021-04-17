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

//Listing 9.3 Driver program to exercise Spring AOP functionality
package ajia.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ajia.domain.Product;
import ajia.service.InventoryService;

public class Main {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"application-context.xml");
		InventoryService inventoryService = (InventoryService) applicationContext
				.getBean("inventoryService");
		Product ajiaBook = new Product("AJIA", "AspectJ in Action", 44.99);
		inventoryService.addProduct(ajiaBook, 1000000);
		inventoryService.removeProduct(ajiaBook, 1000000);
	}
}