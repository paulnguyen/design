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

package ajia.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {
	private Order testOrder;
	private Product testProduct1 = new Product("testProduct1", "", 1);
	private Product testProduct2 = new Product("testProduct2", "", 2);
	
	@Before
	public void setUp() {
		testOrder = new Order();
	}
	
	@Test 
	public void addProduct() {
		testOrder.addProduct(testProduct1, 1);
		assertEquals(1, testOrder.getLineItems().size());
		assertNotNull(getLineItemFor(testOrder, testProduct1).getProduct());
		assertEquals(1, getLineItemFor(testOrder, testProduct1).getQuantity());
		
		testOrder.addProduct(testProduct1, 2);
		assertEquals(1, testOrder.getLineItems().size());
		assertNotNull(getLineItemFor(testOrder, testProduct1).getProduct());
		assertEquals(3, getLineItemFor(testOrder, testProduct1).getQuantity());
		
		testOrder.addProduct(testProduct2, 1);
		assertEquals(2, testOrder.getLineItems().size());
		assertNotNull(getLineItemFor(testOrder, testProduct2).getProduct());
		assertEquals(1, getLineItemFor(testOrder, testProduct2).getQuantity());
	}
	
	@Test
	public void removeProduct() {
		testOrder.addProduct(testProduct1, 2);
		testOrder.removeProduct(testProduct1, 1);
		assertNotNull(getLineItemFor(testOrder, testProduct1).getProduct());
		assertEquals(1, getLineItemFor(testOrder, testProduct1).getQuantity());
		
		testOrder.removeProduct(testProduct1, 1);
		assertNull(getLineItemFor(testOrder, testProduct1));
	}
	
	@Test
	public void getTotalPrice() {
		assertEquals(0, testOrder.getTotalPrice(), 0.01);
		testOrder.addProduct(testProduct1, 1);
		assertEquals(1, testOrder.getTotalPrice(), 0.01);
		testOrder.addProduct(testProduct1, 2);
		assertEquals(3, testOrder.getTotalPrice(), 0.01);
		
		testOrder.addProduct(testProduct2, 2);
		assertEquals(7, testOrder.getTotalPrice(), 0.01);
	}
	
	@Test(expected=IllegalStateException.class)
	public void addAfterPlace() {
		testOrder.place();
		testOrder.addProduct(testProduct1, 1);
	}
	
	@Test(expected=IllegalStateException.class)
	public void removeAfterPlace() {
		testOrder.place();
		testOrder.removeProduct(testProduct1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removeProductNotYetAdded() {
		testOrder.addProduct(testProduct1, 1);
		testOrder.removeProduct(testProduct2, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removingMoreThanExistingQuantity() {
		testOrder.addProduct(testProduct1, 1);
		testOrder.removeProduct(testProduct1, 2);
	}

	private LineItem getLineItemFor(Order order, Product product) {
		for (LineItem lineItem : order.getLineItems()) {
			if (lineItem.getProduct().equals(product)) {
				return lineItem;
			}
		}
		return null;
	}
}
