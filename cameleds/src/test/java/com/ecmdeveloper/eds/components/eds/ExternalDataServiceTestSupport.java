package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.test.junit4.CamelTestSupport;

public abstract class ExternalDataServiceTestSupport extends CamelTestSupport {

    class AssertExpression implements Processor {
    	
    	private final String expression;
    	private final Object expected;
    	
    	public AssertExpression(String expression, Object expected) {
			super();
			this.expression = expression;
			this.expected = expected;
		}

		@Override
    	public void process(Exchange exchange) throws Exception {
		       Expression exp = context.resolveLanguage("simple").createExpression(expression);
		       Object value = exp.evaluate(exchange, Object.class);
		       assertEquals(expected, value);
		}
    }	
}
