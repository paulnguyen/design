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

//Listing 16.24 Controlling exposure to aggregate root
package ajia.enforcement;

import ajia.domain.LineItem;
import ajia.domain.Order;

public aspect LineItemExposureControlAspect {
    pointcut lineOrderModification() : execution(* LineItem.set*(..));

    pointcut duringOrder() : cflow(execution(* Order.*(..)));

    before() : lineOrderModification() && !duringOrder() {
        throw new AggregateBoundaryViolationException(
                "A LineItem may not be modified except through an Order");
    }
}