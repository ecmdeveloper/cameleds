/**
 * 
 */
package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.spi.Registry;
import org.junit.Test;

import com.ecmdeveloper.eds.model.Choice;
import com.ecmdeveloper.eds.model.ChoiceList;
import com.ecmdeveloper.eds.model.PropertyRule;

/**
 * @author Ricardo Belfor
 *
 */
public class EnrichPropertyRuleTest extends ExternalDataServiceTestSupport {


	@Override
	protected JndiRegistry createRegistry() throws Exception {
		JndiRegistry registry = super.createRegistry();
		PropertyRule myRule = new PropertyRule();
		myRule.setHidden(true);
		myRule.setLabel("Rules rule!");
        registry.bind("myRule",  myRule );
		return registry;
	}


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
            	
            	CsvDataFormat dataFormat = new CsvDataFormat();
            	dataFormat.setDelimiter("\t");
            	dataFormat.setSkipFirstLine(true);
            	
            	from("direct:start")
            	.setBody(simple("resource:classpath:/daltons.csv") )
            	.unmarshal().csv()
            	.to("choicelist:mydaltons?valueFieldIndex=2&nameFieldIndex=0")
 //           	.beanRef("myRule", "setChoiceList")
            	.to("bean:myRule?method=setChoiceList")
            	.process( new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						Registry registry = exchange.getContext().getRegistry();
						PropertyRule rule = registry.lookupByNameAndType("myRule", PropertyRule.class);
						assertNotNull(rule);
						assertEquals( exchange.getIn().getBody(), rule.getChoiceList() );
						
					}
				})
            	.to("mock:end");

            	from("direct:countries")
            	.setBody(simple("resource:classpath:/countryInfoStripped.txt") )
            	.unmarshal(dataFormat)
            	.to("choicelist:mydaltons?valueFieldIndex=0&nameFieldIndex=4")
 //           	.beanRef("myRule", "setChoiceList")
            	.to("bean:myRule?method=setChoiceList")
            	.process( new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						Registry registry = exchange.getContext().getRegistry();
						PropertyRule rule = registry.lookupByNameAndType("myRule", PropertyRule.class);
						assertNotNull(rule);
						assertEquals( exchange.getIn().getBody(), rule.getChoiceList() );
						
					}
				})
            	.to("mock:end");
            }
        };
    }
    
   @Test
   public void testEnrichRule() throws Exception {
		Object body = context.createProducerTemplate().requestBody("direct:start","Bla");
		assertNotNull( body );
		assertEquals( ChoiceList.class, body.getClass() );
    }

   @Test
   public void testEnrichCountries() throws Exception {
		Object body = context.createProducerTemplate().requestBody("direct:countries","Bla");
		assertNotNull( body );
		assertEquals( ChoiceList.class, body.getClass() );
		
		for ( Choice choice : ((ChoiceList)body).getChoices() ) {
			System.out.println( choice.getValue() + "\t" + choice.getDisplayName() );
		}
    }
}
