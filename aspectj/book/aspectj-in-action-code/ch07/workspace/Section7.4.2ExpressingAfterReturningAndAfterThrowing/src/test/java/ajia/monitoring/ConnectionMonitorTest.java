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

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConnectionMonitorTest {
	// Okay to use a mock object, since we are advising call().
	@Mock private Connection testConnection;
	@Mock private PreparedStatement ps;
	@Mock private PrintStream out;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		System.setOut(out);
	}
	
	@Test
	public void connectionMonitoredNormal() throws SQLException {
		doReturn(ps).when(testConnection).prepareStatement("some sql");
		reset(out);
		
		testConnection.prepareStatement("some sql");
		verify(out).println("Just used testConnection successfully");
	}

	@Test(expected=SQLException.class)
	public void connectionMonitoredException() throws SQLException {
		doThrow(new SQLException()).when(testConnection).prepareStatement("some sql");
		reset(out);
		
		try {
			testConnection.prepareStatement("some sql");
		} catch (SQLException ex) {
			verify(out).println("Just used testConnection but met with a failure");
			throw ex;
		}
	}
}
