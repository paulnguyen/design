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

//Listing 13.22: Annotated InventoryServiceCachedImpl  class
package ajia.service.impl;

import java.util.Map;

import ajia.characteristics.ReadOnly;
import ajia.domain.InventoryItem;
import ajia.domain.Product;
import ajia.service.InventoryService;

public class InventoryServiceCachedImpl implements InventoryService {
    private Map<Long, InventoryItem> inventory;

    public void addProduct(Product product, int quantity) {
        InventoryItem inventoryItem = inventory.get(product.getId());
        inventoryItem.replenish(quantity);
    }

    public void removeProduct(Product product, int quantity) {
        InventoryItem inventoryItem = inventory.get(product.getId());
        inventoryItem.deplete(quantity);
    }

    @ReadOnly
    public boolean isProductAvailable(Product product, int quantity) {
        InventoryItem inventoryItem = inventory.get(product.getId());
        return inventoryItem.getQuantityOnHand() >= quantity;
    }

    public void setInventory(Map<Long, InventoryItem> inventory) {
        this.inventory = inventory;
    }
}