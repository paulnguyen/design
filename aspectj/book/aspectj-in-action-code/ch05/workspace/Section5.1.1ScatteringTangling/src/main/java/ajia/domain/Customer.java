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

//Listing 5.2 Customer class with property change–notification functionality
package ajia.domain;

//import ...
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Customer {
	private String address;
	private PropertyChangeSupport propertyChangeSupport;

	public Customer() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		propertyChangeSupport.firePropertyChange("address", this.address,
				address);
		this.address = address;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}