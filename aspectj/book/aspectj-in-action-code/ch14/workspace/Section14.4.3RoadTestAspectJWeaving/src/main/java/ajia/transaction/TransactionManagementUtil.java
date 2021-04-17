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

import org.springframework.transaction.annotation.Transactional;

public class TransactionManagementUtil {
    public static TransactionAttributeWithRollbackRules createTransactionAttribute(
            Transactional typeAnnotation, Transactional methodAnnnnotation) {
        TransactionAttributeWithRollbackRules txAttribute = new TransactionAttributeWithRollbackRules();
        if (typeAnnotation != null) {
            updateTransactionAttribute(txAttribute, typeAnnotation);
        }
        if (methodAnnnnotation != null) {
            updateTransactionAttribute(txAttribute, methodAnnnnotation);
        }
        return txAttribute;
    }
    
    private static void updateTransactionAttribute(TransactionAttributeWithRollbackRules txAttribute, Transactional txAnnotation) {
        txAttribute.setPropagationBehavior(txAnnotation.propagation().value());
        txAttribute.setIsolationLevel(txAnnotation.isolation().value());
        txAttribute.setReadOnly(txAnnotation.readOnly());
        txAttribute.setTimeout(txAnnotation.timeout());
        txAttribute.addRollbackFor(txAnnotation.rollbackFor());
        txAttribute.addRollbackFor(txAnnotation.noRollbackFor());
    }
}
