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

//Listing 16.20 Inventory-monitoring aspect
package ajia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ajia.service.InventoryMonitor;
import ajia.domain.InventoryItem;

public aspect InventoryMonitoringAspect {
    @Autowired private InventoryMonitor inventoryMonitor;

    public pointcut inventoryUpdate(InventoryItem item)
        : (execution(* InventoryItem.replenish(..))
          || execution(* InventoryItem.deplete(..)))
          && this(item);

    after(InventoryItem item) returning : inventoryUpdate(item) {
        inventoryMonitor.notify(item.getProduct(), thisJoinPoint);
    }
}