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

//Listing A.5 Inventory item modeling product availability
package ajia.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ajia.util.DomainEntity;

//import ...

@Entity
@Table(name="inventoryItems")
public class InventoryItem extends DomainEntity {
    private @OneToOne Product product;
    private int quantityOnHand;

    private InventoryItem() {
    }
    
    public InventoryItem(Product product) {
        this.product = product;
    }
    
    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void deplete(int quantity) {
        quantityOnHand -= quantity;
    }

    public void replenish(int quantity) {
        quantityOnHand += quantity;
    }
}
