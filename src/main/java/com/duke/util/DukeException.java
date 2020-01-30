package com.duke.util;

/**
 * An exception that deals with all Duke specific exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructs an Duke Exception with the given error message.
     *
     * @param message Error message of the Exception.
     */
    public DukeException(String message) {
        super(message);
    }

}
