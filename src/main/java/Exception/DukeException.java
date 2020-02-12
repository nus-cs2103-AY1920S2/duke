package com.duke.dukeException;

/**
 * Duke exception, just another way to customize exceptions.
 */
public class DukeException extends Exception{
	private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
    	return this.message;
    }
}