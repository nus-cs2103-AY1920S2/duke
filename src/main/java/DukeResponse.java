package com.duke;

/**
 * Response of duke from user requests
 */
public class DukeResponse {
	private String message; 

	/**
	 * [DukeResponse description]
	 * @param  message [description]
	 * @return         [description]
	 */
	public DukeResponse(String message) {
		this.message = message;
	}

	/**
	 * [getMessage description]
	 * @return [description]
	 */
	public String getMessage() {
		return this.message;
	}
}