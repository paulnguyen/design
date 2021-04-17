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

package ajia.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ajia.util.DomainEntity;


@Entity
@Table(name="orders")
public class Order extends DomainEntity {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Collection<LineItem> lineItems = new ArrayList<LineItem>();
    private boolean placed;

    public void addItem(Product product, int quantity) {
        if (isPlaced()) {
            throw new IllegalStateException("Order once placed may not be modified");
        }
        LineItem lineItem = getItemFor(product);
        if (lineItem != null) {
            lineItem.setQuantity(lineItem.getQuantity() + quantity);
        } else {
            lineItem = new LineItem(product, 1, this);
            lineItem.setQuantity(quantity);
            lineItems.add(lineItem);
        }
    }

    public void removeItem(Product product, int quantity) {
        if (isPlaced()) {
            throw new IllegalStateException("Order once placed may not be modified");
        }
        LineItem lineItem = getItemFor(product);

        if (lineItem == null) {
            throw new IllegalArgumentException(
                    "Failed to get line item");
        }
        int currentQuantity = lineItem.getQuantity();
        if (currentQuantity < quantity) {
            throw new IllegalArgumentException(
                    "Removing more quantity than present");
        }
        if (currentQuantity == quantity) {
            lineItems.remove(lineItem);
        }
        lineItem.setQuantity(currentQuantity - quantity);
    }

    public boolean isPlaced() {
        return placed;
    }

    public void place() {
        placed = true;
    }

    public void cancel() {
        placed = false;
    }

    public Collection<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (LineItem lineItem : getLineItems()) {
            totalPrice += lineItem.getLineTotal();
        }
        return totalPrice;
    }

    private LineItem getItemFor(Product product) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProduct().equals(product)) {
                return lineItem;
            }
        }
        return null;
    }
}
