package com.ecmdeveloper.eds.custom;

import java.util.Date;

import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;

public class CustomBean {

	public void execute(ExternalDataResponse request) {
	
		for ( Property p : request.getProperties() ) {
			System.out.println( p.getSymbolicName() + "\t" + p.getValue() );
			if ( p.getSymbolicName().equals("property_name2") ) {
				p.setValue( (new Date()).toString() );
			}
		}
	}
}
