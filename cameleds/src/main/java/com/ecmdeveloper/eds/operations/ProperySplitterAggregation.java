package com.ecmdeveloper.eds.operations;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.ecmdeveloper.eds.model.ExternalDataRequest;

public class ProperySplitterAggregation implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if ( oldExchange == null ) {
			ExternalDataRequest request = newExchange.getIn().getHeader("request", ExternalDataRequest.class);
			newExchange.getIn().setBody(request);
			return newExchange;
		} else {
			return oldExchange;
		}
	}

}
