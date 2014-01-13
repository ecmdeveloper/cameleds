package com.ecmdeveloper.eds.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.ecmdeveloper.eds.model.ChoiceList;
import com.ecmdeveloper.eds.model.Property;

public class ChoiceListEnrichAggregation  implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		Property property = (Property) oldExchange.getIn().getBody();
		ChoiceList choiceList = (ChoiceList) newExchange.getIn().getBody();
		property.setChoiceList(choiceList);
		
		return oldExchange;
	}
}
