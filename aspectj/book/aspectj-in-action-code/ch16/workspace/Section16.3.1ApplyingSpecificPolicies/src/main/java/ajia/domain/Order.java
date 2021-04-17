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

//Listing 16.23 Refactored Order class
//Listing 16.1: Preparing Order for dependency injection
//Listing A.4 Order: Collection of line items
package ajia.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ajia.util.DomainEntity;

@Entity
@Table(name = "orders")
@Configurable
public class Order extends DomainEntity {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Collection<LineItem> lineItems = new ArrayList<LineItem>();

    private transient PricingStrategy pricingStrategy;

    private boolean placed;

    public void addProduct(Product product, int quantity) {
        //... no checking for placed order
        LineItem lineItem = getItemFor(product);
        if (lineItem != null) {
            lineItem.setQuantity(lineItem.getQuantity() + quantity);
        } else {
            lineItem = new LineItem(this, product, 1);
            lineItem.setQuantity(quantity);
            lineItems.add(lineItem);
        }
    }

    public void removeProduct(Product product, int quantity) {
        //.. no checking for placed order
        LineItem lineItem = getItemFor(product);
        if (lineItem == null) {
            throw new IllegalArgumentException("Failed to get line item");
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
        return new ArrayList<LineItem>(lineItems);
    }

    public double getTotalPrice() {
        return pricingStrategy.getPrice(this);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    private LineItem getItemFor(Product product) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProduct().equals(product)) {
                return lineItem;
            }
        }
        return null;
    }
    
    private static aspect Freezing {
        before(Order order)
            : (execution(* Order.addProduct(..))
              || execution(* Order.removeProduct(..)))
              && this(order) {
            if(order.isPlaced()) {
                throw new IllegalStateException("Order once placed may not be modified");
            }
        }
    }
}