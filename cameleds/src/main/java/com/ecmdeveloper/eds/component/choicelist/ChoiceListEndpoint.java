package com.ecmdeveloper.eds.component.choicelist;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Represents a ChoiceList endpoint.
 */
public class ChoiceListEndpoint extends DefaultEndpoint {

	private String displayName;
	private String valueFieldName;
	private String nameFieldName;
	private Integer valueFieldIndex;
	private Integer nameFieldIndex;
	
    public ChoiceListEndpoint() {
    }

    public ChoiceListEndpoint(String uri, ChoiceListComponent component) {
        super(uri, component);
    }

    public ChoiceListEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new ChoiceListProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new ChoiceListConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValueFieldName() {
		return valueFieldName;
	}

	public void setValueFieldName(String valueFieldName) {
		this.valueFieldName = valueFieldName;
	}

	public String getNameFieldName() {
		return nameFieldName;
	}

	public void setNameFieldName(String nameFieldName) {
		this.nameFieldName = nameFieldName;
	}

	public Integer getValueFieldIndex() {
		return valueFieldIndex;
	}

	public void setValueFieldIndex(Integer valueFieldIndex) {
		this.valueFieldIndex = valueFieldIndex;
	}

	public Integer getNameFieldIndex() {
		return nameFieldIndex;
	}

	public void setNameFieldIndex(Integer nameFieldIndex) {
		this.nameFieldIndex = nameFieldIndex;
	}
}
