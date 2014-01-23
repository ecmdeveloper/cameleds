/**
 * 
 */
package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.builder.RouteBuilder;

import com.ecmdeveloper.eds.beans.LogRequest;
import com.ecmdeveloper.eds.components.eds.ExternalDataServiceTestSupport.AssertExpression;
import com.ecmdeveloper.eds.model.constants.RequestMode;

/**
 * @author Ricardo Belfor
 *
 */
public class EnrichPropertyTest extends ExternalDataServiceTestSupport {

   @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("eds://start")
                	.setBody( simple("resource:classpath:/edsrequest.json") )
                	.to("eds:start")
                	.bean( LogRequest.class )
                	.to("eds:end")
                	.to("mock:result");
            }
        };
    }
}
