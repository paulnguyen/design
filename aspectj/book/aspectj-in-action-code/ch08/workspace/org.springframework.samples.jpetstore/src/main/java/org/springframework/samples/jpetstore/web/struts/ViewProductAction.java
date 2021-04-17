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

package org.springframework.samples.jpetstore.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.samples.jpetstore.domain.Product;

public class ViewProductAction extends BaseAction {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    String productId = request.getParameter("productId");
    if (productId != null) {
			PagedListHolder itemList = new PagedListHolder(getPetStore().getItemListByProduct(productId));
			itemList.setPageSize(4);
			Product product = getPetStore().getProduct(productId);
      request.getSession().setAttribute("ViewProductAction_itemList", itemList);
			request.getSession().setAttribute("ViewProductAction_product", product);
			request.setAttribute("itemList", itemList);
      request.setAttribute("product", product);
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
			request.setAttribute("itemList", itemList);
      request.setAttribute("product", product);
    }
    return mapping.findForward("success");
  }

}
