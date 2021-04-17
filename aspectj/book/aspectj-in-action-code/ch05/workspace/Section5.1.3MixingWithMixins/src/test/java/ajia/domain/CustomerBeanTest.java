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

//Listing 5.3 Verifying change-notification functionality
package ajia.domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import static org.junit.Assert.*;

//import ...

public class CustomerBeanTest {
	@Test
	public void addressChangeNotifications() {
		Customer testCustomer = new Customer();
		testCustomer.setAddress("oldAddress");
		final AtomicInteger counter = new AtomicInteger();
		testCustomer.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				assertEquals("address", evt.getPropertyName());
				assertEquals("oldAddress", evt.getOldValue());
				assertEquals("newAddress", evt.getNewValue());
				counter.incrementAndGet();
			}
		});
		testCustomer.setAddress("newAddress");
		assertEquals(1, counter.get());
	}
}