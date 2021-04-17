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

package ajia.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ajia.AbstractIntegrationTest;
import ajia.domain.Product;
import ajia.service.InventoryException;
import ajia.service.InventoryService;
import ajia.service.ProductService;

import static org.junit.Assert.*;

public class InventoryServiceIntegrationTest extends AbstractIntegrationTest {

	@Autowired InventoryService inventoryService;
	@Autowired ProductService productService;

	@Test
	public void addProductExistingInventoryItem() {
	    Product product = productService.findProduct(1001L);
	    inventoryService.addProduct(product, 100);
	    
	    assertTrue(inventoryService.isProductAvailable(product, 99));
	    assertTrue(inventoryService.isProductAvailable(product, 100));
	    assertFalse(inventoryService.isProductAvailable(product, 101));
	}

	@Test
	public void addProductNonExistingInventoryItem() {
	    Product product = productService.findProduct(1004L);
	    inventoryService.addProduct(product, 100);
	    
	    assertTrue(inventoryService.isProductAvailable(product, 99));
	    assertTrue(inventoryService.isProductAvailable(product, 100));
	    assertFalse(inventoryService.isProductAvailable(product, 101));
	}

	@Test
	public void removeProduct() {
        Product product = productService.findProduct(1001L);
        inventoryService.addProduct(product, 100);
        inventoryService.removeProduct(product, 50);

        assertTrue(inventoryService.isProductAvailable(product, 49));
        assertTrue(inventoryService.isProductAvailable(product, 50));
        assertFalse(inventoryService.isProductAvailable(product, 51));
	}
	
	@Test(expected=InventoryException.class)
	public void removeProductInsufficientInventory() {
        Product product = productService.findProduct(1001L);
        inventoryService.addProduct(product, 100);
        inventoryService.removeProduct(product, 101);
	}
}
