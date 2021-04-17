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

package org.springframework.samples.jpetstore.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

public class Cart implements Serializable {

  /* Private Fields */

  private final Map itemMap = Collections.synchronizedMap(new HashMap());
	
  private final PagedListHolder itemList = new PagedListHolder();

  /* JavaBeans Properties */

	public Cart() {
		this.itemList.setPageSize(4);
	}

	public Iterator getAllCartItems() { return itemList.getSource().iterator(); }
  public PagedListHolder getCartItemList() { return itemList; }
  public int getNumberOfItems() { return itemList.getSource().size(); }

  /* Public Methods */

  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }

  public void addItem(Item item, boolean isInStock) {
    CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(item.getItemId(), cartItem);
      itemList.getSource().add(cartItem);
    }
    cartItem.incrementQuantity();
  }


  public Item removeItemById(String itemId) {
    CartItem cartItem = (CartItem) itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    }
		else {
      itemList.getSource().remove(cartItem);
      return cartItem.getItem();
    }
  }

  public void incrementQuantityByItemId(String itemId) {
    CartItem cartItem = (CartItem) itemMap.get(itemId);
    cartItem.incrementQuantity();
  }

  public void setQuantityByItemId(String itemId, int quantity) {
    CartItem cartItem = (CartItem) itemMap.get(itemId);
    cartItem.setQuantity(quantity);
  }

  public double getSubTotal() {
    double subTotal = 0;
    Iterator items = getAllCartItems();
    while (items.hasNext()) {
      CartItem cartItem = (CartItem) items.next();
      Item item = cartItem.getItem();
      double listPrice = item.getListPrice();
      int quantity = cartItem.getQuantity();
      subTotal += listPrice * quantity;
    }
    return subTotal;
  }

}
