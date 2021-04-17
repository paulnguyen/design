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

//Listing A.11 Inventory management service implementation
package ajia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajia.domain.InventoryItem;
import ajia.domain.Product;
import ajia.repository.InventoryItemRepository;
import ajia.service.InventoryException;
import ajia.service.InventoryService;

//import ...

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
    @Autowired InventoryItemRepository inventoryRepository;
    
    public void addProduct(Product product, int quantity) {
        InventoryItem inventoryItem 
            = inventoryRepository.findByProduct(product);
        if (inventoryItem == null) {
            inventoryItem = new InventoryItem(product);
        }
        inventoryItem.replenish(quantity);
        inventoryRepository.update(inventoryItem);
    }

    public void removeProduct(Product product, int quantity) {
        if (isProductAvailable(product, quantity)) {
            InventoryItem inventoryItem 
                = inventoryRepository.findByProduct(product);
            inventoryItem.deplete(quantity);
            inventoryRepository.update(inventoryItem);
        } else {
	        throw new InventoryException("Insufficient inventory to fulfill" 
	                                   + "request for " + product.getName() 
	                                   + " quantity " + quantity);
        }
    }
    
    public boolean isProductAvailable(Product product, int quantity) {
        InventoryItem inventoryItem 
            = inventoryRepository.findByProduct(product);
        if ((inventoryItem == null) 
                || (inventoryItem.getQuantityOnHand() < quantity)) { 
            return false;
        }
        return true;
    }
}
