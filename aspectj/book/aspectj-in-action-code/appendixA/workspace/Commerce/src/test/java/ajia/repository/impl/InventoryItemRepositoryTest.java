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

import ajia.domain.InventoryItem;
import ajia.domain.Product;
import ajia.repository.InventoryItemRepository;
import ajia.repository.ProductRepository;
import ajia.util.GenericRepository;

import static org.junit.Assert.*;

public class InventoryItemRepositoryTest extends GenericRepositoryTest<InventoryItem> {
	@Autowired InventoryItemRepository inventoryItemRepository;
    @Autowired ProductRepository productRepository;
    
	@Test
	public void findByProductExisting() {
		Product product = productRepository.find(1002L);
		InventoryItem inventoryItem = inventoryItemRepository.findByProduct(product);
		assertNotNull(inventoryItem);
		assertEquals(1, inventoryItem.getQuantityOnHand());
	}
	
	@Test
	public void findByProductNonExisting() {
		Product product = productRepository.find(1009L);
		InventoryItem inventoryItem = inventoryItemRepository.findByProduct(product);
		assertNull(inventoryItem);
	}

	@Override
	public GenericRepository<InventoryItem> getRepository() {
		return inventoryItemRepository;
	}
}
