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

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ajia.AbstractIntegrationTest;
import ajia.domain.LineItem;
import ajia.domain.Order;
import ajia.service.OrderService;
import ajia.service.ProductService;
import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/integration-test-context.xml")
public class OrderServiceIntegrationTest extends AbstractIntegrationTest {

	@Autowired OrderService orderService;
	@Autowired ProductService productService;

	@Test
	public void cancelOrder() {
		Order order = orderService.findOrder(2001L);
		orderService.cancelOrder(order);
		assertFalse(order.isPlaced());
		orderService.place(order);
		assertTrue(order.isPlaced());
	}
	
	@Test
	public void orderManipulation() {
	    Order order = new Order();
	    orderService.updateOrder(order);
	    order = orderService.findOrder(order.getId());
	    assertNotNull(order.getId());
	    
	    orderService.addProduct(order, productService.findProduct(1003L), 2);
	    assertEquals(1, order.getLineItems().size());

	    orderService.removeProduct(order, productService.findProduct(1003L), 1);
	    LineItem lineItem = (LineItem)order.getLineItems().toArray()[0];
	    assertEquals(1, lineItem.getQuantity());
	    orderService.removeProduct(order, productService.findProduct(1003L), 1);
	    assertEquals(0, order.getLineItems().size());
	}
	
	@Test
	public void findOrders() {
		Collection<Order> orders = orderService.findOrders();
		assertEquals(4, orders.size());
	}
	
	@Test
	public void deleteOrder() {
		Order order = orderService.findOrder(2004L);
		orderService.deleteOrder(2004L);
		assertNull(orderService.findOrder(2004L));
	}
}
