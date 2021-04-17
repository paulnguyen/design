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

package ajia.repository.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ajia.AbstractIntegrationTest;
import ajia.util.DomainEntity;
import ajia.util.GenericRepository;

import static org.junit.Assert.*;

@Transactional
public abstract class GenericRepositoryTest<T extends DomainEntity> extends AbstractIntegrationTest {
	@PersistenceContext EntityManager em;
	
	public abstract GenericRepository<T> getRepository();
	
	
	@Test
	public void load() {
		// nothing to do here
	}

	public T verifyFindExisting(Long id) {
		T entity= getRepository().find(id);
		assertNotNull(entity);
		assertTrue(id.equals(entity.getId()));
		return entity;
	}
	
	public T verifyUpdate(T newEntity) {
		getRepository().update(newEntity);
		Long id = newEntity.getId();
		
		flush();
		
		T savedEntity = getRepository().find(id);
		assertNotNull(savedEntity);
		return savedEntity;
	}

	public void verifyDelete(Long id) {
		getRepository().delete(getRepository().find(id));
		
		flush();
		
		T deletedEntity = getRepository().find(id);
		assertNull(deletedEntity);
	}
	
	public void flush() {
		em.flush();
		em.clear();
	}
}