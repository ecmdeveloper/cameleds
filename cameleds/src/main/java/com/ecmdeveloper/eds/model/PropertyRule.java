/**
 * 
 */
package com.ecmdeveloper.eds.core;

import com.ecmdeveloper.eds.model.Property;

/**
 * @author ricardobelfor
 *
 */
public class PropertyRule {

	private Boolean hidden;
	private Boolean required;
	private String label;
	
	public Boolean getHidden() {
		return hidden;
	}
	
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
		
	public void apply(Property property) {
		if ( hidden != null ) {
			property.setHidden(hidden);
		}
		
		if ( required != null ) {
			property.setRequired(required);
		}
		
		if ( label != null ) {
			property.setLabel(label);
		}
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
