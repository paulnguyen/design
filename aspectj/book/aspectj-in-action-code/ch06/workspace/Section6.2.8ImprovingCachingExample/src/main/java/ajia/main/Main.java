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

//Listing 6.9 Modified main class to load Spring’s application context
package ajia.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ajia.stock.StockService;

//import ...

public class Main {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("applicationContext.xml");
        StockService service = new StockService();
        
        while(true) {
            service.getQuoteGraph("GOOG", 1);
            service.getQuoteGraph("GOOG", 1);
            service.getQuoteGraph("YHOO", 5);
            service.getQuoteGraph("YHOO", 365);
            service.getQuoteGraph("GOOG", 365);
            service.getQuoteGraph("GOOG", 365);

            System.in.read();
        }
    }
}
