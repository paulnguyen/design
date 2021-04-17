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

//Listing 11.22 ViolationBean.java: a bean that violates the EJB rules
package ajia.ejb;

import javax.swing.JOptionPane;
import javax.ejb.Stateless;

//import ...;
@Stateless
public class ViolationBean {
    private static int subscriptionCount;

    // ...
    public void addSubscription(String subscriptionKey) {
        try {
            Subscription subscription = find(subscriptionKey);
            // ... business logic
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Exception while adding subscription");
            ex.printStackTrace();
        }
        subscriptionCount++;
    }

    // ...find method etc.
    public Subscription find(String subscriptionKey) {
        new Subscription().checkRunTimeViolation();
        return null;
    }
}