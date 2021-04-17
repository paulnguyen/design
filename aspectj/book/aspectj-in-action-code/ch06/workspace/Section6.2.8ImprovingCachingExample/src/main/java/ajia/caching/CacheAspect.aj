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

//Listing 6.7 Preparing the aspect for DI
package ajia.caching;

//import ...
import java.util.Map;

import ajia.expression.ExpressionEvaluator;
import ajia.expression.SpelExpressionEvaluator;
import ajia.util.JoinPointContextUtil;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;

public aspect CacheAspect {
	private Cache cache;
	private boolean enabled = true;
	private ExpressionEvaluator expressionEvaluator = new SpelExpressionEvaluator();
	
	// ... pointcut unchanged from listing 4.12
	public pointcut cachedAccess(Cachable cachable)
		: execution(@Cachable * *(..)) && @annotation(cachable);

	Object around(Cachable cachable) : cachedAccess(cachable) {
		if (!enabled) {
			return proceed(cachable);
		}
		// ... remaining advice unchanged
		Map<String, Object> jpContextMap = JoinPointContextUtil
				.getJoinPointContextMap(thisJoinPoint);
		String key = null;
		try {
			key = cachable.cacheStore()
					+ ":"
					+ expressionEvaluator.evaluate(cachable.keyScript(),
							jpContextMap);
		} catch (Exception ex) {
			System.out.println("Exception evaluating expression " + ex);
			return proceed(cachable);
		}
		Object cachedValue = null;
		try {

			cachedValue = cache.getFromCache(key);
		} catch (NeedsRefreshException ex) {
			cachedValue = proceed(cachable);
			cache.putInCache(key, cachedValue);
		}
		return cachedValue;

	}

	public Cache getCache() {
		return this.cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}