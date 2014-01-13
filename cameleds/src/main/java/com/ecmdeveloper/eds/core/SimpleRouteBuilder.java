package com.ecmdeveloper.eds.core;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.CsvDataFormat;

public class SimpleRouteBuilder extends AbstractRouteBuilder {

	@Override
	public void configure() throws Exception {

//		JacksonDataFormat dataFormat = new JacksonDataFormat(ExternalDataRequest.class);
//    	JacksonDataFormat outputDataFormat = new JacksonDataFormat(ExternalDataResponse.class);
//  	outputDataFormat.getObjectMapper().setSerializationInclusion(Include.NON_NULL);
		
       	CsvDataFormat csvData = new CsvDataFormat();
    	csvData.setDelimiter(",");
     	
//		from("direct:edsstart")
//			.unmarshal(dataFormat)
//			.bean(new LogRequest())
//			.split().method(PropertySplitter.class)
//			.aggregationStrategy( new ProperySplitterAggregation() )
//			.setHeader("SymbolicName", simple("${body.symbolicName}") )
//				.filter( simple("${body.symbolicName} == 'property_name2'") )
//				.beanRef("propertyOperations", "notHidden")
//				.end()
//				.filter( simple("${body.symbolicName} == 'property_name1'") )
//				.enrich("direct:csvresource", new ChoiceListEnrichAggregation() )
//				.bean( new Hidden() )
//				.log( "{body}.displayName")
//				.end()
//			.end()
//			.log("Aggregate: ${body}")
//			.bean( com.ecmdeveloper.eds.custom.CustomBean.class)
//			.marshal(outputDataFormat)
//			.convertBodyTo(String.class).
//			to("mock:split");
		
//		from("direct:csvresource")
//		.log("Reading CSV file" )
//		.setBody(simple("/Users/ricardobelfor/Documents/customeds/cameleds/src/data/csv/daltons.csv") )
//		.process( new Processor() {
//			@Override
//			public void process(Exchange exchange) throws Exception {
//				Object value = exchange.getIn().getBody();
//				String filename = exchange.getContext().getTypeConverter().convertTo(String.class, value);
//				exchange.getIn().setBody(  new File( filename ) );
//			} } )
//	    .unmarshal(csvData)
//		.to("choicelist:daltons?displayName=Names&valueFieldIndex=0");
	}

}
