package com.ecmdeveloper.eds.component.eds;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Represents a ExternalDataService endpoint.
 */
public class ExternalDataServiceEndpoint extends DefaultEndpoint {

	private boolean start;
	private boolean end;
	
    public ExternalDataServiceEndpoint() {
    }

    public ExternalDataServiceEndpoint(String uri, ExternalDataServiceComponent component) {
        super(uri, component);
    }

    public ExternalDataServiceEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new ExternalDataServiceProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new ExternalDataServiceConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
}
