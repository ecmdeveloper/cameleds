/**
 * 
 */
package com.ecmdeveloper.eds.model;


/**
 * @author ricardobelfor
 *
 */
public class PropertyRule implements ChoiceListContainer{

	private Boolean hidden;
	private Boolean required;
	private String label;
	private ChoiceList choiceList;
	
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

	@Override
	public ChoiceList getChoiceList() {
		return choiceList;
	}

	@Override
	public void setChoiceList(ChoiceList choiceList) {
		this.choiceList = choiceList;
	}
}
