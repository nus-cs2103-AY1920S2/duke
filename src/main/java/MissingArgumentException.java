/**
 * Exception class for our application Duke. This Exception is caught
 * when the user input commands have missing parameters.
 */
public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String message) {
        super(message);
    }
}
