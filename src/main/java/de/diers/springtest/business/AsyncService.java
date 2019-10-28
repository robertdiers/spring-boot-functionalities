/**
 * 
 */
package de.diers.springtest.business;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * DO NOT FORGET @EnableAsync FOR APPLICATION
 * @author robert
 *
 */
@Service
public class AsyncService {
	
	@Async
    public void execAsyncWithoutResult(String taskname) throws InterruptedException {
		System.out.println((new Date())+" "+taskname+" start");
        Thread.sleep(3000L);
        System.out.println((new Date())+" "+taskname+" end");
    }
	
	@Async
    public CompletableFuture<String> execAsync(String taskname) throws InterruptedException {
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture("asyncresult-"+taskname);
    }

}
