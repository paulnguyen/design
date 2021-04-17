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

//Listing 16.11: Improved DI through use of nested aspect
package ajia.domain;

import org.springframework.beans.factory.aspectj.AbstractInterfaceDrivenDependencyInjectionAspect;
import org.springframework.beans.factory.aspectj.ConfigurableObject;
import org.springframework.beans.factory.aspectj.GenericInterfaceDrivenDependencyInjectionAspect;

public interface PricingStrategyClient {
    static aspect Impl {
        private transient PricingStrategy PricingStrategyClient.pricingStrategy;
        
        public void PricingStrategyClient.setPricingStrategy(PricingStrategy pricingStrategy) {
            this.pricingStrategy = pricingStrategy;
        }
        
        public PricingStrategy PricingStrategyClient.getPricingStrategy() {
            return this.pricingStrategy;
        }
    }
    
    public static aspect DependencyInjectionAspect
        extends GenericInterfaceDrivenDependencyInjectionAspect
        <PricingStrategyClient> {
        //... unchanged since listing 16.9
        private PricingStrategy pricingStrategy;

        public void configure(PricingStrategyClient bean) {
            bean.setPricingStrategy(this.pricingStrategy);
        }

        public void setPricingStrategy(PricingStrategy pricingStrategy) {
            this.pricingStrategy = pricingStrategy;
        }
    }
}