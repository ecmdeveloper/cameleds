package com.ecmdeveloper.eds.component.eds;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The ExternalDataService producer.
 */
public class ExternalDataServiceProducer extends DefaultProducer {
	
	private static final String REQUEST_HEADER = "request";
	
    private static final Logger LOG = LoggerFactory.getLogger(ExternalDataServiceProducer.class);
    private ExternalDataServiceEndpoint endpoint;

    public ExternalDataServiceProducer(ExternalDataServiceEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {

        if (this.endpoint.isStart() ) {
        	LOG.debug("Converting Request JSON to bean");
	        ObjectMapper mapper = new ObjectMapper();
	        Message message = exchange.getIn();
			ExternalDataRequest request = mapper.readValue( (String) message.getBody(), ExternalDataRequest.class);
	        message.setHeader(REQUEST_HEADER, request );
	        message.setBody(request);
			
        } else if ( this.endpoint.isEnd() ) {
	        Message message = exchange.getIn();
	        ExternalDataRequest externalDataRequest = message.getBody(ExternalDataRequest.class);
	        String valueAsString = getNewBody(externalDataRequest);
	        System.out.println(valueAsString);
	        message.setBody(valueAsString);
        } else {
        	throw new IllegalArgumentException();
        }
    }

	private String getNewBody(ExternalDataRequest externalDataRequest)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ExternalDataResponse externalDataResponse = createExternalDataResponse(externalDataRequest);
		String valueAsString = mapper.writeValueAsString(externalDataResponse);
		return valueAsString;
	}

	private ExternalDataResponse createExternalDataResponse(
			ExternalDataRequest externalDataRequest) {
		ExternalDataResponse externalDataResponse = new ExternalDataResponse();
		externalDataResponse.setProperties(externalDataRequest.getProperties() );
		return externalDataResponse;
	}

}
