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
import org.springframework.samples.jpetstore.domain.Category;
import org.springframework.samples.jpetstore.domain.logic.PetStoreFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public class ViewCategoryController implements Controller {

	private PetStoreFacade petStore;

	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String categoryId = request.getParameter("categoryId");
		if (categoryId != null) {
			Category category = this.petStore.getCategory(categoryId);
			PagedListHolder productList = new PagedListHolder(this.petStore.getProductListByCategory(categoryId));
			productList.setPageSize(4);
			request.getSession().setAttribute("ViewProductAction_category", category);
			request.getSession().setAttribute("ViewProductAction_productList", productList);
			model.put("category", category);
			model.put("productList", productList);
		}
		else {
			Category category = (Category) request.getSession().getAttribute("ViewProductAction_category");
			PagedListHolder productList = (PagedListHolder) request.getSession().getAttribute("ViewProductAction_productList");
			if (category == null || productList == null) {
				throw new IllegalStateException("Cannot find pre-loaded category and product list");
			}
			String page = request.getParameter("page");
			if ("next".equals(page)) {
				productList.nextPage();
			}
			else if ("previous".equals(page)) {
				productList.previousPage();
			}
			model.put("category", category);
			model.put("productList", productList);
		}
		return new ModelAndView("Category", model);
	}

}
