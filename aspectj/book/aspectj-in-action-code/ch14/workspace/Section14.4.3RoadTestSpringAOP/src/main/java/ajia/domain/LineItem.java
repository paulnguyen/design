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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ajia.util.DomainEntity;

@Entity
@Table(name="lineItems")
public class LineItem extends DomainEntity {
    private int quantity;
    private @ManyToOne(cascade=CascadeType.ALL) Product product;
    private double unitPrice;
    
    @ManyToOne private Order order;

    private LineItem() {
    }
    
    public LineItem(Product product, int quantity, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.order = order;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public int setQuantity(int quantity) {
        return this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    
    public double getLineTotal() {
        return getQuantity() * getUnitPrice();
    }
}
