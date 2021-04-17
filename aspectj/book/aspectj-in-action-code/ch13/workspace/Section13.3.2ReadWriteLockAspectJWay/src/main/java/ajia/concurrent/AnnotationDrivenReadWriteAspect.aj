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

//Listing 13.21: Annotation driven subaspect for read-write lock pattern
package ajia.concurrent;

import ajia.characteristics.ReadOnly;

public aspect AnnotationDrivenReadWriteAspect extends ReadWriteLockAspect {
    public pointcut readWriteLockManaged()
        : execution(* (@ReadWriteLockManaged *).*(..));

    public pointcut readOperation()
        : execution(@ReadOnly * *(..))
          && readWriteLockManaged();

    public pointcut writeOperation()
        : !readOperation() && readWriteLockManaged();
}