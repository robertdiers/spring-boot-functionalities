/**
 * 
 */
package de.diers.springtest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import de.diers.springtest.business.TestBA;
import de.diers.springtest.business.TestBABean;

/**
 * @author robert
 *
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationConfig {
	
	private UserTransactionManager atomikosutm = new UserTransactionManager();
	private UserTransactionImp atomikosut = new UserTransactionImp();
	
	private UserTransactionManager getUserTransactionManager() {
		try {
			if (atomikosutm == null) {
				atomikosutm = new UserTransactionManager();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atomikosutm;
	}
	
	private UserTransactionImp getUserTransactionImp() {
		try {
			if (atomikosut == null) {
				atomikosut = new UserTransactionImp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atomikosut;
	}

	@Bean
    public TestBA testBA() {
        return new TestBABean();
    }

	@Bean
    public PlatformTransactionManager txManager() {
		JtaTransactionManager ptm = new JtaTransactionManager(getUserTransactionImp(), getUserTransactionManager());
        return ptm;
    }
	
}
