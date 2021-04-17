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

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import ajia.domain.Order;
import ajia.domain.Product;
import ajia.repository.OrderRepository;
import ajia.service.InventoryException;
import ajia.service.InventoryService;


public class OrderServiceTest {
    private OrderServiceImpl testOrderService;
    
    @Mock private Order order;
    @Mock private Product product;
    @Mock private InventoryService inventoryService;
    @Mock private OrderRepository orderRepository;
    
    @Before
    public void setup() {
    	initMocks(this);
        testOrderService = new OrderServiceImpl();
        ReflectionTestUtils.setField(testOrderService, "inventoryService", inventoryService);
        ReflectionTestUtils.setField(testOrderService, "orderRepository", orderRepository);
    }
    
    @Test(expected=InventoryException.class)
    public void insufficientInventoryOrdering() {
        doThrow(new InventoryException("")).when(inventoryService).removeProduct(product, 1);
        testOrderService.addProduct(order, product, 1);
    }
    
    @Test
    public void sufficientInventoryOrdering() {
        testOrderService.addProduct(order, product, 1);
        verify(inventoryService).removeProduct(product, 1);
    }
}
