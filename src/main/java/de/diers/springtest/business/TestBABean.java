/**
 * 
 */
package de.diers.springtest.business;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author robert
 *
 */
public class TestBABean implements TestBA {

	@Override
	public String sayHello(String input) {
		return input + "BEAN";
	}
	
	@Override
	@Transactional
	public String sayHelloTransactionManaged(String input) {
		return input + "TRANSBEAN";
	}

}
