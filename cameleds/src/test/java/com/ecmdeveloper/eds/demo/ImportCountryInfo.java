/**
 * 
 */
package com.ecmdeveloper.eds.demo;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ricardo Belfor
 *
 */
public class ImportCountryInfo extends CamelSpringTestSupport {

	public class CountryInfoToSQL {
		
		public String toSQL(Exchange exchange) {
			
			List<String> list = (List<String>) exchange.getIn().getBody();
			
	        StringBuilder sb = new StringBuilder();
	        sb.append("insert into country_info ");
	        sb.append("(iso,iso3,iso_numeric,fips,country,capital,area_in_sq_km,population,continent,tld,currency_code,currency_name,phone,postal_code_format,postal_code_regex,languages,geoname_id,neighbours,equivalent_fips_code) values (");
	        String concat = "";
	        int columnIndex = 0;
	        for (String value : list) {
	        	sb.append(concat);
	        	if ( !value.isEmpty() ) {
	        		if ( columnIndex == 2 || columnIndex == 6 || columnIndex == 7 || columnIndex == 16 ) {
	        			sb.append(value);
	        		} else {
	        			sb.append('\'').append(escaped(value)).append('\'');
	        		}
	        		
	        	} else {
	        		sb.append("null");
	        	}
	        	concat = ", ";
	        	++columnIndex;
	        }
	        sb.append(") ");

	        return sb.toString();
		}

		private String escaped(String value) {
			return value.replaceAll("'", "''");
		}
	}
		
	@Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
            	
            	CsvDataFormat dataFormat = new CsvDataFormat();
            	dataFormat.setDelimiter("\t");
            	dataFormat.setSkipFirstLine(true);
            	
            	from("direct:countries")
            	.setBody( simple("delete from country_info") )
            	.to("jdbc:dataSource")
            	.setBody(simple("resource:classpath:/countryInfoStripped.txt") )
            	.unmarshal(dataFormat)
            	.split().body()
            	.bean( new CountryInfoToSQL() )
//	            .log("${body}")
               	.to("jdbc:dataSource");
            }
        };
    }
	    
	/* (non-Javadoc)
	 * @see org.apache.camel.test.spring.CamelSpringTestSupport#createApplicationContext()
	 */
	@Override
	protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/derby-context.xml");
	}

   @Test
   public void testImportCountries() throws Exception {
		Object body = context.createProducerTemplate().requestBody("direct:countries","Bla");
    }
}
