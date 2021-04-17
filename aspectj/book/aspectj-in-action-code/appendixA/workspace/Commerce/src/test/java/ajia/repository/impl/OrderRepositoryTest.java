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

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ajia.domain.LineItem;
import ajia.domain.Order;
import ajia.domain.Product;
import ajia.repository.OrderRepository;
import ajia.repository.ProductRepository;
import ajia.util.GenericRepository;

import static org.junit.Assert.*;

public class OrderRepositoryTest extends GenericRepositoryTest<Order> {
	@Autowired OrderRepository orderRepository;
    @Autowired ProductRepository productRepository;

	@Test
	public void find() {
		Order order = verifyFindExisting(2001L);
		Collection<LineItem> lineItems = order.getLineItems();
		assertNotNull(lineItems);
		assertEquals(2, lineItems.size());
	}
	
	@Test
	public void create() {
		Order newOrder = new Order();
        newOrder.addProduct(productRepository.find(1001L), 20);
		Order savedOrder = verifyUpdate(newOrder);
        assertEquals(1, savedOrder.getLineItems().size());
	}
	
	@Test
	public void delete() {
		verifyDelete(2002L);
	}

	@Override
	public GenericRepository<Order> getRepository() {
		return orderRepository;
	}
}
