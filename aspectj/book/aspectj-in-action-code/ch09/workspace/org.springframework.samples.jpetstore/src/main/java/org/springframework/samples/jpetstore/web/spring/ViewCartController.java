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

import org.springframework.samples.jpetstore.domain.Cart;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public class ViewCartController implements Controller {

	private String successView;

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Cart cart = (Cart) WebUtils.getOrCreateSessionAttribute(request.getSession(), "sessionCart", Cart.class);
		String page = request.getParameter("page");
		if (userSession != null) {
			if ("next".equals(page)) {
				userSession.getMyList().nextPage();
			}
			else if ("previous".equals(page)) {
				userSession.getMyList().previousPage();
			}
		}
		if ("nextCart".equals(page)) {
			cart.getCartItemList().nextPage();
		}
		else if ("previousCart".equals(page)) {
			cart.getCartItemList().previousPage();
		}
		return new ModelAndView(this.successView, "cart", cart);
	}

}
