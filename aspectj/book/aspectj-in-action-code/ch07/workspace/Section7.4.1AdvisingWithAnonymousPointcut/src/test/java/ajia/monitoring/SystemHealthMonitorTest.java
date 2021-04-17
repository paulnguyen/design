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

package ajia.monitoring;

import static org.mockito.Mockito.*;

import org.aspectj.lang.Aspects;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ajia.monitoring.example.ExampleMonitoredClass;

public class SystemHealthMonitorTest {
	@Mock HeartBeatListener mockListener;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Aspects.aspectOf(SystemHealthMonitor.class).heartBeatListener = mockListener;
	}
	
	@Test
	public void listenerNotificationForMonitoredClass() {
		ExampleMonitoredClass example = new ExampleMonitoredClass();
		example.perform();
		verify(mockListener).beat();
	}
	
	@Test
	public void listenerNotificationForNotMonitoredClass() {
		ExampleNotMonitoredClass example = new ExampleNotMonitoredClass();
		example.perform();
		verifyZeroInteractions(mockListener);
	}

	// Not monitored since the pointcut excludes classes in ajia.monitoring
	private static class ExampleNotMonitoredClass {
		public void perform() {
		}
	}
}
