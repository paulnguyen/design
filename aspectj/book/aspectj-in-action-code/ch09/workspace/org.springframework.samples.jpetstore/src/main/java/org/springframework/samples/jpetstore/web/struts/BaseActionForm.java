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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BaseActionForm extends ActionForm {

  /* Public Methods */

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    ActionErrors actionErrors = null;
    ArrayList errorList = new ArrayList();
    doValidate(mapping, request, errorList);
    request.setAttribute("errors", errorList);
    if (!errorList.isEmpty()) {
      actionErrors = new ActionErrors();
      actionErrors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error"));
    }
    return actionErrors;
  }

  public void doValidate(ActionMapping mapping, HttpServletRequest request, List errors) {
  }

  /* Protected Methods */

  protected void addErrorIfStringEmpty(List errors, String message, String value) {
    if (value == null || value.trim().length() < 1) {
      errors.add(message);
    }
  }

}
