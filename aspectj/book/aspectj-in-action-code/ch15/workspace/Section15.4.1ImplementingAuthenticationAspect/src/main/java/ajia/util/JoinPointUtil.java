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

package ajia.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;

public class JoinPointUtil {
    public static void rethrow(JoinPoint.StaticPart jp, Throwable ex) {
        Signature signature = jp.getSignature();
        if (!(signature instanceof CodeSignature)) {
            throw new IncompatibleRethrownException();
        }
        
        boolean mayThrow = false;
        if (ex instanceof RuntimeException) {
            mayThrow = true;
        } else {
            CodeSignature codeSignature = (CodeSignature)signature;
            for (Class exceptionType : codeSignature.getExceptionTypes()) {
                if (exceptionType.isAssignableFrom(ex.getClass())) {
                    mayThrow = true;
                    break;
                }
            }
        }
        
        if (!mayThrow) {
            throw new IncompatibleRethrownException();
        }

        try {
            synchronized (CheckedExceptionThrower.class) {
                CheckedExceptionThrower.exception = ex;
                CheckedExceptionThrower.class.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static class CheckedExceptionThrower {
        private static Throwable exception;
        
        CheckedExceptionThrower() throws Throwable {
            Throwable ex = exception;
            exception = null;
            throw ex;
        }
    }

    private static class IncompatibleRethrownException extends RuntimeException {
        
    }
}

