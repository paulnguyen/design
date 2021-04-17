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

//Listing 5.17 Combining member introduction and type-hierarchy modifications
package ajia.tracking;

//import ...
import org.springframework.stereotype.Service;

public aspect TimeTracker {
	private static interface LastAccessedTimeHolder {
		static aspect Impl {
			private long LastAccessedTimeHolder.lastAccessedTime;

			public long LastAccessedTimeHolder.getLastAccessedTime() {
				return lastAccessedTime;
			}

			public void LastAccessedTimeHolder.setLastAccessedTime(long time) {
				lastAccessedTime = time;
			}
		}
	}

	declare parents : @Service * implements LastAccessedTimeHolder;

	before(LastAccessedTimeHolder service)
		: execution(* LastAccessedTimeHolder+.*(..)) && this(service)
		&& !within(TimeTracker) {
		service.setLastAccessedTime(System.currentTimeMillis());
	}
}