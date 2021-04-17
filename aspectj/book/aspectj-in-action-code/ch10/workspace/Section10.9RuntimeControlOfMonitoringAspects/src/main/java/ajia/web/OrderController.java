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
package ajia.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ajia.domain.Order;
import ajia.service.OrderService;
import ajia.service.ProductService;

//import ...

@Controller
public class OrderController {
    @Autowired private OrderService orderService;
    @Autowired private ProductService productService;
    
    @RequestMapping("/viewCart.htm")
    public Order viewCart(HttpSession session) {
        return getCurrentOrder(session);
    }
    
    @RequestMapping(value="/addToCart.htm", method=RequestMethod.POST)
    public String addToCart(@RequestParam("id") Long productId, HttpSession session) {
        Order currentOrder = getCurrentOrder(session);
        orderService.addProduct(currentOrder, 
                                productService.findProduct(productId), 1);
        return "redirect:productSummary.htm";
    }
    
    @RequestMapping(value="/place.htm", method=RequestMethod.POST)
    public String place(HttpSession session) {
        orderService.place(getCurrentOrder(session));
        session.removeAttribute("currentOrder");
        return "redirect:/";
    }
    
    private Order getCurrentOrder(HttpSession session) {
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        if (currentOrder == null) {
            currentOrder = new Order();
            orderService.updateOrder(currentOrder);
            session.setAttribute("currentOrder", currentOrder);
        }
        return currentOrder;
    }
}
