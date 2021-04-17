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

//Listing 10.20 Shipping service implementation
package ajia.service.impl;

//import ...

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ajia.domain.Order;
import ajia.domain.shipment.Package;
import ajia.domain.shipment.Tracking;
import ajia.service.CourierService;
import ajia.service.EmailerService;
import ajia.service.ShippingService;

public class ShippingServiceImpl implements ShippingService {
    private Logger logger = Logger.getLogger(ShippingServiceImpl.class);
    private CourierService courierService = new CourierServiceImpl();
    private EmailerService emailer = new EmailerServiceImpl();

    public void processOrder(Order order) {
        logger.log(Level.INFO,
                "[ShippingServiceImpl.processOrder] Processing order "
                        + order.getId());
        Package packageToSend = createPackage(order);
        logger.log(Level.INFO,
                "[ShippingServiceImpl.processOrder] Notifying courier "
                        + "service with " + packageToSend);
        Tracking tracking = courierService.send(packageToSend);
        logger.log(Level.INFO,
                "[ShippingServiceImpl.processOrder] Sending email with "
                        + tracking);
        emailer.send(tracking);
        logger.log(Level.INFO,
                "[ShippingServiceImpl.processOrder] Finished processing");
    }

    private Package createPackage(Order order) {
        logger.log(Level.INFO,
                "[ShippingServiceImpl.createPackage] Creating package");
        return new Package("1234");
    }
}