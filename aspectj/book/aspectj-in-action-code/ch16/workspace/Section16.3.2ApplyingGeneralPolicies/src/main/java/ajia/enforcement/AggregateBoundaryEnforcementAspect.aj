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

//Listing 16.30 Generalized aspect to enforce aggregate boundary policies
package ajia.enforcement;

import org.apache.log4j.Logger;

import ajia.service.impl.ReadOnly;

public aspect AggregateBoundaryEnforcementAspect {
    private Logger logger = Logger
            .getLogger(AggregateBoundaryEnforcementAspect.class);

    pointcut nonAggregateRootModification(Aggregate aggregate)
        : execution(!@ReadOnly * *(..)) && @within(aggregate);

    pointcut aggregateRootOperation(AggregateRoot aggregateRoot)
        : execution(* *(..)) && @within(aggregateRoot);

    pointcut directModification()
        : nonAggregateRootModification(*)
          && !cflowbelow(aggregateRootOperation(*));

    pointcut throughRootModification(AggregateRoot aggregateRoot,
            Aggregate aggregateNonRoot)
        : nonAggregateRootModification(aggregateNonRoot)
          && cflowbelow(aggregateRootOperation(aggregateRoot));

    before() : directModification() {
        throw new AggregateBoundaryViolationException(
                "Non-root entity modified without going through"
                + "its aggregate root");
    }

    before(AggregateRoot aggregateRoot, Aggregate aggregateNonRoot)
    : throughRootModification(aggregateRoot, aggregateNonRoot) {
        if (!aggregateRoot.value().equals(aggregateNonRoot.value())) {
            throw new AggregateBoundaryViolationException(
                    "Non-aggregate root object not accessed though its root");
        }
    }
}