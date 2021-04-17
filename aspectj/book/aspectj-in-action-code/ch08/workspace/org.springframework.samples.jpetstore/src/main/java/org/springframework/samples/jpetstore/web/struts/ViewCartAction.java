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

public class ViewCartAction extends BaseAction {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    CartActionForm cartForm = (CartActionForm) form;
    AccountActionForm acctForm = (AccountActionForm) request.getSession().getAttribute("accountForm");
    String page = request.getParameter("page");
    if (acctForm != null && acctForm.getAccount() != null) {
      if ("next".equals(page)) {
        acctForm.getMyList().nextPage();
      }
			else if ("previous".equals(page)) {
        acctForm.getMyList().previousPage();
      }
    }
    if ("nextCart".equals(page)) {
      cartForm.getCart().getCartItemList().nextPage();
    }
		else if ("previousCart".equals(page)) {
      cartForm.getCart().getCartItemList().previousPage();
    }
    return mapping.findForward("success");
  }

}
