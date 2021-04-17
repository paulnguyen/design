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

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import ajia.repository.OrderRepository;
import ajia.repository.ProductRepository;
import ajia.service.InventoryService;

public class OrderTest {
	private Order testOrder;
	
    @Mock private PricingStrategy mockPricingStrategy;
    @Mock private InventoryService mockInventoryService;
    @Mock private OrderRepository mockOrderRepository;
    @Mock private ProductRepository mockProductRepository;
    @Mock private Product mockProduct;
    
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testOrder = new Order();
		ReflectionTestUtils.setField(testOrder, "pricingStrategy", mockPricingStrategy);
		ReflectionTestUtils.setField(testOrder, "inventoryService", mockInventoryService);
		ReflectionTestUtils.setField(testOrder, "orderRepository", mockOrderRepository);
		ReflectionTestUtils.setField(testOrder, "productRepository", mockProductRepository);
		
		when(mockProductRepository.find(1L)).thenReturn(mockProduct);
	}
	
	@Test
	public void inventoryNotificationAddProduct() {
		testOrder.addProduct(1L, 1);
		
		verify(mockInventoryService).removeProduct(mockProduct, 1);
		verify(mockOrderRepository).update(testOrder);
	}

	@Test
	public void inventoryNotificationRemoveProduct() {
		testOrder.addProduct(1L, 1);
		reset(mockInventoryService); // forget notification due to addProduct()
		reset(mockOrderRepository); // forget update due to addProduct()
		testOrder.removeProduct(1L, 1);
		
		verify(mockInventoryService).addProduct(mockProduct, 1);
		verify(mockOrderRepository).update(testOrder);
	}
}
