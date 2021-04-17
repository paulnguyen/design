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

//Listing 4.14 Class to evaluate Spring Expression language scripts
package ajia.expression;

//import ...
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelExpressionEvaluator implements ExpressionEvaluator {
	private ExpressionParser parser = new SpelExpressionParser();

	public Object evaluate(String script, Map<String, Object> context)
			throws ParseException, EvaluationException {
		Expression expression = parser.parseExpression(script);
		EvaluationContext evalContext = new StandardEvaluationContext();
		for (String key : context.keySet()) {
			evalContext.setVariable(key, context.get(key));
		}
		return expression.getValue(evalContext);
	}
}