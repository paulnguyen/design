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

//Listing 4.8 Monitoring cache usage
package ajia.caching;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;

public aspect CacheMonitoring {
	pointcut cacheRetrieval(String key)
: call(* Cache.getFromCache(String)) && args(key);

	after(Object key) returning(Object value)
: cacheRetrieval(key) {
		System.out.println("Cache hit. Key: " + key + " Value: " + value);
	}

	after(Object key) throwing(NeedsRefreshException ex)
: cacheRetrieval(key) {
		System.out.println("Cache miss. Key: " + key);
	}
}