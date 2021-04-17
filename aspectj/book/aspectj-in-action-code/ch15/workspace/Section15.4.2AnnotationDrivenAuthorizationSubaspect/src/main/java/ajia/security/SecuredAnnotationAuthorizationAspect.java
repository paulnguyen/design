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

//Listing 15.11 Annotation-driven authorization aspect
package ajia.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecuredAnnotationAuthorizationAspect extends
        AbstractAuthorizationAspect {
    // The use of the wildcard in org.springframework..Secured,
    // although correct, is used only for better formatting
    @Pointcut("execution(@org.springframework..Secured * *(..))"
            + "|| execution(* (@org.springframework..Secured *).*(..))")
    public void authorizationRequired() {
    }
}