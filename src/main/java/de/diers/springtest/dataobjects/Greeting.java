/**
 * 
 */
package de.diers.springtest.dataobjects;

import java.io.Serializable;

/**
 * @author robert
 *
 */
public class Greeting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
    private String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
