/**
 * 
 */
package com.ecmdeveloper.eds.operations;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * @author Ricardo Belfor
 *
 */
public class EnrichHeadersAggregation implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange original, Exchange resource) {
		original.getIn().setHeaders( resource.getIn().getHeaders() );
		return original;
	}	
}
