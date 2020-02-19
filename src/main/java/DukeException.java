package main.java;

/**
 * Class to handle exceptions with Duke program
 */
public class DukeException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}