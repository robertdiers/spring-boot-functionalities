/**
 * 
 */
package de.diers.springtest.presentation.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.diers.springtest.ApplicationContextUtils;
import de.diers.springtest.business.AsyncService;
import de.diers.springtest.business.TestBA;
import de.diers.springtest.dataobjects.Greeting;

/**
 * @author robert
 *
 */
@RestController
public class TestRestController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Inject AsyncService asyncService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        
    	//test bean call
    	TestBA ba = ApplicationContextUtils.getTestBA();
    	String newname = ba.sayHello(name);
    	
    	//test transactional bean call
    	newname = ba.sayHelloTransactionManaged(newname);
    	
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, newname));
    }
    
    @RequestMapping("/asynctest")
    public String asynctest() {
        
    	try {
	    	// Start the clock
	        long start = System.currentTimeMillis();
	        
	        asyncService.execAsyncWithoutResult("fireandforget");
	
	        // Kick of multiple, asynchronous lookups
	        CompletableFuture<String> page1 = asyncService.execAsync("A");
	        CompletableFuture<String> page2 = asyncService.execAsync("B");
	        CompletableFuture<String> page3 = asyncService.execAsync("C");
	        CompletableFuture<String> page4 = asyncService.execAsync("D");
	        CompletableFuture<String> page5 = asyncService.execAsync("E");
	        CompletableFuture<String> page6 = asyncService.execAsync("F");
	        CompletableFuture<String> page7 = asyncService.execAsync("G");
	
	        // Wait until they are all done
	        CompletableFuture.allOf(page1,page2,page3,page4,page5,page6,page7).join();
	
	        // Print results, including elapsed time
	        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
	        System.out.println("--> " + page1.get());
	        System.out.println("--> " + page2.get());
	        System.out.println("--> " + page3.get());
	        System.out.println("--> " + page4.get());
	        System.out.println("--> " + page5.get());
	        System.out.println("--> " + page6.get());
	        System.out.println("--> " + page7.get());
	        
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return "triggered...";
    }

}