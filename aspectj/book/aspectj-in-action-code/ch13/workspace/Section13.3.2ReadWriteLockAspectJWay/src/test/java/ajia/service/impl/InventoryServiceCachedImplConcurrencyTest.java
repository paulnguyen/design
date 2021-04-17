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

package ajia.service.impl;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import ajia.concurrent.AnnotationDrivenReadWriteAspect;
import ajia.domain.InventoryItem;
import ajia.domain.Product;

public class InventoryServiceCachedImplConcurrencyTest {
	@Mock ReadWriteLock mockLock;
	@Mock Lock mockReadLock;
	@Mock Lock mockWriteLock;
	
	private InventoryServiceCachedImpl testService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testService = new InventoryServiceCachedImpl();
		Map<Long, InventoryItem> mockInventory = mock(Map.class);
		InventoryItem mockInventoryItem = mock(InventoryItem.class);
		when(mockInventory.get(any(Long.class))).thenReturn(mockInventoryItem);
		testService.setInventory(mockInventory);

		when(mockLock.readLock()).thenReturn(mockReadLock);
		when(mockLock.writeLock()).thenReturn(mockWriteLock);
		ReflectionTestUtils.setField(AnnotationDrivenReadWriteAspect.aspectOf(testService), "lock", mockLock);
	}

	@Test
	public void objectsHaveIndependantLocks() {
		InventoryServiceCachedImpl testService1 = new InventoryServiceCachedImpl();
		InventoryServiceCachedImpl testService2 = new InventoryServiceCachedImpl();
		// Need invocation of a join point matching the pointcut in association specification
		testService1.setInventory(new HashMap<Long, InventoryItem>());
		testService2.setInventory(new HashMap<Long, InventoryItem>());
		Assert.assertNotSame("Locks should be shared between two objects", 
				AnnotationDrivenReadWriteAspect.aspectOf(testService1), 
				AnnotationDrivenReadWriteAspect.aspectOf(testService2));
	}
	
	@Test
	public void isProductAvailableManagesReadLock() {
		testService.isProductAvailable(new Product(), 1);
		verifyLockInteraction(mockReadLock, mockWriteLock);
	}

	@Test
	public void addProductManagesWriteLock() {
		testService.addProduct(new Product(), 1);
		verifyLockInteraction(mockWriteLock, mockReadLock);
	}
	
	@Test
	public void removeProductManagesWriteLock() {
		testService.removeProduct(new Product(), 1);
		verifyLockInteraction(mockWriteLock, mockReadLock);
	}

	@Test
	public void setInventoryManagesWriteLock() {
		testService.setInventory(new HashMap<Long, InventoryItem>());
		verifyLockInteraction(mockWriteLock, mockReadLock);
	}
	
	private void verifyLockInteraction(Lock usedLock, Lock unusedLock) {
		InOrder inOrder = inOrder(usedLock);
		inOrder.verify(usedLock).lock();
		inOrder.verify(usedLock).unlock();
		verifyZeroInteractions(unusedLock);
	}
}
