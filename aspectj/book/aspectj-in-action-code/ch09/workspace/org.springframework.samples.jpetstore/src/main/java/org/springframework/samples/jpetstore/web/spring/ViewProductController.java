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

package org.springframework.samples.jpetstore.web.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.samples.jpetstore.domain.Product;
import org.springframework.samples.jpetstore.domain.logic.PetStoreFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public class ViewProductController implements Controller {

	private PetStoreFacade petStore;

	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String productId = request.getParameter("productId");
		if (productId != null) {
			PagedListHolder itemList = new PagedListHolder(this.petStore.getItemListByProduct(productId));
			itemList.setPageSize(4);
			Product product = this.petStore.getProduct(productId);
			request.getSession().setAttribute("ViewProductAction_itemList", itemList);
			request.getSession().setAttribute("ViewProductAction_product", product);
			model.put("itemList", itemList);
			model.put("product", product);
		}
		else {
			PagedListHolder itemList = (PagedListHolder) request.getSession().getAttribute("ViewProductAction_itemList");
			Product product = (Product) request.getSession().getAttribute("ViewProductAction_product");
			String page = request.getParameter("page");
			if ("next".equals(page)) {
				itemList.nextPage();
			}
			else if ("previous".equals(page)) {
				itemList.previousPage();
			}
			model.put("itemList", itemList);
			model.put("product", product);
		}
		return new ModelAndView("Product", model);
	}

}
