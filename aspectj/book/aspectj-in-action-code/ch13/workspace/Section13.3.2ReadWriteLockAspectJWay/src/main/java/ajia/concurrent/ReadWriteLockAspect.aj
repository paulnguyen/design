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

//Listing 13.18: Base read-write lock aspect 
package ajia.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract aspect ReadWriteLockAspect
    perthis(readOperation() || writeOperation()) {
    
    public abstract pointcut readOperation();
    public abstract pointcut writeOperation();
    
    private ReadWriteLock lock
        = new ReentrantReadWriteLock();
    
    Object around() : readOperation() {
        lock.readLock().lock();
        try {
            return proceed();
        } finally {
            lock.readLock().unlock();
        }
    }
    
    Object around() : writeOperation() {
        lock.writeLock().lock();
        try {
            return proceed();
        } finally {
            lock.writeLock().unlock();
        }
    }
}