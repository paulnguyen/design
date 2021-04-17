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

//Listing 16.31 Testing enforcement of the aggregate boundary policy
package ajia.domain;

import org.junit.Test;
import org.mockito.Mockito;

import ajia.enforcement.AggregateBoundaryViolationException;
import ajia.enforcement.AggregateRoot;

public class AggregateBoundaryEnforcementTest {
    @Test(expected = AggregateBoundaryViolationException.class)
    public void directModification() {
        LineItem nonRoot = new LineItem(Mockito.mock(Order.class), 
                                        Mockito.mock(Product.class), 1);
        nonRoot.setQuantity(2);
    }

    @Test(expected = AggregateBoundaryViolationException.class)
    public void throughInvalidRootModification() {
        TestRoot root = new TestRoot();
        root.operation();
    }

    @Test
    public void throughValidRootModification() {
        Order order = new Order();
        order.addProduct(Mockito.mock(Product.class), 2);
    }

    @AggregateRoot("test")
    private static class TestRoot {
        public void operation() {
            LineItem nonRoot = new LineItem(Mockito.mock(Order.class), 
                                            Mockito.mock(Product.class), 1);
            nonRoot.setQuantity(2);
        }
    }
}