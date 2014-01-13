package com.ecmdeveloper.eds.core;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.ecmdeveloper.eds.model.ExternalDataRequest;

public class SetHeadersProcessor implements Processor {

	private static final String REQUEST_MODE = "requestMode";
	private static final String REPOSITORY_ID = "repositoryId";
	private static final String OBJECT_ID = "objectId";
	private static final String EXTERNAL_DATA_IDENTIFIER = "externalDataIdentifier";

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Message in = exchange.getIn();
		
		ExternalDataRequest request = (ExternalDataRequest) in.getBody();
			
		in.setHeader(REQUEST_MODE, request.getRequestMode() );
		in.setHeader(REPOSITORY_ID, request.getRepositoryId() );
		in.setHeader(OBJECT_ID, request.getObjectId() );
		in.setHeader(EXTERNAL_DATA_IDENTIFIER, request.getExternalDataIdentifier() );
		in.setHeaders( request.getClientContext() );
	}
}
