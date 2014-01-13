
package com.ecmdeveloper.eds.model;

import java.util.List;

import com.ecmdeveloper.eds.model.constants.DisplayMode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Property {
	
   	private String symbolicName;
	private Object value;
	private String customValidationError;
	private List<Integer> customInvalidItems;
	private DisplayMode displayMode;
	private Boolean required;
	private Boolean hidden;
	private Object maxValue;
	private Object minValue;
	private Integer maxLength;
	private ChoiceList choiceList;
   	private Boolean hasDependentProperties;
	private String label;
   	
   	@Override
	public String toString() {
		return getSymbolicName();
	}

	public ChoiceList getChoiceList() {
		return this.choiceList;
	}
 	
	public void setChoiceList(ChoiceList choiceList) {
		this.choiceList = choiceList;
	}
	
 	public List<Integer> getCustomInvalidItems() {
		return this.customInvalidItems;
	}
 	
	public void setCustomInvalidItems(List<Integer> customInvalidItems) {
		this.customInvalidItems = customInvalidItems;
	}
	
 	public String getCustomValidationError() {
		return this.customValidationError;
	}
 	
	public void setCustomValidationError(String customValidationError) {
		this.customValidationError = customValidationError;
	}
	
 	public DisplayMode getDisplayMode() {
		return this.displayMode;
	}
 	
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}
	
 	public Boolean getHasDependentProperties() {
		return this.hasDependentProperties;
	}
 	
	public void setHasDependentProperties(Boolean hasDependentProperties) {
		this.hasDependentProperties = hasDependentProperties;
	}
	
 	public Boolean getHidden() {
		return this.hidden;
	}
 	
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	
 	public Integer getMaxLength() {
		return this.maxLength;
	}
 	
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	
 	public Object getMaxValue() {
		return this.maxValue;
	}
 	
	public void setMaxValue(Object maxValue) {
		this.maxValue = maxValue;
	}
	
 	public Object getMinValue() {
		return this.minValue;
	}
 	
	public void setMinValue(Object minValue) {
		this.minValue = minValue;
	}
	
 	public Boolean getRequired() {
		return this.required;
	}
 	
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
 	public String getSymbolicName() {
		return this.symbolicName;
	}
 	
	public void setSymbolicName(String symbolicName) {
		this.symbolicName = symbolicName;
	}
	
 	public Object getValue() {
		return this.value;
	}
 	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void sayHello() {
		System.out.println( "Property " + getDisplayMode() + " says hello!");
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
