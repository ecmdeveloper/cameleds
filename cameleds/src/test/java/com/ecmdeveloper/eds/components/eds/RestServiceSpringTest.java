/**
 * 
 */
package com.ecmdeveloper.eds.components.eds;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ricardo Belfor
 *
 */
public class RestServiceSpringTest extends CamelSpringTestSupport {

	/* (non-Javadoc)
	 * @see org.apache.camel.test.spring.CamelSpringTestSupport#createApplicationContext()
	 */
	@Override
	protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("restservice-context.xml");
	}

   @Test
   public void testGetPostCode() throws Exception {
		Object body = context.createProducerTemplate().requestBody("direct:start","postcode");
		assertNotNull( body );
		System.out.println(body);
    }
}
