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
import org.springframework.samples.jpetstore.domain.Account;

public class EditAccountAction extends SecureBaseAction {

  protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    AccountActionForm acctForm = (AccountActionForm) form;
		if (AccountActionForm.VALIDATE_EDIT_ACCOUNT.equals(acctForm.getValidate())) {
			acctForm.getAccount().setListOption(request.getParameter("account.listOption") != null);
			acctForm.getAccount().setBannerOption(request.getParameter("account.bannerOption") != null);
			Account account = acctForm.getAccount();
			getPetStore().updateAccount(account);
			acctForm.setAccount(getPetStore().getAccount(account.getUsername()));
			PagedListHolder myList = new PagedListHolder(getPetStore().getProductListByCategory(account.getFavouriteCategoryId()));
			myList.setPageSize(4);
			acctForm.setMyList(myList);
			request.getSession().setAttribute("accountForm", acctForm);
			request.getSession().removeAttribute("workingAccountForm");
			return mapping.findForward("success");
		}
		else {
			request.setAttribute("message", "Your account was not updated because the submitted information was not validated.");
			return mapping.findForward("failure");
		}
  }

}
