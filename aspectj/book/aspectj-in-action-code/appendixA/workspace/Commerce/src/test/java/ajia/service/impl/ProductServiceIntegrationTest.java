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

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ajia.AbstractIntegrationTest;
import ajia.domain.Product;
import ajia.service.ProductService;

import static org.junit.Assert.*;

public class ProductServiceIntegrationTest  extends AbstractIntegrationTest{

	@Autowired ProductService productService;

	@Test
	public void load() {
		// nothing to do here
	}
	
	@Test
	public void findProducts() {
		List<Product> plist = productService.findProducts();
		assertNotNull(plist);
		assertTrue(plist.size() > 0);
		assertNotNull(plist.get(0).getId());
		assertNotNull(plist.get(0).getName());
	}
	
	@Test
	public void createProduct() {
		Product newProduct = new Product();
		newProduct.setName("newProduct");
		productService.updateProduct(newProduct);
		
		assertNotNull(productService.findProduct(newProduct.getId()));
	}
	
	@Test
	public void deleteProduct() {
		// Delete product without a corresponding inventory item
		productService.deleteProduct(1004L);
		
		assertNull(productService.findProduct(1004L));
	}
	
	@Test
	public void updateProduct() {
		
	}
}
