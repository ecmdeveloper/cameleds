package com.ecmdeveloper.eds.beans;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

import com.ecmdeveloper.eds.model.ExternalDataRequest;

public class LogRequest {

//	public void log(Exchange exchange) {
//		
//		Object body = exchange.getIn().getBody();
//		if ( body != null ) {
//			System.out.println(body.toString() );
//		}
//	}
	
	public void log(ExternalDataRequest request, Exchange exchange) {

		System.out.println( "Header: " + exchange.getIn().getHeader("objectId") );
		if ( request == null) {
			return;
		}
		
		System.out.println( "Repository Id: " + request.getRepositoryId() );
		System.out.println( "Object Id: " + request.getObjectId() );
		System.out.println( "Request Mode: " + request.getRequestMode() );
	}

//	public void log(Exchange exchange) {
//
//		System.out.println( "Header: " + exchange.getIn().getHeader("objectId") );
//	}
}
