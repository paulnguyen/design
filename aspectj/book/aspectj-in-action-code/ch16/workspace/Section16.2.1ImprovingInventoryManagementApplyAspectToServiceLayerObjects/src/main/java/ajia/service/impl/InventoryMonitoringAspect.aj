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

//Listing 16.19 Monitoring inventory changes through aspect
package ajia.service.impl;

//import ...
import org.springframework.beans.factory.annotation.Autowired;

import ajia.domain.Product;
import ajia.service.InventoryMonitor;
import ajia.service.InventoryService;

public aspect InventoryMonitoringAspect {
    @Autowired
    private InventoryMonitor inventoryMonitor;

    public pointcut inventoryUpdate(Product product)
    : (execution(* InventoryService.addProduct(Product, ..))
    || execution(* InventoryService.removeProduct(Product, ..)))
    && args(product, ..);

    after(Product product) returning : inventoryUpdate(product) {
        inventoryMonitor.notify(product, thisJoinPoint);
    }
}