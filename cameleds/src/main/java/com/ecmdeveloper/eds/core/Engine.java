package com.ecmdeveloper.eds.core;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;

public class Engine implements CamelContextAware {

	private CamelContext camelContext;

	@Override
	public CamelContext getCamelContext() {
		return camelContext;
	}

	@Override
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}
	
	public String convert(Object input) {

		String body = camelContext.getTypeConverter().convertTo(String.class, input);
		Object requestBody = camelContext.createProducerTemplate().requestBody(
				"direct:myroute", body);
		
		return requestBody.toString();
	}
}
