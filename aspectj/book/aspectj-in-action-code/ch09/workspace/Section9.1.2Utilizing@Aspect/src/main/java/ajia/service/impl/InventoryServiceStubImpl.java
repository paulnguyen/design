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

//Listing 9.1 InventoryServiceStubImpl: stub implementation of InventoryService
package ajia.service.impl;

import ajia.service.InventoryService;
import ajia.domain.Product;

public class InventoryServiceStubImpl implements InventoryService {
	public void addProduct(Product product, int quantity) {
		System.out.println("InventoryServiceImpl.addProduct "
				+ product.getName() + " " + quantity);
	}

	public void removeProduct(Product product, int quantity) {
		System.out.println("InventoryServiceImpl.removeProduct "
				+ product.getName() + " " + quantity);
	}

	public boolean isProductAvailable(Product product, int quantity) {
		System.out.println("InventoryServiceImpl.isProductAvailable "
				+ product.getName() + " " + quantity);
		return true;
	}
}