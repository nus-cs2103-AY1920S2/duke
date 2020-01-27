package main.java;

/**
 * Main exception class that Duke throws.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     * @param error The error message to be printed.
     */
    public DukeException(String error) {
        super(error);
    }
}