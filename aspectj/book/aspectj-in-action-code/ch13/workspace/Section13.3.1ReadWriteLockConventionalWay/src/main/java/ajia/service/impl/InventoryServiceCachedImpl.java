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

//Listing 13.17: InventoryServiceCachedImpl with the read-write lock pattern implemented
package ajia.service.impl;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import ajia.domain.InventoryItem;
import ajia.domain.Product;
import ajia.service.InventoryService;

public class InventoryServiceCachedImpl implements InventoryService {
    private Map<Long, InventoryItem> inventory;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addProduct(Product product, int quantity) {
        lock.writeLock().lock();
        try {
            InventoryItem inventoryItem = inventory.get(product.getId());
            inventoryItem.replenish(quantity);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeProduct(Product product, int quantity) {
        lock.writeLock().lock();
        try {
            InventoryItem inventoryItem = inventory.get(product.getId());
            inventoryItem.deplete(quantity);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean isProductAvailable(Product product, int quantity) {
        lock.readLock().lock();
        try {
            InventoryItem inventoryItem = inventory.get(product.getId());
            return inventoryItem.getQuantityOnHand() >= quantity;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setInventory(Map<Long, InventoryItem> inventory) {
        lock.writeLock().lock();
        try {
            this.inventory = inventory;
        } finally {
            lock.writeLock().unlock();
        }
    }
}