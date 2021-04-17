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

package ajia.repository.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ajia.domain.Product;
import ajia.repository.ProductRepository;
import ajia.util.GenericRepository;

import static org.junit.Assert.*;

public class ProductRepositoryTest extends GenericRepositoryTest<Product> {
	@Autowired ProductRepository productRepository;

	@Test
	public void find() {
		verifyFindExisting(1001L);
	}
	
	@Test
	public void create() {
		Product newProduct = new Product();
		newProduct.setName("newProduct");
		Product savedProduct = verifyUpdate(newProduct);
		assertEquals("newProduct", savedProduct.getName());
	}
	
	@Test
	public void update() {
		Product product = productRepository.find(1001L);
		product.setName("foo");
		Product savedProduct = verifyUpdate(product);
		assertEquals("foo", savedProduct.getName());
	}

	@Test
	public void delete() {
	    // Delete product that is not part of any order
		verifyDelete(1004L);
	}

	@Override
	public GenericRepository<Product> getRepository() {
		return productRepository;
	}
}
