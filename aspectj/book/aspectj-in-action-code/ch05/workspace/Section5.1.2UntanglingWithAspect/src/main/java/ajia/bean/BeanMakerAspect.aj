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

//Listing 5.4: Aspect to add change-notification functionality in the Customer class
package ajia.bean;

import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.apache.commons.beanutils.BeanUtils;

import ajia.domain.Customer;

public aspect BeanMakerAspect {
    private PropertyChangeSupport Customer.propertyChangeSupport;

    public void Customer.addPropertyChangeListener(
            PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void Customer.removePropertyChangeListener(
            PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    pointcut beanCreation(Customer bean)
	    : initialization(Customer+.new(..)) && this(bean);

    pointcut beanPropertyChange(Customer bean, Object newValue)
	    : execution(void Customer+.set*(*))
	      && args(newValue) && this(bean);

    after(Customer bean) returning : beanCreation(bean) {
        bean.propertyChangeSupport = new PropertyChangeSupport(bean);
    }

    void around(Customer bean, Object newValue)
	    : beanPropertyChange(bean, newValue) {
        String methodName = thisJoinPointStaticPart.getSignature().getName();
        String propertyName = Introspector
                .decapitalize(methodName.substring(3));
        Object oldValue = getPropertyValue(bean, propertyName);
        proceed(bean, newValue);
        bean.propertyChangeSupport.firePropertyChange(propertyName, oldValue,
                newValue);
    }

    private static Object getPropertyValue(Object bean, String propertyName) {
        try {
            return BeanUtils.getProperty(bean, propertyName);
        } catch (Exception ex) {
            return null;
        }
    }
}