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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ajia.domain.Product;
import ajia.service.InventoryService;
import ajia.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/spring/*.xml")
public class InventoryServiceTest {

	@Autowired InventoryService inventoryService;
	@Autowired ProductService productService;

	@Test
	public void addProduct() {
	    Product product = productService.findProduct(1001L);
	    inventoryService.addProduct(product, 100);
	    
	    assertTrue(inventoryService.isProductAvailable(product, 99));
	    assertTrue(inventoryService.isProductAvailable(product, 100));
	    assertFalse(inventoryService.isProductAvailable(product, 101));
	}
	
	@Test
	public void removeProductSufficientInventory() {
        Product product = productService.findProduct(1001L);
        inventoryService.addProduct(product, 100);
        
        inventoryService.removeProduct(product, 100);
        assertTrue(inventoryService.isProductAvailable(product, 100));
        inventoryService.removeProduct(product, 100);
        assertTrue(inventoryService.isProductAvailable(product, 0));
        assertFalse(inventoryService.isProductAvailable(product, 1));
	}
}
