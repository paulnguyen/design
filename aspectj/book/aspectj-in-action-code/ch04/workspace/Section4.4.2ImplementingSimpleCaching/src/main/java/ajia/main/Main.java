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

//Listing 4.9 Class to exercise caching functionality
package ajia.main;

import ajia.stock.StockService;

public class Main {
	public static void main(String[] args) {
		StockService service = new StockService();
		service.getQuoteGraph("GOOG");
		service.getQuoteGraph("GOOG");
		service.getQuoteGraph("YHOO");
		service.getQuoteGraph("YHOO");
		service.getQuoteGraph("GOOG");
	}
}