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

//Listing A.18 Controller to view and update an order
package ajia.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ajia.domain.Order;
import ajia.service.OrderService;
import ajia.service.ProductService;

//import ...

@Controller
@RequestMapping("admin/*.htm")
public class OrderAdminController {
    
    @Autowired private OrderService orderService;
    @Autowired private ProductService productService;
    
    @RequestMapping
    @ModelAttribute("orders.htm")
    public List<Order> orderSummary() {
        return orderService.findOrders();
    }
    
    @RequestMapping
    public Order orderDetails(Long orderId) {
        return orderService.findOrder(orderId);
    }
    
    @RequestMapping
    public String orderDelete(Long orderId)    {
        orderService.deleteOrder(orderId);
        return "redirect:orderSummary.htm";
    }
}
