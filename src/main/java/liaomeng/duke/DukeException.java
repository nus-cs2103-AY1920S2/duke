package liaomeng.duke;

/**
 * The exception Class representing any exception that may occur when using the application.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("An Error occurred. Please refer to the following message:\n" + message);
    }
}
