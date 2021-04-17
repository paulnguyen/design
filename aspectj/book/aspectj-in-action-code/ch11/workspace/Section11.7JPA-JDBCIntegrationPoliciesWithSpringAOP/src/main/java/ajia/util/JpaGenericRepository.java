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

//Listing A.8 Reusable generic-based repository implementation
package ajia.util;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

//import ...

public class JpaGenericRepository<T extends DomainEntity> 
    implements GenericRepository<T> {

    private Class<T> entityType;
    @PersistenceContext protected EntityManager em;
    
    @SuppressWarnings("unchecked")
    protected JpaGenericRepository() {
        this.entityType 
            = (Class<T>) 
              ((ParameterizedType)getClass().getGenericSuperclass())
                  .getActualTypeArguments()[0];
    }
    
    public T find(Long id) {
        return em.find(entityType, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return em.createQuery("select o from " + entityType.getName() +  
                              " o ").getResultList();
    }

    public void update(T entity) {
    	if (!em.contains(entity)) {
    		em.merge(entity);
    	}
    }

    public void delete(T entity) {
        em.remove(entity);
    }
}
