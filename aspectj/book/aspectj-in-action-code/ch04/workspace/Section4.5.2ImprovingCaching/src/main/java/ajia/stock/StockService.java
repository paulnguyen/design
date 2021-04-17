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

//Listing 4.10 Caching specification using scripts
package ajia.stock;

import ajia.caching.Cachable;

public class StockService {
	@Cachable(cacheStore = "Chart", keyScript = "#ticker + ':' + #days")
	public byte[] getQuoteGraph(String ticker, int days) {
		// Simulate creation of graph
		return ticker.getBytes();
	}
}
