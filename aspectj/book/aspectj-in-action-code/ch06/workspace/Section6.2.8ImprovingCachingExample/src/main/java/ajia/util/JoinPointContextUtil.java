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

//Listing 4.13 Utility class that creates a map from the join point object
package ajia.util;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;

public class JoinPointContextUtil {
	public static Map<String, Object> getJoinPointContextMap(JoinPoint jp) {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("_jp", jp);
		// convenience binding
		context.put("_this", jp.getThis());
		context.put("_target", jp.getTarget());
		context.put("_args", jp.getArgs());
		Signature sig = jp.getSignature();
		if (sig instanceof CodeSignature) {
			CodeSignature codeSig = (CodeSignature) sig;
			String[] paramNames = codeSig.getParameterNames();
			Object[] args = jp.getArgs();
			for (int i = 0; i < paramNames.length; i++) {
				context.put(paramNames[i], args[i]);
			}
		}
		return context;
	}
}