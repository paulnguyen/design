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

package ajia.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ajia.domain.Order;
import ajia.service.OrderService;

@Controller
@SessionAttributes("order")
@RequestMapping("admin/orderEdit.htm")
public class EditOrderController {
	@Autowired private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public Order setupForm(@RequestParam(required=false) Long orderId) {
		if (orderId != null) {
			return orderService.findOrder(orderId);
		} else {
			return new Order();
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String orderEdit(Order order) {
		orderService.updateOrder(order);
		return "redirect:orderSummary.htm";
	}
}
