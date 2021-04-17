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

public class CartItem implements Serializable {

  /* Private Fields */

  private Item item;
  private int quantity;
  private boolean inStock;

  /* JavaBeans Properties */

  public boolean isInStock() { return inStock; }
  public void setInStock(boolean inStock) { this.inStock = inStock; }

  public Item getItem() { return item; }
  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

	public double getTotalPrice() {
		if (item != null) {
			return item.getListPrice() * quantity;
		}
		else {
			return 0;
		}
	}

  /* Public methods */

  public void incrementQuantity() {
    quantity++;
  }

}
