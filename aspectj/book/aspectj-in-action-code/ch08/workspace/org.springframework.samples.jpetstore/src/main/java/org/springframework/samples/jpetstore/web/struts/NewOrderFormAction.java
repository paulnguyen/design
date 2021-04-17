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

import org.springframework.samples.jpetstore.domain.Account;

public class NewOrderFormAction extends SecureBaseAction {

  protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    AccountActionForm acctForm = (AccountActionForm) request.getSession().getAttribute("accountForm");
    CartActionForm cartForm = (CartActionForm) request.getSession().getAttribute("cartForm");
    if (cartForm != null) {
      OrderActionForm orderForm = (OrderActionForm) form;
      // Re-read account from DB at team's request.
      Account account = getPetStore().getAccount(acctForm.getAccount().getUsername());
      orderForm.getOrder().initOrder(account, cartForm.getCart());
      return mapping.findForward("success");
    }
		else {
      request.setAttribute("message", "An order could not be created because a cart could not be found.");
      return mapping.findForward("failure");
    }
  }

}