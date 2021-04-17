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

//Listing 14.16 Testing fault tolerance through retries
package ajia.service.impl;

import org.junit.Test;
import javax.persistence.EntityManager;

import org.springframework.dao.ConcurrencyFailureException;

public class FaultToleranceInventoryServiceTest extends InventoryServiceTest {
    private static aspect FaultInjector percflow(faultTest()) {
        private int faultCount = 0;
        private static int MAX_FAULT_COUNT = 2;

        pointcut faultTest()
            : execution(@Test * InventoryServiceTest.*(..))
              && this(FaultToleranceInventoryServiceTest);

        pointcut faultSite() : call(* EntityManager.*(..));

        before() : faultSite() {
            if (faultCount++ < MAX_FAULT_COUNT) {
                throw new ConcurrencyFailureException(
                        "Simulated failure for testing");
            }
        }
    }
}