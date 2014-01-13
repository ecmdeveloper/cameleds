package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.ecmdeveloper.eds.beans.LogRequest;
import com.ecmdeveloper.eds.model.constants.RequestMode;

public class ExternalDataServiceComponentTest extends ExternalDataServiceTestSupport {

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
                	.to("eds:start")
//                	.process( new SetHeadersProcessor() )
                	.bean( LogRequest.class )
                	.process( new AssertExpression( "${body.requestMode}", RequestMode.initialExistingObject) )
                	.process( new AssertExpression( "${body.repositoryId}", "target_object_store_name") )
                	.process( new AssertExpression( "${body.clientContext[Key1]}", "Value1") )
                	.process( new AssertExpression( "${body.property[property_name1].value}", "current_value1") )
                	.process( new AssertExpression( "${body.property[property_name2].value}", "current_value2") )
                	.process( new AssertExpression( "${body.property[int_property].value}", 1999) )
                	.to("eds:end")
                	.to("mock:result");
            }
        };
    }
}
