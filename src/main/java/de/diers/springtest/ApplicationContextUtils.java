/**
 * 
 */
package de.diers.springtest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.diers.springtest.business.TestBA;

/**
 * @author robert
 *
 */
public class ApplicationContextUtils implements ApplicationContextAware {

	 private static ApplicationContext ctx;

	 private static final String TestBA = "testBA";

	  @Override
	  public void setApplicationContext(ApplicationContext appContext)
	      throws BeansException {
		  ctx = appContext;
	  }

	  public static ApplicationContext getApplicationContext() {
		  if (ctx == null) {
			  ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		  }
		  return ctx;
	  }

	  public static TestBA getTestBA(){
		  return (TestBA) getApplicationContext().getBean(TestBA);
	  }

}
