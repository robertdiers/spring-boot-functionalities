/**
 * 
 */
package de.diers.springtest.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.diers.springtest.ApplicationContextUtils;
import de.diers.springtest.business.TestBA;

/**
 * DO NOT FORGET @EnableScheduling FOR SPRING APPLICATION
 * @author robert
 *
 */
@Component
public class ScheduledTasks {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	//every 60 sec
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
    	
    	TestBA ba = ApplicationContextUtils.getTestBA();
    	String test = ba.sayHelloTransactionManaged("test");
    	
        System.out.println("The time is now " + dateFormat.format(new Date()) + " " + test);
    }

}
