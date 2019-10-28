/**
 * 
 */
package de.diers.springtest.presentation.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author robert
 *
 */
@ManagedBean(name="JsfWelcomeBean")
@SessionScoped
public class JsfWelcomeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String hello = "HelloWorld!";

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}	

}
