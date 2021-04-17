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

import org.apache.struts.action.ActionMapping;

import org.springframework.samples.jpetstore.domain.Cart;

public class CartActionForm extends BaseActionForm {

  /* Private Fields */

  private Cart cart = new Cart();
  private String workingItemId;

  /* JavaBeans Properties */

  public Cart getCart() { return cart; }
  public void setCart(Cart cart) { this.cart = cart; }

  public String getWorkingItemId() { return workingItemId; }
  public void setWorkingItemId(String workingItemId) { this.workingItemId = workingItemId; }

  /* Public Methods */

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    workingItemId = null;
  }
}
