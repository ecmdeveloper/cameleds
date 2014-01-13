package com.ecmdeveloper.eds.component.eds;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link ExternalDataServiceEndpoint}.
 */
public class ExternalDataServiceComponent extends DefaultComponent {

    private static final String START_CONFIG = "start";
    private static final String END_CONFIG = "end";

	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new ExternalDataServiceEndpoint(uri, this);
        
        if ( START_CONFIG.equals(remaining) ) {
        	parameters.put(START_CONFIG, true);
        } else if ( END_CONFIG.equals(remaining) ) {
        	parameters.put(END_CONFIG, true);
        }
        
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
