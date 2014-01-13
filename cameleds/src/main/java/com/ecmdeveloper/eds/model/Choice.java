
package com.ecmdeveloper.eds.model;


public class Choice {
	
   	private String displayName;
   	private Object value;

 	public Choice(String displayName, Object value) {
		this.displayName = displayName != null ? displayName : value.toString();
		this.value = value != null ? value : displayName;
	}

 	public Choice(String value) {
		this(value, value);
	}
 	
	public String getDisplayName() {
		return this.displayName;
	}
 	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
 	public Object getValue() {
		return this.value;
	}
 	
	public void setValue(String value) {
		this.value = value;
	}
}
