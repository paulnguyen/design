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

//Listing 11.1 Enforcement of layered architecture
package ajia.architecture;

public aspect LayeringViolation {
	declare error
       : SystemArchitecture.dataLayerCall()
          && !SystemArchitecture.inRepositoryLayer()
       : "Only the repository layer is allowed to access the data layer";

	declare error
       : SystemArchitecture.repositoryLayerCall()
         && !SystemArchitecture.inRepositoryLayer()
         && !SystemArchitecture.inServiceLayer()
       : "Only the service layer is allowed to access the repository layer";
         
	declare error
       : SystemArchitecture.serviceLayerCall()
         && !SystemArchitecture.inServiceLayer()
         && !SystemArchitecture.inWebLayer()
       : "Only the UI layer is allowed to access the service layer";
         
	declare error
       : SystemArchitecture.webLayerCall()
         && !SystemArchitecture.inWebLayer()
       : "Only the UI layer is allowed to access itself";
}