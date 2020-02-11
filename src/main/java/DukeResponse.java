package com.duke;

/**
 * Response of duke from user requests
 */
public class DukeResponse {
	private String message; 

	public DukeResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}