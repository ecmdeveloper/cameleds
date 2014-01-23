/**
 * 
 */
package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.junit.Test;

import com.ecmdeveloper.eds.operations.PropertyOperations;

/**
 * @author Ricardo Belfor
 *
 */
public class RestServiceTest extends ExternalDataServiceTestSupport {

	@Override
	protected JndiRegistry createRegistry() throws Exception {
		JndiRegistry registry = super.createRegistry();
		PropertyOperations myOperations = new PropertyOperations();
        registry.bind("myOperations",  myOperations );
		return registry;
	}
	
   @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start")
            	.setBody( simple("resource:classpath:/postcode_request.json") )
            	.to("eds:start")
            	.setHeader("postcode", simple("${body.property[postcode].value}") )
            	.enrich("direct:postcode", new AggregationStrategy() {

					@Override
					public Exchange aggregate(Exchange original,
							Exchange resource) {
						original.getIn().setHeaders( resource.getIn().getHeaders() );
						return original;
					}})
				.to("bean:myOperations?method=setValue(${body.property[street]},${header[street]})")	
				.to("bean:myOperations?method=setValue(${body.property[town]},${header[town]})")	
				.to("bean:myOperations?method=setValue(${body.property[province]},${header[province]})")	
                .process( new AssertExpression( "${body.property[street].value}", "Sjalotpad") )
                .process( new AssertExpression( "${body.property[town].value}", "Almere") )
                .process( new AssertExpression( "${body.property[province].value}", "Flevoland") )
            	.to("eds:end");

                from("direct:postcode")
                .log("Enrich headers: ${headers}")
            	.setHeader("Api-Key", constant("ef40493a981f3dc1353930bf103ceca39accdca5" ) )
            	.setHeader(Exchange.HTTP_URI, simple("http://api.postcodeapi.nu/${header.postcode}"))
            	.setBody(simple("not used"))
            	.to("http4://api.postcodeapi.nu")
            	.unmarshal().json(JsonLibrary.Jackson)
//            	.unmarshal().json(JsonLibrary.Jackson, PostcodeLookup.class)
            	.setHeader( "street", simple("${body[resource][street]}") )
            	.setHeader( "town", simple("${body[resource][town]}") )
            	.setHeader( "province", simple("${body[resource][province]}") )
            	.setHeader( "latitude", simple("${body[resource][latitude]}") )
            	.setHeader( "longitude", simple("${body[resource][longitude]}") );
                 
            }
        };
   }

   @Test
   public void testGetPostCode() throws Exception {
		Object body = context.createProducerTemplate().requestBody("direct:start","postcode");
		assertNotNull( body );
		System.out.println(body);
    }
}
