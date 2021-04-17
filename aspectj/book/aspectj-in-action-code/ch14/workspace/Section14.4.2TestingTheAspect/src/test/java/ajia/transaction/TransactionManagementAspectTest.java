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

//Listing 14.4: Test for transaction management aspect
package ajia.transaction;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.persistence.PersistenceException;

import org.aspectj.lang.Aspects;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionManagementAspectTest {
	@Mock private PlatformTransactionManager transactionManager;
	@Mock private TransactionStatus status1;
	@Mock private TransactionStatus status2;
	
	private TestService testService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(transactionManager
				.getTransaction(new DefaultTransactionDefinition()))
				.thenReturn(status1).thenReturn(status2);
		testService = new TestService();
		Aspects.aspectOf(AnnotationDrivenTransactionManagementAspect.class)
				.setTransactionManager(transactionManager);
	}

	@Test
	public void nonTransactionalMethod() {
		testService.nonTransactional();
		verifyZeroInteractions(transactionManager);
	}

	@Test
	public void transactionNoException() {
		testService.noExceptionMethod();
		verify(transactionManager).getTransaction(
				new DefaultTransactionDefinition());
		verify(transactionManager).commit(status1);
		verifyNoMoreInteractions(transactionManager);
	}

	@Test
	public void nestedTransactionNoException() {
		testService.nestedTransactionMethod();
		verify(transactionManager, times(2)).getTransaction(
				new DefaultTransactionDefinition());
		InOrder inOrder = inOrder(transactionManager);
		inOrder.verify(transactionManager).commit(status2);
		inOrder.verify(transactionManager).commit(status1);
		verifyNoMoreInteractions(transactionManager);
	}

	@Test(expected = RuntimeException.class)
	public void transactionRuntimeException() {
		try {
			testService.runtimeExceptionMethod();
		} finally {
			verify(transactionManager).getTransaction(
					new DefaultTransactionDefinition());
			verify(transactionManager).rollback(status1);
			verifyNoMoreInteractions(transactionManager);
		}
	}

	// ... more tests omitted for brevity ...
	
	private static class TestService {
		public void nonTransactional() {
		}

		@Transactional
		public void noExceptionMethod() {
		}

		@Transactional
		public void nestedTransactionMethod() {
			noExceptionMethod();
		}

		@Transactional
		public void runtimeExceptionMethod() {
			throw new PersistenceException();
		}

		@Transactional(rollbackFor = IOException.class)
		public void ioExceptionMethodWithRollback() throws IOException {
			throw new IOException();
		}

		@Transactional
		public void ioExceptionMethodWithNoRollback() throws IOException {
			throw new IOException();
		}
	}
}