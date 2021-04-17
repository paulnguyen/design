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

//Listing A.9 Inventory repository based on common JPA repository
package ajia.repository.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ajia.domain.InventoryItem;
import ajia.domain.Product;
import ajia.repository.InventoryItemRepository;
import ajia.util.JpaGenericRepository;

//import ...

@Repository(value="inventoryItemRepository")
public class JpaInventoryItemRepository 
        extends JpaGenericRepository<InventoryItem> 
        implements InventoryItemRepository {
    public InventoryItem findByProduct(Product product) {
        Query query 
            = em.createQuery("select o from InventoryItem o "
                           + "where o.product = :product");
        query.setParameter("product", product);
        try {
            return (InventoryItem) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
