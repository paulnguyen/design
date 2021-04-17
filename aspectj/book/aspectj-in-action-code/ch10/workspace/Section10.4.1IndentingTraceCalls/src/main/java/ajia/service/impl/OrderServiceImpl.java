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

//Listing A.10 OrderService implementation
package ajia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajia.domain.LineItem;
import ajia.domain.Order;
import ajia.domain.Product;
import ajia.repository.OrderRepository;
import ajia.service.InventoryService;
import ajia.service.OrderService;

//import ...

@Service(value="orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private InventoryService inventory;
    
    public Order findOrder(Long orderId) {
        return orderRepository.find(orderId);
    }

    public void updateOrder(Order order) {
        orderRepository.update(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.delete(orderRepository.find(orderId));
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public void addProduct(Order order, Product product, int quantity) {
    	inventory.removeProduct(product, quantity);
        order.addProduct(product, quantity);
        updateOrder(order);
    }
    
    public void removeProduct(Order order, Product product, int quantity) {
        inventory.addProduct(product, quantity);
        order.removeProduct(product, quantity);
        updateOrder(order);
    }

    public void cancelOrder(Order order) {
        for (LineItem lineItem : order.getLineItems()) {
            removeProduct(order, lineItem.getProduct(), lineItem.getQuantity());
        }
        order.cancel();
        updateOrder(order);
    }
    
    public void place(Order order) {
        order.place();
        updateOrder(order);
    }
}
