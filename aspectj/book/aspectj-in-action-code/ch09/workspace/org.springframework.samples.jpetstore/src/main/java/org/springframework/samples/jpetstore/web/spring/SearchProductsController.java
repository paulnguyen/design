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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.samples.jpetstore.domain.logic.PetStoreFacade;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public class SearchProductsController implements Controller {

	private PetStoreFacade petStore;

	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
			}
			PagedListHolder productList = new PagedListHolder(this.petStore.searchProductList(keyword.toLowerCase()));
			productList.setPageSize(4);
			request.getSession().setAttribute("SearchProductsController_productList", productList);
			return new ModelAndView("SearchProducts", "productList", productList);
		}
		else {
			String page = request.getParameter("page");
			PagedListHolder productList = (PagedListHolder) request.getSession().getAttribute("SearchProductsController_productList");
			if (productList == null) {
				return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
			}
			if ("next".equals(page)) {
				productList.nextPage();
			}
			else if ("previous".equals(page)) {
				productList.previousPage();
			}
			return new ModelAndView("SearchProducts", "productList", productList);
		}
	}

}
