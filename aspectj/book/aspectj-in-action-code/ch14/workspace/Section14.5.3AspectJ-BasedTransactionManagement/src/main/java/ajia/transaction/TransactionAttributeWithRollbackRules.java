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

package ajia.transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

public class TransactionAttributeWithRollbackRules extends DefaultTransactionAttribute {
    Collection<Class<? extends Throwable>> rollbackFor = new ArrayList<Class<? extends Throwable>>();
    Collection<Class<? extends Throwable>> noRollbackFor = new ArrayList<Class<? extends Throwable>>();
    
    public void addRollbackFor(Class<? extends Throwable>[] rollbackFor) {
        Collections.addAll(this.rollbackFor, rollbackFor);
    }

    public void addNoRollbackFor(Class<? extends Throwable>[] noRollbackFor) {
        Collections.addAll(this.noRollbackFor, noRollbackFor);
    }

    @Override
    public boolean rollbackOn(Throwable ex) {
        if (ex instanceof RuntimeException || ex instanceof Error) {
            for (Class<? extends Throwable> t : this.noRollbackFor) {
                if (t.isAssignableFrom(ex.getClass())) {
                    return false;
                }
            }
            return true;
        } else {
            for (Class<? extends Throwable> t : this.rollbackFor) {
                if (t.isAssignableFrom(ex.getClass())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    
}
