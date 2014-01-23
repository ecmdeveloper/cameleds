package com.ecmdeveloper.eds.operations;

import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.DisplayMode;

public class PropertyOperations {

	public void hidden(Property property) {
		property.setHidden(true);
	}
	
	public void notHidden(Property property) {
		property.setHidden(false);
	}
	
	public void required(Property property) {
		property.setRequired(true);
	}
	
	public void notRequired(Property property) {
		property.setRequired(false);
	}
	
	public void readonly(Property property) {
		property.setDisplayMode(DisplayMode.readonly);
	}
	
	public void readwrite(Property property) {
		property.setDisplayMode(DisplayMode.readwrite);
	}
	
	public void setValue(Property property, Object value) {
		property.setValue(value);
	}
}
