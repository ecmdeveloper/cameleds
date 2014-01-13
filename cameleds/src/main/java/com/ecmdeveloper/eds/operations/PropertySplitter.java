package com.ecmdeveloper.eds.operations;

import java.util.List;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.Property;

public class PropertySplitter {

	public List<Property> splitProperties(ExternalDataRequest request ) {
		return request.getProperties();
	}
}
