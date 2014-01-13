
package com.ecmdeveloper.eds.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ExternalDataResponse {
	
   	private String externalDataIdentifier;
   	private List<Property> properties;

 	public String getExternalDataIdentifier() {
		return this.externalDataIdentifier;
	}
	
 	public void setExternalDataIdentifier(String externalDataIdentifier) {
		this.externalDataIdentifier = externalDataIdentifier;
	}
 	
	public List<Property> getProperties() {
		return this.properties;
	}
 	
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}