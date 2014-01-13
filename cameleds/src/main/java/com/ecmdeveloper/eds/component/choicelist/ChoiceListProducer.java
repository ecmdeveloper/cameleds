package com.ecmdeveloper.eds.component.choicelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecmdeveloper.eds.model.Choice;
import com.ecmdeveloper.eds.model.ChoiceList;

/**
 * The ChoiceList producer.
 */
public class ChoiceListProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(ChoiceListProducer.class);
    private ChoiceListEndpoint endpoint;

    public ChoiceListProducer(ChoiceListEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {
        Object body = exchange.getIn().getBody();
        
		ChoiceList choiceList = createChoiceList();
		choiceList.setDisplayName( endpoint.getDisplayName() );
		
		LOG.info("Creating choice list '{}'", choiceList.getDisplayName() );
		
        if ( body instanceof List ) {
        	
        	List<?> bodyList = (List<?>) body;
        	
        	for ( Object listItem : bodyList) {
        	
        		Choice choice = null;
        		
        		if ( listItem instanceof HashMap ) {
        			choice = createChoiceFromMap( (HashMap<String, Object>) listItem );
        		} else if ( listItem instanceof List ) {
        			choice = createChoiceFromList( (List<Object>) listItem );
        		} else {
        			throw new IllegalArgumentException( listItem.toString() );
        		}
 
        		choiceList.getChoices().add( choice );        	
    		}
        }
        
        exchange.getIn().setBody(choiceList);
    }

	private Choice createChoiceFromMap(HashMap<String, Object> listItemMap) {
		Choice choice;

		String nameFieldName = endpoint.getNameFieldName();
		String valueFieldName = endpoint.getValueFieldName();

		String name = null;
		Object value = null;

		if ( nameFieldName == null && valueFieldName == null ) {
			if ( !listItemMap.isEmpty() ) {
				value = listItemMap.values().iterator().next();
				name = value.toString();
			}
			
		} else {
		
			if ( nameFieldName != null ) {
				name = (String) listItemMap.get( nameFieldName );
			}
	
			if ( valueFieldName != null ) {
				value = (String) listItemMap.get( valueFieldName );
			}
		}
		
		choice = new Choice(name, value);
		return choice;
	}

	private Choice createChoiceFromList(List<Object> listItemList) {
		Choice choice;
		String name = null;
		Integer nameFieldIndex = endpoint.getNameFieldIndex();
		Integer valueFieldIndex = endpoint.getValueFieldIndex();

		if ( nameFieldIndex == null && valueFieldIndex == null) {
			valueFieldIndex = 0;
		}
		
		if ( nameFieldIndex != null ) {
			name = (String) listItemList.get( nameFieldIndex );
		}

		String value = null;
		if ( valueFieldIndex != null ) {
			value = (String) listItemList.get( valueFieldIndex );
		}
		
		choice = new Choice(name, value);
		return choice;
	}
	private ChoiceList createChoiceList() {
		ChoiceList choiceList = new ChoiceList();
		List<Choice> choices = new ArrayList<Choice>();
		choiceList.setChoices(choices);
		return choiceList;
	}
}
