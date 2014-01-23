package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.junit.Test;

import com.ecmdeveloper.eds.model.PropertyRule;
import com.ecmdeveloper.eds.model.constants.RequestMode;
import com.ecmdeveloper.eds.operations.PropertyOperations;
import com.ecmdeveloper.eds.operations.PropertySplitter;
import com.ecmdeveloper.eds.operations.ProperySplitterAggregation;

public class PropertySplitterTest extends ExternalDataServiceTestSupport {

		@Override
		protected JndiRegistry createRegistry() throws Exception {
			JndiRegistry registry = super.createRegistry();
			PropertyRule myRule = new PropertyRule();
			myRule.setHidden(true);
			myRule.setLabel("Rules rule!");
	        registry.bind("myRule",  myRule );
			return registry;
		}
	
	   @Test
	    public void testExternalDataService() throws Exception {
	        MockEndpoint mock = getMockEndpoint("mock:result");
	        mock.expectedMinimumMessageCount(1);       
	        assertMockEndpointsSatisfied();
	    }

	    @Override
	    protected RouteBuilder createRouteBuilder() throws Exception {
	        return new RouteBuilder() {
	            public void configure() {
	                from("eds://start")
	                	.setBody( simple("resource:classpath:/edsrequest.json") )
	                	.setHeader("Hello", simple("World!") )
	                	.to("eds:start")
	                	.split().simple("${body.properties}") //method(PropertySplitter.class)
	                	.aggregationStrategy( new ProperySplitterAggregation() )
	                	.log("Split: ${body.symbolicName}") 	
	                	.log("${header.request.requestMode}")
	                	.filter( simple("${body.symbolicName} == 'property_name2'") )
		                	.beanRef("myRule")
		                	.end()
	                	.filter( simple("${body.symbolicName} == 'int_property'") )
		                	.bean(PropertyOperations.class, "notHidden")
		                	.end()
	                	.end()
	                	.process( new AssertExpression( "${body.requestMode}", RequestMode.initialExistingObject) )
	                	.process( new AssertExpression( "${body.property[property_name2].hidden}", true) )
	                	.process( new AssertExpression( "${body.property[property_name2].label}", "Rules rule!") )
	                	.process( new AssertExpression( "${body.property[property_name1].hidden}", null) )
	                	.process( new AssertExpression( "${body.property[int_property].hidden}", false) )
	                	.to("mock:result");
	            }
	        };
	    }
}
