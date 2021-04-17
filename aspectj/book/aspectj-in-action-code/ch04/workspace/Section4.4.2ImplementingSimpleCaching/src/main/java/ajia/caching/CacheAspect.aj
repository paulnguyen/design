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

//Listing 4.7 Aspect that implements the caching logic
package ajia.caching;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;

public aspect CacheAspect {
	private Cache cache = new Cache(true, true, false);

	public pointcut cachedAccess(Object arg, Cachable cachable)
: execution(@Cachable * *(*))
&& args(arg) && @annotation(cachable);

	Object around(Object arg, Cachable cachable)
: cachedAccess(arg, cachable) {
		if (arg == null) {
			return proceed(arg, cachable);
		}
		String key = cachable.cacheStore() + ":" + arg;
		Object cachedValue = null;
		try {
			cachedValue = cache.getFromCache(key);
		} catch (NeedsRefreshException ex) {
			// either the value isn't in the cache,
			// or the value is stale
			cachedValue = proceed(arg, cachable);
			cache.putInCache(key, cachedValue);
		}
		return cachedValue;
	}
}