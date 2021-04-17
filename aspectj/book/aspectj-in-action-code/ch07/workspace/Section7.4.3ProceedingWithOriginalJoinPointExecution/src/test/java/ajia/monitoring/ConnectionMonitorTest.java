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
import static org.junit.Assert.*;

import java.io.PrintStream;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConnectionMonitorTest {
	@Mock private PrintStream out;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		System.setOut(out);
	}
	
	@Test
	public void connectionMonitored() {
		ExampleClass example = new ExampleClass();
		int four = example.makeDouble(2);
		assertEquals(4, four);
		verify(out).println(startsWith("Method ConnectionMonitorTest.ExampleClass.makeDouble(..) took "));
	}
	
	private static class ExampleClass {
		public int makeDouble(int x) {
			return x*2;
		}
	}

	@Aspect
	private static class ExampleClassMonitoring extends Monitoring {
		@Pointcut("execution(* ExampleClass.*(..))")
		public void monitored() {}
	}
}
