package com.ecmdeveloper.eds.component.choicelist;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link ChoiceListEndpoint}.
 */
public class ChoiceListComponent extends DefaultComponent {

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new ChoiceListEndpoint(uri, this);
        parameters.put("displayName", remaining);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
