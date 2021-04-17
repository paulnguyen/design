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

//Listing 11.2 Aspect defining system architecture pointcuts
package ajia.architecture;

//import ...
import ajia.jdbc.*;
import ajia.jpa.*;
import ajia.spring.*;

public aspect SystemArchitecture {
    pointcut webLayerCall()
        : call(* ajia.web..*.*(..));

    pointcut inWebLayer()
        : within(ajia.web..*);

    pointcut serviceLayerCall()
        : call(* ajia.service..*.*(..));

    pointcut inServiceLayer()
         : within(ajia.service..*);

    pointcut repositoryLayerCall()
        : call(* ajia.repository..*.*(..));

    pointcut inRepositoryLayer()
        : within(ajia.repository..*);

    pointcut dataLayerCall()
        : JDBCPointcuts.jdbcCall()
          || JPAPointcuts.jpaCall()
          || SpringPointcuts.jdbcTemplateCall();
}