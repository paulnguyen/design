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

//Listing 11.13 The NullReturnDetector aspect, that enforces the idiom
package ajia.enforcement;

//import ...
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.Collection;

public aspect RepositoryNullReturnDetector {
    private Logger logger = Logger
            .getLogger(RepositoryNullReturnDetector.class);

    after() returning(Object mapOrCollection)
    : execution((Map+ || Collection+) *..*Repository.*(..)) {
        if (mapOrCollection == null) {
            logger.error("Null detected: "
                    + thisJoinPointStaticPart.getSignature(), new Throwable());
        }
    }
}