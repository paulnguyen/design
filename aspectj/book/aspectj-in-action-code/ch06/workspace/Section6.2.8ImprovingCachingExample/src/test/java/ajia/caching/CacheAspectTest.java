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

//Listing 6.10 JUnit test for checking cache behavior
package ajia.caching;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;

//import ...

public class CacheAspectTest {
	private CacheAspect cacheAspect = CacheAspect.aspectOf();
	private TestService testService = new TestService();
	@Mock
	private Cache mockCache;
	@Mock
	private TestServiceHelper mockHelper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		cacheAspect.setCache(mockCache);
		testService.helper = mockHelper;
	}

	@Test
	public void cacheHit() throws Exception {
		testService.get("testArg");
		verify(mockCache).getFromCache("test:testArg");
		verifyZeroInteractions(mockHelper);
	}

	@Test
	public void cacheMiss() throws Exception {
		when(mockCache.getFromCache("test:testArg")).thenThrow(
				new NeedsRefreshException("miss"));
		testService.get("testArg");
		verify(mockHelper).get();
	}

	private static class TestService {
		private TestServiceHelper helper;

		@Cachable(cacheStore = "test", keyScript = "#arg1")
		public Object get(String arg1) {
			return helper.get();
		}
	}

	private static interface TestServiceHelper {
		Object get();
	}
}